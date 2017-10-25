package android.tech.mvvm.data.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "notes")
data class Note(
        @field:SerializedName("noteTitle")
        @ColumnInfo(name = "title")
        var noteTitle: String,
        @field:SerializedName("noteDesc")
        @ColumnInfo(name = "desc")
        var noteDesc: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("_id")
    @ColumnInfo(name = "_id")
    var noteId: Long = -1

    constructor() : this("", "")
}
