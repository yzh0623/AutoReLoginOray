package khome.route.oray;

import org.junit.Test;

import khome.route.selenium.oray.OrayService;
import khome.route.selenium.oray.impl.OrayServiceImpl;

public class OrayServiceTest {

	private OrayService orayService;

	@Test
	public void testOrayFlushDNS() {
		orayService = OrayServiceImpl.getInstance();
		orayService.orayFlushDNS();
	}

}
