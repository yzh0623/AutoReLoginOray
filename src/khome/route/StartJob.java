package khome.route;

import khome.route.job.JobSetting;

public class StartJob {

	public static void main(String[] args) {
		JobSetting js = new JobSetting();
		js.startReLoginJobThread();
	}

}
