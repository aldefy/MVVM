package android.tech.mvvm.domain.services.jobs

import com.birbit.android.jobqueue.JobManager
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService

class SchedulerJobService : FrameworkJobSchedulerService() {

    override fun getJobManager(): JobManager {
        return JobManagerFactory.jobManager!!
    }
}
