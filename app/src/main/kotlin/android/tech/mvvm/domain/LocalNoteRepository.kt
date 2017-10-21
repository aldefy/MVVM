package android.tech.mvvm.domain

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalNoteRepository {
    abstract fun add(desc: String, title: String): Single<Note>
    abstract fun update(note: Note): Completable
    abstract fun delete(note: Note): Completable
    abstract fun getNotes(): Flowable<List<Note>>
}
