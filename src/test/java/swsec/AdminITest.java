package swsec;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AdminITest {

	private WebTester tester;
	private static final String TEST_USER = "testuser";
	private static final String TEST_PASSWORD = "testpass";

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://127.0.0.1:8080/");
		tester.beginAt("login.jsp");
		tester.setTextField("username", "admin");
		tester.setTextField("password", "admin");
		tester.submit();
		tester.clickLink("admin");
	}

	@Test
	public void userCreationTest() {
		tester.setTextField("username", TEST_USER);
		tester.setTextField("password", TEST_PASSWORD);
		tester.submit();
		tester.assertTextPresent(TEST_USER);
	}

	public void userDeletionTest() {
		tester.clickLink("delete_" + TEST_USER);
		tester.assertTextNotPresent(TEST_USER);
	}

	@After
	public void close() {
		tester.clickLink("exit");
	}

}
