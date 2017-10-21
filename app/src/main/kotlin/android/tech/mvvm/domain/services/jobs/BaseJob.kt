package android.tech.mvvm.domain.services.jobs

import android.tech.mvvm.data.remote.RemoteNotesApi
import android.tech.mvvm.helpers.rx.AppRxSchedulers
import com.birbit.android.jobqueue.Job
import com.birbit.android.jobqueue.Params
import javax.inject.Inject


abstract class BaseJob protected constructor(params: Params) : Job(params) {
    @Inject lateinit var remoteService: RemoteNotesApi
    @Inject lateinit var rxSchedulers: AppRxSchedulers
}
