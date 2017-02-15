package khome.route.job;

import java.util.HashMap;
import java.util.Map;

import khome.route.job.util.JobUtil;

public class JobSetting {

	public void startReLoginJobThread() {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("type", "cron");
		paramMap.put("cron", "00 00 0/3 * * ?");
		try {
			JobUtil.initJob(ReLoginJob.class, "ReLoginJob", paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
