package swsec.dbsetup;

import java.io.File;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

public class DBSetup {

	private static final String DB_FILE = "src/main/webapp/WEB-INF/swsecdemo.sqlite";

	public static void main(String[] args) {

		boolean clean = (args.length != 0);

		// creamos la db desde cero
		File dbFile = new File(DB_FILE);
		if (clean) {
			if (dbFile.delete()) {
				System.out.println("[INFO] Database reset successful.");
			} else {
				System.out.println("[INFO] Error during database reset.");
			}
		}
		SqlJetDb db = null;
		boolean tableExists = false;
		if (dbFile.exists()) {
			try {
				db = SqlJetDb.open(dbFile, true);
	      db.beginTransaction(SqlJetTransactionMode.WRITE);
				tableExists = db.getSchema().getTable("usuarios") != null;
				db.commit();
				db.close();
			} catch (SqlJetException e) {
	      e.printStackTrace();
	    }
		}
		if (tableExists) {
			System.out.println("[INFO] Database already contains data.");
			return;
		}
    try {
      db = SqlJetDb.open(dbFile, true);
      db.getOptions().setAutovacuum(true);
      db.beginTransaction(SqlJetTransactionMode.WRITE);
      db.createTable("CREATE TABLE 'mensajes' ('id' INTEGER PRIMARY KEY NOT NULL, 'mensaje' TEXT NOT NULL );");
      db.createTable("CREATE TABLE 'usuarios' ('id' INTEGER PRIMARY KEY NOT NULL, 'username' VARCHAR(10) NOT NULL, 'password' VARCHAR(10) NOT NULL ,'type' INTEGER);");
      ISqlJetTable table = db.getTable("usuarios");
      table.insert("jperez", "123", "1");
      table.insert("basaber", "12345", "1");
      table.insert("skramer", "power", "2");
      table.insert("aeinstein", "simple", "2");
      table.insert("tstark", "ironman", "2");
      table.insert("cpalma", "lepego", "2");
      table.insert("asavage", "boom", "1");
      table.insert("jhyneman", "boom", "1");
      table.insert("tanderson", "matrix", "1");
      table.insert("zcool", "god", "1");
      db.commit();
      db.close();
    } catch (SqlJetException e) {
      e.printStackTrace();
    }
	}
}
