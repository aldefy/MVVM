package android.tech.mvvm.data.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.tech.mvvm.data.db.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE _id = :arg0 LIMIT 1")
    fun getNote(arg0: Long): LiveData<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)


    @Query("DELETE FROM notes")
    fun nukeTable()
}
