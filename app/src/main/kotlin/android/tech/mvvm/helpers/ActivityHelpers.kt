package android.tech.mvvm.helpers

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Activity.setStatusBarColor(colorResId: Int) {
    supportsLollipop {
        with(window) {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = resources.getColor(colorResId)
        }
    }
}

fun Activity.screenWidth(): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun Activity.screenHeight(): Int {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}


fun Activity.showOnUiThread(init: Activity.() -> Unit): Activity {
    if (!isFinishing) {
        runOnUiThread {
            init()
        }
    }
    return this
}

// used for show a toast message in the UI Thread
fun Activity.showToast(message: String) {
    showOnUiThread { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
}

// used for simple start activity without Intent parameters
fun Activity.start(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
}

