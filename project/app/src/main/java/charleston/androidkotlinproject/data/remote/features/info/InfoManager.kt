package charleston.androidkotlinproject.data.remote.features.info

import charleston.androidkotlinproject.data.domain.Info
import io.reactivex.Observable
import retrofit2.Retrofit

/**
 * Created by charleston.anjos on 03/10/17.
 */

class InfoManager(val retrofit: Retrofit) {

    fun findAll(): Observable<ArrayList<Info>> {
        return retrofit.create(InfoWorker::class.java).findAll()
    }

    fun findById(id: String): Observable<Info> {
        return retrofit.create(InfoWorker::class.java).findAById(id)
    }
}