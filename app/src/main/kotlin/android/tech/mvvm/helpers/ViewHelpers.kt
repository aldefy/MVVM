package android.tech.mvvm.helpers

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.isVisible() = visibility == View.VISIBLE

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun onViews(views: List<View>, func: View.() -> Unit) {
    views.map { it.func() }
}

fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard(inputMethodManager: InputMethodManager) {
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun hideViews(views: List<View>) {
    onViews(views) { hide() }
}

fun showViews(views: List<View>) {
    onViews(views) { show() }
}

fun conditionalShowViews(views: List<View>, predicate: () -> Boolean) {
    if (predicate()) showViews(views) else hideViews(views)
}


fun ImageView.loadUrl(url: String, placeholderId: Int = 0) {
    if (url.isNotBlank()) {
        if (placeholderId != 0) {
            Glide.with(context).load(url).apply(RequestOptions().placeholder(placeholderId)).into(this)
        } else {
            Glide.with(context).load(url).into(this)
        }
    }
}

/**
 * Adds an [RecyclerView.OnScrollListener] to show or hide the FloatingActionButton when the RecyclerView scrolls up
 * or down respectively
 */
fun RecyclerView.bindFloatingActionButton(fab: FloatingActionButton) = this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0 && fab.isShown) {
            fab.hide()
        } else if (dy < 0 && !fab.isShown) {
            fab.show()
        }
    }
})



