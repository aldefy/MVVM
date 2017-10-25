package android.tech.mvvm.feature.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.tech.mvvm.data.db.entities.Note
import android.tech.mvvm.domain.NotesRepository
import javax.inject.Inject

class NotesDetailsViewModel( var id: Long) : ViewModel() {

    @Inject
    lateinit var notesRepo: NotesRepository
    lateinit var selectedNote: LiveData<Note>

    init {
        initSelectedNote()
    }

    private fun initSelectedNote() {
        selectedNote = notesRepo.getNote(id = id)
    }

    fun deleteNote() {
        notesRepo.deleteNote(selectedNote.value!!)
    }

}
