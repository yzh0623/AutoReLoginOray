package khome.route.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import khome.route.service.OptService;
import khome.route.service.impl.OptServiceImpl;

public class ReLoginJob implements Job {

	/**
	 * 执行公网ip检查
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		OptService optService = OptServiceImpl.getInstance();
		optService.checkPublicIp();
	}

}
