package charleston.androidkotlinproject.extensions

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import charleston.androidkotlinproject.R

/**
 * Created by charleston.anjos on 03/10/17.
 */
fun Toolbar.create(activity: AppCompatActivity, title: String, showBack: Boolean, backPressListener: View.OnClickListener?) {
    val context: Context = activity

    this.title = title

    setBackgroundColor(context.color(R.color.white))
    setTitleTextColor(context.color(R.color.colorSecondaryText))

    activity.setSupportActionBar(this)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
    activity.supportActionBar?.setDisplayShowHomeEnabled(showBack)

    if (backPressListener != null) {
        setNavigationOnClickListener(backPressListener)
    } else if (showBack) {
        setNavigationOnClickListener({ activity.onBackPressed() })
    }
}

fun Toolbar.create(activity: AppCompatActivity, title: String, showBack: Boolean) {
    this.create(activity, title, showBack, null)
}