package khome.route.selenium.oray.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import khome.route.selenium.oray.OrayService;

public class OrayServiceImpl implements OrayService {

	private WebDriver driver;
	private String baseUrl;
	private String domainUrl;

	private static OrayServiceImpl INSTANCE;

	public static synchronized OrayServiceImpl getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new OrayServiceImpl();
		}
		return INSTANCE;
	}

	public OrayServiceImpl() {
		driver = new FirefoxDriver();
		baseUrl = "http://www.oray.com";
		domainUrl = "https://console.oray.com/domain/root/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Override
	public void orayFlushDNS() {
		try {
			driver.get(baseUrl + "/");
			driver.findElement(By.linkText("登录")).click();
			driver.findElement(By.id("account")).clear();
			driver.findElement(By.id("account")).sendKeys("yuanzh0623");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("yzh0623");
			driver.findElement(By.cssSelector("input.btn-red")).click();
			Thread.sleep(3000);

			driver.get(domainUrl);
			Thread.sleep(1000);

			driver.findElement(By.linkText("壳域名")).click();
			Thread.sleep(1000);

			driver.findElement(By.linkText("khome.iok.la")).click();

			driver.quit();
		} catch (Exception e) {

		}

	}

}
