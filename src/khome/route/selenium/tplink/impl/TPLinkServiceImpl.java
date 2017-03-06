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

	// 单例生成对象
	public static synchronized TPLinkServiceImpl getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new TPLinkServiceImpl();
		}
		return INSTANCE;
	}

	// 构造函数中初始化变量
	public TPLinkServiceImpl() {
		driver = new FirefoxDriver();
		baseUrl = "http://192.168.168.1";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// 路由器ddns重新登录
	@Override
	public void autoRelogin() {
		try {
			// 打开路由器
			driver.get(baseUrl + "/");
			driver.findElement(By.id("lgPwd")).click();
			driver.findElement(By.id("lgPwd")).clear();
			// 输入路由器登录密码
			driver.findElement(By.id("lgPwd")).sendKeys("yuanzhenhui0623");
			driver.findElement(By.id("loginSub")).click();

			Thread.sleep(3000);

			// 找到对应页面
			driver.findElement(By.cssSelector("#appsMgtMbtn > h2")).click();
			driver.findElement(By.cssSelector("#network_rsMenu > label.menuLbl")).click();

			// 使用javascript辅助功能改变页面结构，将原来掩盖的链接展示出来
			// selenium若页面没有展示到的元素，即使你知道存在也无法对其经行操作，因此用javascript来改变当前页面展示内容
			((JavascriptExecutor) driver).executeScript("document.getElementById('appsRCon').style='display: block'");
			((JavascriptExecutor) driver).executeScript("document.getElementById('bConFun').style='display: none'");

			driver.findElement(By.xpath("(//input[@value='进 入'])[10]")).click();
			Thread.sleep(3000);
			// 重登录
			driver.findElement(By.id("ddnsLogout")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("ddnsLogin")).click();
			Thread.sleep(3000);
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
