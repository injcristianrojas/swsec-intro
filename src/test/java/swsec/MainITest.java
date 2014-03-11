package swsec;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class MainITest {

	private WebTester tester;

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://127.0.0.1:8080/");
	}

	@Test
	public void test() {
		System.out.println("Basic test (login)...");

		tester.beginAt("login.jsp");
		tester.assertTitleEquals("Fans de las aves chilenas (SWSEC Intro)");
		tester.setTextField("username", "crirojas");
		tester.setTextField("password", "123");
		tester.submit();
		
		tester.assertTextPresent("Usuario: crirojas");
		tester.clickLink("exit");
		
		tester.assertTextNotPresent("Usuario: crirojas");
		tester.assertTextPresent("Login");
	}

}
