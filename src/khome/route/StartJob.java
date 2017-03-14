package khome.route;

import org.apache.log4j.Logger;

import khome.route.job.JobSetting;

public class StartJob {

	private static final Logger LOG = Logger.getLogger(StartJob.class);

	public static void main(String[] args) {
		LOG.info("定时器启动开始------------------->");

		JobSetting js = new JobSetting();

		// 定时器每隔5分钟执行一次
		js.startCheck4ReLogin();
		// 凌晨的时候必定会做一次的重启
		js.reLoginInEarlymorning();

		LOG.info("定时器启动结束------------------->");
	}

}
