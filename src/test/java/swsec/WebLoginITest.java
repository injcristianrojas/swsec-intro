package swsec;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.Before;
import org.junit.Test;

public class WebLoginITest {

	private WebTester tester;

	private void loginTest(String username, String password) {
		tester.beginAt("login.jsp");
		tester.assertTitleEquals("Fans de las aves chilenas (SWSEC Intro)");
		tester.setTextField("username", username);
		tester.setTextField("password", password);
		tester.submit();

		tester.assertTextPresent("Usuario: " + username);
		tester.clickLink("exit");

		tester.assertTextNotPresent("Usuario: " + username);
		tester.assertTextPresent("Login");
	}

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://127.0.0.1:8080/");
	}

	@Test
	public void userTest1() {
		loginTest(TestConfig.DEFAULT_USER, TestConfig.DEFAULT_PASSWORD);
	}

	@Test
	public void userTest2() {
		loginTest("basaber", "12345");
	}

	@Test
	public void userTest3() {
		loginTest("skramer", "power");
	}

	@Test
	public void userTest4() {
		loginTest("aeinstein", "simple");
	}

	@Test
	public void userTest5() {
		loginTest("tstark", "ironman");
	}

	@Test
	public void userTest6() {
		loginTest("cpalma", "lepego");
	}

	@Test
	public void userTest7() {
		loginTest("asavage", "boom");
	}

	@Test
	public void userTest8() {
		loginTest("jhyneman", "boom");
	}

	@Test
	public void userTest9() {
		loginTest("tanderson", "matrix");
	}

	@Test
	public void userTest10() {
		loginTest("zcool", "god");
	}

	@Test
	public void dirtyDatabaseTest() {
		tester.beginAt("login.jsp");
		tester.setTextField("username", TestConfig.DEFAULT_USER);
		tester.setTextField("password", TestConfig.DEFAULT_PASSWORD);
		tester.submit();
		try {
			tester.clickLink("wall");
		} catch (Exception e) {
			tester.assertResetButtonNotPresent();
		}
		tester.assertElementNotPresentByXPath("//script");
		tester.clickLink("exit");
	}
}
