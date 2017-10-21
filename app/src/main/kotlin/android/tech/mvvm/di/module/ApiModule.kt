package android.tech.mvvm.di.module

import android.tech.mvvm.data.remote.RemoteNotesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {
    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "http://192.168.1.4:3000/"
    }

    @Provides
    @Singleton
    fun provideAPI(@Named("retrofit") retrofit: Retrofit): RemoteNotesApi {
        return retrofit.create(RemoteNotesApi::class.java)
    }
}
