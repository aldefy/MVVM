package android.tech.mvvm.data.remote

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.*

interface RemoteNotesApi {
    @GET("notes/list")
    fun getNotes(): Observable<Note>

    @POST("notes/")
    @Headers("Content-Type: application/json")
    fun addNote(@Body note: Note): Observable<Response<JSONObject>>

    @DELETE("notes/")
    @Headers("Content-Type: application/json")
    fun deleteNote(@Body jsonObject: JSONObject): Observable<Response<JSONObject>>
}
