package android.tech.mvvm

import android.app.Activity
import android.app.Application
import android.content.Context
import android.tech.mvvm.di.component.AppComponent
import android.tech.mvvm.di.component.DaggerAppComponent
import android.tech.mvvm.domain.services.jobs.BaseJob
import android.tech.mvvm.domain.services.jobs.JobManagerFactory
import com.birbit.android.jobqueue.di.DependencyInjector
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject


class MainApp : Application(), HasActivityInjector {
    companion object {
        lateinit var component: AppComponent
        fun applicationContext(): Context {
            return this.applicationContext()
        }

        fun getAppComponent(): AppComponent {
            return component
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
                .builder()
                .application(this)
                .build()
        component.inject(this)
        initJobsManager()
        initLogging()
    }

    private fun initJobsManager() {

        val dependencyInjector = DependencyInjector { job ->
            // this line depends on how your Dagger components are setup;
            // the important part is to cast job to BaseJob
            getAppComponent().inject(job as BaseJob)
        }
        JobManagerFactory.getJobManager(this, dependencyInjector)

    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            val formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(true)
                    .methodOffset(5)
                    .tag("Wellthy")
                    .build()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
            Timber.plant(object : Timber.DebugTree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    Logger.log(priority, tag, message, t)
                }
            })
        }
    }
}
