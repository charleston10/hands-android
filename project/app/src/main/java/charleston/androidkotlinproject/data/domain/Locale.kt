package charleston.androidkotlinproject.data.domain

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

/**
 * Created by charleston on 20/11/17.
 */
data class Locale(
        private var type: String,
        private var coordinates: Array<BigDecimal>
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Locale

        if (type != other.type) return false
        if (!Arrays.equals(coordinates, other.coordinates)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + Arrays.hashCode(coordinates)
        return result
    }
}

