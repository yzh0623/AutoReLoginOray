package khome.route.selenium.tplink.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import khome.route.selenium.tplink.TPLinkService;

public class TPLinkServiceImpl implements TPLinkService {

	private WebDriver driver;
	private String baseUrl;

	private static TPLinkServiceImpl INSTANCE;

	public static synchronized TPLinkServiceImpl getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new TPLinkServiceImpl();
		}
		return INSTANCE;
	}

	public TPLinkServiceImpl() {
		driver = new FirefoxDriver();
		baseUrl = "http://192.168.168.1";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Override
	public void autoRelogin() {
		try {
			driver.get(baseUrl + "/");
			driver.findElement(By.id("lgPwd")).click();
			driver.findElement(By.id("lgPwd")).clear();
			driver.findElement(By.id("lgPwd")).sendKeys("yuanzhenhui0623");
			driver.findElement(By.id("loginSub")).click();

			driver.findElement(By.cssSelector("#appsMgtMbtn > h2")).click();
			driver.findElement(
					By.cssSelector("#network_rsMenu > label.menuLbl")).click();

			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('appsRCon').style='display: block'");
			((JavascriptExecutor) driver).executeScript(
					"document.getElementById('bConFun').style='display: none'");

			driver.findElement(By.xpath("(//input[@value='进 入'])[10]")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("ddnsLogout")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("ddnsLogin")).click();
			Thread.sleep(3000);
			driver.quit();
		} catch (Exception e) {
		}
	}

}
