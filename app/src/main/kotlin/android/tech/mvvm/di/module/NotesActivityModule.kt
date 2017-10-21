package android.tech.mvvm.di.module

import android.tech.mvvm.domain.*
import android.tech.mvvm.presentation.NotesViewModelFactory
import dagger.Module
import dagger.Provides


/**
 * Define NotesActivity-specific dependencies here.
 */
@Module
class NotesActivityModule {
    @Provides
    internal fun provideNotesViewModelFactory(getNotesUseCase: GetNotesUseCase,
                                              addNoteUseCase: AddNoteUseCase): NotesViewModelFactory {
        return NotesViewModelFactory(getNotesUseCase, addNoteUseCase)
    }

    @Provides
    internal fun provideSyncNoteLifecycleObserver(deleteNoteUseCase: DeleteNoteUseCase): SyncNoteLifecycleObserver {
        return SyncNoteLifecycleObserver(deleteNoteUseCase)
    }

    @Provides
    internal fun provideAddNoteUseCase(localNoteRepository: LocalNoteRepository, syncNoteUseCase: SyncNoteUseCase): AddNoteUseCase {
        return AddNoteUseCase(localNoteRepository, syncNoteUseCase)
    }

    @Provides
    internal fun provideGetNotesUseCase(localNoteRepository: LocalNoteRepository): GetNotesUseCase {
        return GetNotesUseCase(localNoteRepository)
    }


    @Provides
    internal fun provideDeleteNoteUseCase(localNoteRepository: LocalNoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(localNoteRepository)
    }

    @Provides
    internal fun provideSyncNoteUseCase(remoteNoteRepository: RemoteNoteRepository): SyncNoteUseCase {
        return SyncNoteUseCase(remoteNoteRepository)
    }
}
