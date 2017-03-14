package khome.route.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import khome.route.service.OptService;
import khome.route.service.impl.OptServiceImpl;

public class Check2ReLogJob implements Job {

	/**
	 * 执行公网ip检查
	 */
	@Override
	public void execute(JobExecutionContext arg0) {
		OptService optService = OptServiceImpl.getInstance();
		try {
			optService.checkPublicIp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}