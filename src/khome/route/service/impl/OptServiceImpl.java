package khome.route.service.impl;

import org.apache.log4j.Logger;

import khome.route.selenium.oray.OrayService;
import khome.route.selenium.oray.impl.OrayServiceImpl;
import khome.route.selenium.tplink.TPLinkService;
import khome.route.selenium.tplink.impl.TPLinkServiceImpl;
import khome.route.service.OptService;
import khome.route.util.IpUtil;

public class OptServiceImpl implements OptService {

	private static final Logger LOG = Logger.getLogger(OptServiceImpl.class);

	private static OptServiceImpl INSTANCE;

	public static synchronized OptServiceImpl getInstance() {
		if (null == INSTANCE) {
			INSTANCE = new OptServiceImpl();
		}
		return INSTANCE;
	}

	// selenium操作
	@Override
	public void seleniumOpt() throws Exception {
		LOG.info("tplink路由器DDNS重启开始------------>");

		TPLinkService tPLinkService = TPLinkServiceImpl.getInstance();
		tPLinkService.autoRelogin();

		LOG.info("tplink路由器DDNS重启结束------------>");

		// 屏蔽登录花生壳手工操作步骤
		LOG.info("花生壳登录开始------------>");

		OrayService orayService = OrayServiceImpl.getInstance();
		orayService.orayFlushDNS();

		LOG.info("花生壳登录结束------------>");
	}

	// 判断当前公网ip是否存在变动
	@Override
	public void checkPublicIp() throws Exception {
		LOG.info("IP判断开始 ---------------->");
		// 通过IpUtil类获取当前公网ip
		String pIp = IpUtil.getPublicIP();
		// 跟系统中缓存的ip进行对比，若发生不等的情况即公网ip发生了变动
		if (!IpUtil.cacheIP.equals(pIp)) {
			LOG.info("得到的公网ip与现有的ip有出入，需要更改");
			// 将变动的ip缓存起来
			IpUtil.cacheIP = pIp;
			// 并重新登录ddns功能
			seleniumOpt();
		}
	}

}
