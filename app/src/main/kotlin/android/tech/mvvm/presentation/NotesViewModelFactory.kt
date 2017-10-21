package android.tech.mvvm.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.tech.mvvm.domain.AddNoteUseCase
import android.tech.mvvm.domain.GetNotesUseCase

class NotesViewModelFactory(private val getNotesUseCase: GetNotesUseCase,
                            private val addNoteUseCase: AddNoteUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(getNotesUseCase, addNoteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
