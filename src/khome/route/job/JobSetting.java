package khome.route.job;

import java.util.HashMap;
import java.util.Map;

import khome.route.job.util.JobUtil;

public class JobSetting {

	/**
	 * 开始执行重启线程
	 */
	public void startCheck4ReLogin() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("type", "cron");
		// 每5分钟执行一次
		paramMap.put("cron", "00 0/5 00 * * ?");
		try {
			JobUtil.initJob(Check2ReLogJob.class, "Check2ReLogJob", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 每天凌晨4点会执行一次重启（这个是肯定要做的）
	 */
	public void reLoginInEarlymorning() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("type", "cron");
		paramMap.put("cron", "00 00 04 * * ?");
		try {
			JobUtil.initJob(ReLoginJob.class, "ReLoginJob", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
