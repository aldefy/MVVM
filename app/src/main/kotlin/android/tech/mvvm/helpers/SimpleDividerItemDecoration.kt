package android.tech.mvvm.helpers

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v7.widget.RecyclerView


class SimpleDividerItemDecoration(context: Context, private val mColor: Int, private val mHeight: Int // in px format
) : RecyclerView.ItemDecoration() {
    private val paint: Paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView) {

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child
                    .layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + mHeight

            paint.color = mColor
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)

        }
    }
}
