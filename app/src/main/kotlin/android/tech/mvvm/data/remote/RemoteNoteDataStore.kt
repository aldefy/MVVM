package android.tech.mvvm.data.remote

import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.domain.RemoteNoteRepository
import android.tech.mvvm.domain.services.jobs.JobManagerFactory
import android.tech.mvvm.domain.services.jobs.SyncNoteJob
import io.reactivex.Completable

class RemoteNoteDataStore : RemoteNoteRepository {

    override fun sync(note: Note): Completable {
        return Completable.fromAction { JobManagerFactory.jobManager!!.addJobInBackground(SyncNoteJob(note)) }
    }

}
