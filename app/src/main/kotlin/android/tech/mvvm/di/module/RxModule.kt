package android.tech.mvvm.di.module

import dagger.Module
import dagger.Provides
import android.tech.mvvm.helpers.rx.AppRxSchedulers

/**
 * Provides Schedulers for Observables , Subjects etc
 */
@Module
class RxModule {

    @Provides
    fun provideRxSchedulers(): AppRxSchedulers {
        return AppRxSchedulers()
    }
}
