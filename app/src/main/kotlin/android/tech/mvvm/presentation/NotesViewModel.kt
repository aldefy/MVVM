package android.tech.mvvm.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.domain.AddNoteUseCase
import android.tech.mvvm.domain.GetNotesUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NotesViewModel(private val getNotesUseCase: GetNotesUseCase,
                     private val addNoteUseCase: AddNoteUseCase) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val commentsLiveData = MutableLiveData<List<Note>>()

    init {
        loadNotes()
    }

    override fun onCleared() {
        disposables.clear()
    }

    /**
     * Adds new note
     */
    fun addNote(title: String, desc: String) {
        disposables.add(addNoteUseCase.addNote(title, desc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Timber.d("add note success") },
                        { t -> Timber.e(t, "add note error") }))
    }

    /**
     * Exposes the latest notes so the UI can observe it
     */
    fun notes(): LiveData<List<Note>> {
        return commentsLiveData
    }

    internal fun loadNotes() {
        disposables.add(getNotesUseCase.notes
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ value: List<Note> -> commentsLiveData.setValue(value) },
                        { t: Throwable -> Timber.e(t, "get notes error") }))
    }
}
