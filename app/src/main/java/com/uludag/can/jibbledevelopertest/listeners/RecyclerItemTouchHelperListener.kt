package com.uludag.can.jibbledevelopertest.listeners

import android.support.v7.widget.RecyclerView

// This listener contains swipe action method that will be implemented in HomeActivity
interface RecyclerItemTouchHelperListener {
    fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int)
}