package android.tech.mvvm.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.tech.mvvm.MainApp
import android.tech.mvvm.data.db.NoteDatabase
import android.tech.mvvm.data.db.dao.NoteDao
import android.tech.mvvm.data.remote.RemoteNotesApi
import android.tech.mvvm.domain.NotesRepository
import android.tech.mvvm.helpers.rx.AppRxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


// Add application level bindings here, e.g.: RestClientApi, Repository, etc.
@Module(includes = arrayOf(ViewModelModule::class))
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
    fun provideNotesRepository(noteDao: NoteDao, api: RemoteNotesApi, rxSchedulers: AppRxSchedulers): NotesRepository {
        return NotesRepository(noteDao = noteDao, api = api, rxSchedulers = rxSchedulers)
    }

}
