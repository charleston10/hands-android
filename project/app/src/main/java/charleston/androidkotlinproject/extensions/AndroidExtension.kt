package charleston.androidkotlinproject.extensions

import android.content.Context
import android.os.Build
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatEditText
import android.view.View
import android.widget.EditText
import charleston.androidkotlinproject.R

/**
 * Created by charleston.anjos on 03/10/17.
 */
fun Context.color(@ColorRes colorRes: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getColor(colorRes)
    } else {
        ContextCompat.getColor(this, colorRes)
    }
}

fun Context.versionName(): String {
    return try {
        val manager = packageManager
        val info = manager.getPackageInfo(packageName, 0)
        info.versionName
    } catch (e: Exception) {
        e.printStackTrace()
        "1.0.0"
    }
}

fun Context.formValidate(fields: List<View>): Boolean {
    var isValid = true

    for (v in fields) {
        if (v is EditText) {
            val str = v.text.toString()

            if (str.isEmpty()) {
                v.error = resources.getString(R.string.message_campo_obrigatorio)
                isValid = false
            }
        }
        if (v is AppCompatEditText) {
            val str = v.text.toString()

            if (str.isEmpty()) {
                v.error = resources.getString(R.string.message_campo_obrigatorio)
                isValid = false
            }
        }
    }
    return isValid
}

fun Context.drawable(drawable: Int) = ContextCompat.getDrawable(this, drawable)