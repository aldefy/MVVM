package android.tech.mvvm.helpers

import android.view.View

interface AdapterClickListener {
    /*
     * Standard return position for the element in the list that is clicked on
     */
    fun onClick(pos: Int)

    /*
     * Useful for running shared element transitions between activities / fragments - pass the root view from the layout or the view which has a transition to be applied on
     */
    fun onClick(pos: Int, view: View)
}
