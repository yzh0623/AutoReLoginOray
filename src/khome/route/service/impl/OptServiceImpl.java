package khome.route.service.impl;

import khome.route.selenium.tplink.TPLinkService;
import khome.route.selenium.tplink.impl.TPLinkServiceImpl;
import khome.route.service.OptService;
import khome.route.util.IpUtil;

public class OptServiceImpl implements OptService {

	private static OptServiceImpl INSTANCE;

	public static synchronized OptServiceImpl getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new OptServiceImpl();
		}
		return INSTANCE;
	}

	// selenium操作
	@Override
	public void seleniumOpt() {
		TPLinkService tPLinkService = TPLinkServiceImpl.getInstance();
		tPLinkService.autoRelogin();

		// 屏蔽登录花生壳手工操作步骤
		// OrayService orayService = OrayServiceImpl.getInstance();
		// orayService.orayFlushDNS();
	}

	// 判断当前公网ip是否存在变动
	@Override
	public void checkPublicIp() {
		try {
			// 通过IpUtil类获取当前公网ip
			String pIp = IpUtil.getPublicIP();
			// 跟系统中缓存的ip进行对比，若发生不等的情况即公网ip发生了变动
			if (!IpUtil.cacheIP.equals(pIp)) {
				// 将变动的ip缓存起来
				IpUtil.cacheIP = pIp;
				// 并重新登录ddns功能
				seleniumOpt();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
