package android.tech.mvvm.feature.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.domain.NotesRepository
import javax.inject.Inject


class NotesViewModel @Inject constructor(var notesRepo: NotesRepository) : ViewModel() {

    private var selectedNote: MutableLiveData<Note>? = null
    lateinit var notesList: LiveData<List<Note>>

    init {
        notesList = getNotes()
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesRepo.loadNotes()
    }

    fun addNote() {
        return notesRepo.addNote()
    }

}
