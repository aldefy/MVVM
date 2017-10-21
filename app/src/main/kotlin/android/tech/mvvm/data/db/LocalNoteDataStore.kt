package android.tech.mvvm.data.db

import android.tech.mvvm.data.db.dao.NoteDao
import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.domain.LocalNoteRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import java.util.*

class LocalNoteDataStore(private var noteDao: NoteDao) : LocalNoteRepository {
    override fun getNotes(): Flowable<List<Note>> {
        Timber.d("getting notes")
        return noteDao.getNotes()
    }

    /**
     * Creates new note entry in local DB
     */
    override fun add(desc: String, title: String): Single<Note> {
        Timber.d("Creating note for title -  %s and desc - %s", title, desc)
        val note = Note(title, desc)
        note.noteId = Date().time
        return Single.fromCallable {
            val rowId = noteDao.insert(note)
            Timber.d("note stored " + rowId)
            note.noteId = rowId
            note
        }

    }

    /**
     * Updates Note object in local DB
     */
    override fun update(note: Note): Completable {
        Timber.d("updating note sync status for note id %s", note.noteId)
        return Completable.fromAction { noteDao.update(note) }
    }

    /**
     * Deletes note from local DB
     */
    override fun delete(note: Note): Completable {
        Timber.d("deleting note with id %s", note.noteId)
        return Completable.fromAction { noteDao.delete(note) }
    }

}
