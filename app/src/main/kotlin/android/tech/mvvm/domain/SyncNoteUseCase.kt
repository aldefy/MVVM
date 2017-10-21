package android.tech.mvvm.domain

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Completable

/**
 * Responsible for syncing a Note with remote repository.
 */
class SyncNoteUseCase(private val remoteNoteRepository: RemoteNoteRepository) {

    fun syncNote(note: Note): Completable {
        return remoteNoteRepository.sync(note)
    }
}
