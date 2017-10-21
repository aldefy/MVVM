package android.tech.mvvm.data.db.dao

import android.arch.persistence.room.*
import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Flowable

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getNotes(): Flowable<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)


    @Query("DELETE FROM notes")
    fun nukeTable()
}
