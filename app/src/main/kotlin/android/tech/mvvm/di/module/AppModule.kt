package android.tech.mvvm.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.tech.mvvm.MainApp
import android.tech.mvvm.data.db.LocalNoteDataStore
import android.tech.mvvm.data.db.NoteDatabase
import android.tech.mvvm.data.db.dao.NoteDao
import android.tech.mvvm.data.remote.RemoteNoteDataStore
import android.tech.mvvm.domain.LocalNoteRepository
import android.tech.mvvm.domain.RemoteNoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


// Add application level bindings here, e.g.: RestClientApi, Repository, etc.
@Module
class AppModule {


    @Provides
    fun provideContext(application: MainApp): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideDB(context: Context): NoteDatabase = Room.databaseBuilder(context, NoteDatabase::class.java, "mvvm-note-kotlin").build()

    @Provides
    @Singleton
    fun provideNotesDao(database: NoteDatabase): NoteDao = database.notesDao()

    @Singleton
    @Provides
    fun provideLocalNotesRepository(noteDao: NoteDao): LocalNoteRepository {
        return LocalNoteDataStore(noteDao)
    }

    @Singleton
    @Provides
    fun provideRemoteNotesRepository(): RemoteNoteRepository {
        return RemoteNoteDataStore()
    }

}
