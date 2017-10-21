package android.tech.mvvm.domain

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Flowable

class GetNotesUseCase(private val localNoteRepository: LocalNoteRepository) {

    val notes: Flowable<List<Note>>
        get() = localNoteRepository.getNotes()
}
