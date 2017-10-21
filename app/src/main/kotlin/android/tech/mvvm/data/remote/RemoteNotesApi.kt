package android.tech.mvvm.data.remote

import android.tech.mvvm.data.db.entities.Note
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RemoteNotesApi {
    @GET("notes/list")
    fun getNotes(): Observable<Note>

    @POST("notes/add")
    @Headers("Content-Type: application/json")
    fun addNote(@Body note: Note): Observable<Response<JSONObject>>
}
