package android.tech.mvvm.di.component

import android.tech.mvvm.MainApp
import android.tech.mvvm.data.remote.RemoteNotesApi
import android.tech.mvvm.di.module.*
import android.tech.mvvm.domain.services.jobs.BaseJob
import android.tech.mvvm.helpers.rx.AppRxSchedulers
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 *
 */
@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        UIBuilder::class,
        // ViewModelModule::class,
        NetworkModule::class,
        RxModule::class,
        ApiModule::class
        ))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MainApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: MainApp)
  //  fun inject(notesActivity: NotesActivity)
   // fun inject(detailActivity: NotesDetailActivity)
    fun inject(job: BaseJob)

    fun api(): RemoteNotesApi
    fun rxSchedulers(): AppRxSchedulers
}
