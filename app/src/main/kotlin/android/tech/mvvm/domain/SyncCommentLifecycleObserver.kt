package android.tech.mvvm.domain

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.tech.mvvm.data.db.entities.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Updates local database after remote note sync requests
 */
class SyncNoteLifecycleObserver(private val deleteNoteUseCase: DeleteNoteUseCase) : LifecycleObserver {
    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        Timber.d("onResume lifecycle event.")

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Timber.d("onPause lifecycle event.")
        disposables.clear()
    }


    private fun onSyncNoteSuccess(note: Note) {
        Timber.d("received sync note success event for note %s", note)
    }

    private fun onSyncNoteFailed(note: Note) {
        Timber.d("received sync note failed event for note %s", note)
        disposables.add(deleteNoteUseCase.deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("delete note success") },
                        { t -> Timber.e(t, "delete note error") }))
    }
}
