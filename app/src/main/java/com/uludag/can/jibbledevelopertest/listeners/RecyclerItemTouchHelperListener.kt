package com.uludag.can.jibbledevelopertest.listeners

import android.support.v7.widget.RecyclerView

interface RecyclerItemTouchHelperListener {
    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
}