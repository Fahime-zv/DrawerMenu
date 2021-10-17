package com.fahimezv.drawermenu

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import java.util.*


class CustomDrawerLayout(context:Context):DrawerLayout(context) {
	private val directionRight = true

	override fun onTouchEvent(ev:MotionEvent):Boolean {
		super.onTouchEvent(ev)
		return true
	}

	override fun dispatchTouchEvent(ev:MotionEvent):Boolean {
		super.dispatchTouchEvent(ev)
		if(directionRight) {
			if(isDrawerOpen(getChildAt(1)) || isDrawerVisible(getChildAt(1))) {
				return if(ev.x <= getChildAt(1).right) false
				else (ev.x >= getChildAt(1).right && ev.y >= height - getChildAt(1).right * 4 / 5).not()
			}
		}
		else {
			if(isDrawerOpen(getChildAt(1)) || isDrawerVisible(getChildAt(1))) {
				return ev.x < getChildAt(1).right
			}
		}
		return super.dispatchTouchEvent(ev)
	}





}