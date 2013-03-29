package injcristianrojas;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Backuptest {

	private String startUrl = "http://127.0.0.1:8080/login.jsp";
	private WebClient webClient;

	@Before
	public void setUp() throws Exception {
		webClient = new WebClient();
	}

	@After
	public void tearDown() throws Exception {
		webClient.closeAllWindows();
	}

	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		System.out.println("Probando web en " + startUrl);
		HtmlPage page1 = webClient.getPage(startUrl);
		Assert.assertEquals("Fans de los bellos paisajes (SWSEC Intro)", page1.getTitleText());

		HtmlForm form = page1.getFormByName("mainForm");
		HtmlTextInput usernameField = form.getInputByName("username");
		HtmlPasswordInput passwordField = form.getInputByName("password");
		HtmlSubmitInput submitButton = form.getInputByName("submitButton");
		usernameField.setValueAttribute("crirojas");
		passwordField.setValueAttribute("123");

		HtmlPage page2 = submitButton.click();
		Assert.assertTrue(page2.asXml().contains("Usuario: crirojas"));

		HtmlPage page3 = page2.getHtmlElementById("exit").click();
		Assert.assertTrue(!page3.asXml().contains("Usuario: crirojas"));
		Assert.assertTrue(page3.asXml().contains("Fans de los bellos paisajes"));
	}

}
