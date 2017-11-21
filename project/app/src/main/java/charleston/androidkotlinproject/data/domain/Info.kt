package charleston.androidkotlinproject.data.domain

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by charleston on 20/11/17.
 *
 * 'euid','deviceId','os','osVersion','deviceModel','home',
 * 'work','istInstalledApps','batteryState','batteryPercentage','arrival',
 * 'departure','categorie','venueName','venueTotalTime','precision','venueLngLat',
 * 'address','city','state','country'
 */
data class Info(
        @SerializedName("_id") var id: String,
        var euid: String,
        var deviceId: String,
        var os: String,
        var osVersion: String,
        var deviceModel: String,
        var home: Locale,
        var work: Locale,
        var istInstalledApps: List<String>,
        var batteryState: String,
        var batteryPercentage: Int,
        var arrival: Date,
        var departure: Date,
        @SerializedName("categorie") var category: String,
        var venueName: String,
        var venueTotalTime: String,
        var precision: String,
        var venueLngLat: Locale,
        var address: String,
        var city: String,
        var state: String,
        var country: String
) {
    fun apps(): String {
        val stringBuilder = StringBuilder()

        if (istInstalledApps.isNotEmpty()) {

            var count = 0
            for (app: String in istInstalledApps) {
                stringBuilder.append(app)

                count++
                if (count < istInstalledApps.size) stringBuilder.append(", ")
            }
        } else {
            stringBuilder.append("Não há aplicativos instalados")
        }

        return stringBuilder.toString()
    }
}