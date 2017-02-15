package khome.route.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import khome.route.selenium.oray.OrayService;
import khome.route.selenium.oray.impl.OrayServiceImpl;
import khome.route.selenium.tplink.TPLinkService;
import khome.route.selenium.tplink.impl.TPLinkServiceImpl;

public class ReLoginJob implements Job {

	/**
	 * 重登录并刷新DNS
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		TPLinkService tPLinkService = TPLinkServiceImpl.getInstance();
		tPLinkService.autoRelogin();

		OrayService orayService = OrayServiceImpl.getInstance();
		orayService.orayFlushDNS();

	}

}
