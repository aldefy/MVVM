package android.tech.mvvm.domain.services.jobs

import android.content.Context
import android.os.Build
import com.birbit.android.jobqueue.JobManager
import com.birbit.android.jobqueue.config.Configuration
import com.birbit.android.jobqueue.di.DependencyInjector
import com.birbit.android.jobqueue.log.CustomLogger
import com.birbit.android.jobqueue.scheduling.FrameworkJobSchedulerService
import com.birbit.android.jobqueue.scheduling.GcmJobSchedulerService
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import timber.log.Timber

object JobManagerFactory {

    @get:Synchronized
    var jobManager: JobManager? = null
        private set

    private val customLogger = object : CustomLogger {

        override fun isDebugEnabled(): Boolean {
            return true
        }

        override fun d(text: String, vararg args: Any) {
            Timber.d(String.format(text, *args))
        }

        override fun e(t: Throwable, text: String, vararg args: Any) {
            Timber.e(t, String.format(text, *args))
        }

        override fun e(text: String, vararg args: Any) {
            Timber.e(String.format(text, *args))
        }

        override fun v(text: String, vararg args: Any) {
            // no-op
        }
    }

    @Synchronized
    fun getJobManager(context: Context): JobManager {
        if (jobManager == null) {
            jobManager = configureJobManager(context)
        }
        return jobManager as JobManager
    }

    @Synchronized
    fun getJobManager(context: Context, dependencyInjector: DependencyInjector): JobManager {
        if (jobManager == null) {
            jobManager = configureJobManager(context, dependencyInjector)
        }
        return jobManager as JobManager
    }

    private fun configureJobManager(context: Context): JobManager {
        val builder = Configuration.Builder(context)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minutes
                .customLogger(customLogger)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(context,
                    SchedulerJobService::class.java), true)
        } else {
            val enableGcm = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
            if (enableGcm == ConnectionResult.SUCCESS) {
                builder.scheduler(GcmJobSchedulerService.createSchedulerFor(context,
                        GcmJobSchedulerService::class.java), true)
            }
        }
        return JobManager(builder.build())
    }

    private fun configureJobManager(context: Context, dependencyInjector: DependencyInjector): JobManager {
        val builder = Configuration.Builder(context)
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minutes
                .customLogger(customLogger)
                .injector(dependencyInjector)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.scheduler(FrameworkJobSchedulerService.createSchedulerFor(context,
                    SchedulerJobService::class.java), true)
        } else {
            val enableGcm = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)
            if (enableGcm == ConnectionResult.SUCCESS) {
                builder.scheduler(GcmJobSchedulerService.createSchedulerFor(context,
                        GcmJobSchedulerService::class.java), true)
            }
        }
        return JobManager(builder.build())
    }
}
