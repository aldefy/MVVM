package android.tech.mvvm.helpers

import android.view.View

interface RVItemClickListener {
    fun onItemClick(v: View, position: Int)
}
