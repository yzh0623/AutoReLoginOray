package khome.route.tplink;

import org.junit.Test;

import khome.route.selenium.tplink.TPLinkService;
import khome.route.selenium.tplink.impl.TPLinkServiceImpl;

public class TPLinkServiceTest {

	private TPLinkService tPLinkService;

	@Test
	public void testAutoRelogin() {
		tPLinkService = TPLinkServiceImpl.getInstance();
		tPLinkService.autoRelogin();
	}

}
