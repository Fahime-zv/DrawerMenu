package com.fahimezv.drawermenu

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListPopupWindow
import android.widget.TextView

class MenuItem(context:Context):LinearLayout(context) {

	init {
		orientation = VERTICAL


	}

	private fun createTextView(name:String) = TextView(context).apply {
		text = name
		setTextColor(Color.WHITE)
	}

	private fun createIconImageView(icon:Int) = ImageView(context).apply {
		setBackgroundResource(icon)
	}

	fun addMenuItem(icon:Int ,name:String ,showLine:Boolean ,onClickListener:OnClickListener) {


		if(this.childCount >0) {
			if(showLine) {
				val lineView = View(context).apply {
					setBackgroundColor(Color.WHITE)
				}
				this.addView(lineView ,ViewGroup.LayoutParams.MATCH_PARENT ,5)
			}
		}


		val iconImageView = createIconImageView(icon)
		val nameTextView = createTextView(name)

		val menuLinearLayout = LinearLayout(context).apply {
			orientation = HORIZONTAL
			addView(iconImageView ,100 ,100)
			addView(nameTextView ,ViewGroup.LayoutParams.WRAP_CONTENT ,ViewGroup.LayoutParams.WRAP_CONTENT)
		}
		menuLinearLayout.setOnClickListener(onClickListener)

		this.addView(
			menuLinearLayout ,ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.WRAP_CONTENT
					)
	}

}