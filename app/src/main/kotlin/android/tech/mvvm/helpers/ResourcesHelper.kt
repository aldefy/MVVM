package android.tech.mvvm.helpers

import android.content.Context
import android.support.v4.content.ContextCompat


fun Context.color(id: Int): Int = ContextCompat.getColor(this, id)

fun Context.getDrawableCompat(id: Int) = ContextCompat.getDrawable(this, id)
