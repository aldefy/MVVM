package android.tech.mvvm.domain

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Completable

interface RemoteNoteRepository {
    abstract fun sync(note: Note): Completable
}
