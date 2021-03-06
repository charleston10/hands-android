package charleston.androidkotlinproject.extensions

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator


/**
 * Created by charleston.anjos on 03/10/17.
 */
fun View.show() {
    alpha = 0.0f
    visibility = View.VISIBLE
    animate().setDuration(700).alpha(1.00f).start()
}

fun String.generateImage(): Drawable {
    val generator = ColorGenerator.MATERIAL
    val randomColor = generator.randomColor

    return TextDrawable.builder()
            .beginConfig()
            .textColor(Color.WHITE)
            .useFont(Typeface.DEFAULT)
            .fontSize(50) /* size in px */
            .bold()
            .toUpperCase()
            .endConfig()
            .buildRoundRect(this, randomColor, 15)
}

fun View.isVisible(boolean: Boolean) {
    visibility = if (boolean) View.VISIBLE else View.GONE
}