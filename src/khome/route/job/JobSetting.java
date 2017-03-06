package khome.route.job;

import java.util.HashMap;
import java.util.Map;

import khome.route.job.util.JobUtil;

public class JobSetting {

	/**
	 * 开始执行重启线程
	 */
	public void startReLoginJobThread() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("type", "cron");
		// 每两分钟执行一次
		paramMap.put("cron", "00 0/5 00 * * ?");
		try {
			JobUtil.initJob(ReLoginJob.class, "ReLoginJob", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
