package swsec;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class PasswordChangeITest {

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
		tester.setTextField("username", "jperez");
		tester.setTextField("password", "123");
		tester.submit();
		
		tester.assertTextPresent("Usuario: jperez");
		tester.clickLink("account");

		tester.assertTextPresent("Cambiar Password");
		tester.setTextField("password", "456");
		tester.submit();
		tester.assertTextPresent("Password cambiada exitosamente");

		tester.clickLink("account");

		tester.assertTextPresent("Cambiar Password");
		tester.setTextField("password", "123");
		tester.submit();
		tester.assertTextPresent("Password cambiada exitosamente");
		tester.clickLink("exit");
		
		tester.assertTextNotPresent("Usuario: jperez");
		tester.assertTextPresent("Login");
	}

}
