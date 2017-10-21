package android.tech.mvvm.domain

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Completable

class DeleteNoteUseCase(private val localNoteRepository: LocalNoteRepository) {

    fun deleteNote(note: Note): Completable {
        return localNoteRepository.delete(note)
    }
}
