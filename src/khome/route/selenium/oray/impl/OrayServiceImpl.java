package khome.route.selenium.oray.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import khome.route.selenium.oray.OrayService;

public class OrayServiceImpl implements OrayService {

	private WebDriver driver;
	private String domainUrl;

	private static OrayServiceImpl INSTANCE;

	// 单例生成对象
	public static synchronized OrayServiceImpl getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new OrayServiceImpl();
		}
		return INSTANCE;
	}

	// 构造函数初始化变量
	public OrayServiceImpl() {
		domainUrl = "https://console.oray.com/domain/root/";
	}

	// 使用selenium执行刷新花生壳dns操作
	@Override
	public void orayFlushDNS() throws Exception {

		driver = new FirefoxDriver();
		// 最大化窗口，使selenium能够更好地操作
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// 重定向页面
		driver.get(domainUrl);

		// 输入用户名
		driver.findElement(By.id("account")).clear();
		driver.findElement(By.id("account")).sendKeys("yuanzh0623");
		// 输入密码
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("yzh0623");
		// 跳转页面
		driver.findElement(By.cssSelector("input.btn-red")).click();
		Thread.sleep(3000);

		// 选择壳域名
		driver.findElement(By.linkText("壳域名")).click();
		Thread.sleep(1000);
		// 选择khome.iok.la地址手工刷新dns
		driver.findElement(By.linkText("khome.iok.la")).click();
		Thread.sleep(3000);

		driver.quit();
	}
}
