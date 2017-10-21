package android.tech.mvvm.domain.services.jobs

import com.birbit.android.jobqueue.JobManager
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService

class GcmJobService : GcmJobSchedulerService() {
    override fun getJobManager(): JobManager {
        return JobManagerFactory.jobManager!!
    }
}
