package khome.route.job.util;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobUtil {

	private JobUtil() {

	}

	/**
	 * 初始化定时器
	 * 
	 * @param clazz
	 * @param jobName
	 * @param paramMap
	 * @throws Exception
	 */
	public static void initJob(Class<? extends Job> clazz, String jobName,
			Map<String, String> paramMap) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail jobDetail = JobBuilder.newJob(clazz)
				.withIdentity("Auto_Job_" + jobName, "Auto_Group_" + jobName)
				.build();

		String type = paramMap.get("type");
		if ("cron".equals(type)) {
			String expression = paramMap.get("cron");

			CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
					.withIdentity("Auto_Trigger_" + jobName,
							"Auto_Group_" + jobName)
					.withSchedule(CronScheduleBuilder.cronSchedule(expression))
					.build();
			sched.scheduleJob(jobDetail, cronTrigger);

		} else {
			String simple = paramMap.get("simple");
			String[] ops = simple.split(",");

			SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
					.withIdentity("Auto_Trigger_" + jobName,
							"Auto_Group_" + jobName)
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(Integer.parseInt(ops[0])) // 时间间隔
							.withRepeatCount(Integer.parseInt(ops[1])) // 重复次数
					).build();
			sched.scheduleJob(jobDetail, simpleTrigger);
		}
		sched.start();
	}

}
