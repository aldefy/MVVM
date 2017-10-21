package android.tech.mvvm.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import timber.log.BuildConfig
import timber.log.Timber
import java.util.regex.Pattern

fun Int.isOdd() = this.mod(2) == 1

fun Int.isEven() = this.mod(2) == 0

fun Int.format(digits: Int) = String.format("%0${digits}d%n", this)

fun Boolean.asInt(): Int {
    return if (this) 1 else 0
}

fun Int.asBoolean(): Boolean {
    return (this == 1)
}

inline fun supportsLollipop(func: () -> Unit) =
        supportsVersion(Build.VERSION_CODES.LOLLIPOP, func)

inline fun supportsVersion(ver: Int, func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= ver) {
        func.invoke()
    }
}

inline fun inReleaseMode(func: () -> Unit) {
    if (BuildConfig.BUILD_TYPE == "release") {
        func()
    }
}

inline fun inDebugMode(func: () -> Unit) {
    if (BuildConfig.BUILD_TYPE == "debug") {
        func()
    }
}


fun logException(e: Throwable) = if (BuildConfig.DEBUG) {
    Timber.e(e)
} else {

}

fun Any.debug(message: String) {
    Timber.tag(this::class.java.simpleName).d(message)
}

fun Any.debug(message: String, tr: Throwable) {
    Timber.tag(this::class.java.simpleName).d(tr, message)
}


@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

val EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$"
// used for validate if the current String is an email
fun String.isValidEmail(): Boolean {
    val pattern = Pattern.compile(EMAIL_PATTERN)
    return pattern.matcher(this).matches()
}


