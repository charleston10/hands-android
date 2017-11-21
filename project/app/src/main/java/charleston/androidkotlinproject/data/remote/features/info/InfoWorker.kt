package charleston.androidkotlinproject.data.remote.features.info

import charleston.androidkotlinproject.data.domain.Info
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by charleston.anjos on 03/10/17.
 */
interface InfoWorker {

    @GET(InfoEndPoint.FIND_ALL)
    fun findAll(): Observable<List<Info>>

    @GET(InfoEndPoint.FIND_BY_ID)
    fun findAById(@Path("id") id: String): Observable<Info>
}