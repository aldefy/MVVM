package android.tech.mvvm.domain

import android.arch.lifecycle.LiveData
import android.tech.mvvm.data.db.dao.NoteDao
import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.data.remote.RemoteNotesApi
import android.tech.mvvm.domain.services.networking.RemoteException
import android.tech.mvvm.helpers.rx.AppRxSchedulers
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import java.util.*

class NotesRepository(var noteDao: NoteDao, var api: RemoteNotesApi, var rxSchedulers: AppRxSchedulers) {


    /**
     * Fetching notes from DB
     */
    fun loadNotes(): LiveData<List<Note>> {
        return noteDao.getNotes()
    }

    /**
     * Add new note to DB first
     */
    fun addNote() {
        //adding a dummy note
        noteDao.insert(Note("New note at " + Date().toString(), "Description for new note"))
    }

    /**
     * Fetch single note from DB
     * where @param id is the id saved in DB
     */
    fun getNote(id: Long): LiveData<Note> {
        return noteDao.getNote(id)
    }

    /**
     * Sync any new note added to remote
     */
    fun sync() {

    }

    fun deleteNote(note: Note) {
        deleteNoteFromDB(note = note)
        deleteNoteFromRemote(note = note)
    }

    /**
     * Delete note from remote DB
     */
    private fun deleteNoteFromRemote(note: Note) {
        val jsonReq = JSONObject()
        jsonReq.put("noteTitle", note.noteTitle)
        api.deleteNote(jsonReq)
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(object : Observer<Response<JSONObject>> {
                    override fun onNext(t: Response<JSONObject>?) {
                        if (t == null || !t.isSuccessful || t.errorBody() != null) {
                            Timber.d("Note deleted")
                            throw RemoteException(t!!)
                        }
                        Timber.d("Note deleted from remote db")
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable?) {
                    }

                })
    }

    /**
     * Delete note from DB
     */
    private fun deleteNoteFromDB(note: Note) {
        noteDao.delete(note = note)
    }

}
