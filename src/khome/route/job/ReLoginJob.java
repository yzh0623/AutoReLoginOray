package khome.route.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import khome.route.service.OptService;
import khome.route.service.impl.OptServiceImpl;

public class ReLoginJob implements Job {

	/**
	 * 执行重登录操作
	 */
	@Override
	public void execute(JobExecutionContext arg0) {
		OptService optService = OptServiceImpl.getInstance();
		try {
			optService.seleniumOpt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
