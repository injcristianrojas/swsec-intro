package swsec;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class SQLInjectionITest {

	private WebTester tester;

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://127.0.0.1:8080/");
	}

	@Test
	public void test() {
		tester.beginAt("login.jsp");
		tester.assertTitleEquals("Fans de las aves chilenas (SWSEC Intro)");
		tester.setTextField("username", "hackerMalo");
		tester.setTextField("password", "' or 1=1;--");
		tester.submit();
		
		tester.assertTextPresent("Usuario: hackerMalo");
		tester.clickLink("exit");
		
		tester.assertTextNotPresent("Usuario: hackerMalo");
		tester.assertTextPresent("Login");
	}

}
