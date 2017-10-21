package android.tech.mvvm.domain

import io.reactivex.Completable

class AddNoteUseCase(private val localNoteRepository: LocalNoteRepository, private val syncNotetUseCase: SyncNoteUseCase) {

    fun addNote(title: String, desc: String): Completable {
        return localNoteRepository.add(desc = desc, title = title)
                .flatMapCompletable(syncNotetUseCase::syncNote)
    }
}
