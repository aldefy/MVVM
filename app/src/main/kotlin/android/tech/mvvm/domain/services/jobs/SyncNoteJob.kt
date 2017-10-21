package android.tech.mvvm.domain.services.jobs

import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.domain.services.networking.RemoteException
import com.birbit.android.jobqueue.Params
import com.birbit.android.jobqueue.RetryConstraint
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

class SyncNoteJob(private val note: Note) : BaseJob(Params(JobPriority.MID)
        .requireNetwork()
        .groupBy(TAG)
        .persist()) {
    companion object {
        private val TAG = SyncNoteJob::class.java.canonicalName
    }

    override fun onAdded() {
        Timber.d("Executing onAdded() for note " + note)
    }

    @Throws(Throwable::class)
    override fun onRun() {
        Timber.d("Executing onRun() for note " + note)
        remoteService.addNote(note)
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(object : Observer<Response<JSONObject>> {
                    override fun onNext(t: Response<JSONObject>?) {
                        if (t == null || !t.isSuccessful || t.errorBody() != null) {
                            Timber.d("Note  saved")
                            throw RemoteException(t!!)
                        }
                        Timber.d("Note saved on remote db")
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
    }

    override fun onCancel(cancelReason: Int, throwable: Throwable?) {
        Timber.d("canceling job. reason: %d, throwable: %s", cancelReason, throwable)
    }

    override fun shouldReRunOnThrowable(throwable: Throwable, runCount: Int, maxRunCount: Int): RetryConstraint {
        if (throwable is RemoteException) {
            val exception = throwable

            val statusCode = exception.response.code()
            if (statusCode in 400..499) {
                return RetryConstraint.CANCEL
            }
        }
        // if we are here, most likely the connection was lost during job execution
        return RetryConstraint.RETRY
    }

}
