package charleston.androidkotlinproject.data.domain

import java.io.Serializable

/**
 * Created by charleston on 20/11/17.
 */
data class Coordinates(
        var x: Long,
        var y: Long
) : Serializable