package injcristianrojas.dbsetup;

import java.io.File;

import org.tmatesoft.sqljet.core.SqlJetException;
import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.ISqlJetTable;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

public class DBSetup {

	static String DB_FILE = "swsecdemo.sqlite";

	public static void main(String[] args) throws SqlJetException {
		// Destuimos la DB
		File dbFile = new File(DB_FILE);
		dbFile.delete();

		// ...y la creamos desde cero
		SqlJetDb db = SqlJetDb.open(dbFile, true);
		db.getOptions().setAutovacuum(true);
		db.beginTransaction(SqlJetTransactionMode.WRITE);
		try {
			db.createTable("CREATE TABLE 'mensajes' ('id' INTEGER PRIMARY KEY  NOT NULL , 'mensaje' TEXT NOT NULL );");
			db.createTable("CREATE TABLE 'usuarios' ('id' INTEGER PRIMARY KEY  NOT NULL , 'username' VARCHAR(10) NOT NULL , 'password' VARCHAR(10) NOT NULL , 'type' INTEGER);");
		} finally {
			db.commit();
		}
		
		db.beginTransaction(SqlJetTransactionMode.WRITE);
		try {
			ISqlJetTable table = db.getTable("usuarios");
			table.insert("crirojas", "123", "1");
			table.insert("jperez", "12345", "1");
			table.insert("skramer", "skramer", "1");
			table.insert("aeinstein", "simple", "2");
		} finally {
			db.commit();
		}
		db.close();
	}
}
