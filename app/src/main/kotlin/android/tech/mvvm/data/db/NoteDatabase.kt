package android.tech.mvvm.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.tech.mvvm.data.db.dao.NoteDao
import android.tech.mvvm.data.db.entities.Note

@Database(
        entities = arrayOf(Note::class),
        version = 2,
        exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun notesDao(): NoteDao
}
