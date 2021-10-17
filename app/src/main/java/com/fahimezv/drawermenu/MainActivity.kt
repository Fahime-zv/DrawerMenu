package com.fahimezv.drawermenu

import android.annotation.SuppressLint
import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListPopupWindow
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity:AppCompatActivity() {
	private lateinit var rootFrameLayout:FrameLayout
	private lateinit var mainFrame:FrameLayout
	private lateinit var secondFrame:LinearLayout
	private lateinit var customDrawerLayout:DrawerLayout
	private lateinit var actionBarDrawerToggle:ActionBarDrawerToggle
	private lateinit var item:MenuItem

	@SuppressLint("ResourceType")
	override fun onCreate(savedInstanceState:Bundle?) {
		super.onCreate(savedInstanceState)
		customDrawerLayout = DrawerLayout(this)
		customDrawerLayout.setScrimColor(Color.TRANSPARENT)
		item = MenuItem(this)

		rootFrameLayout = FrameLayout(this).apply {
			setBackgroundColor(Color.parseColor("#c1805e"))
		}

		setContentView(rootFrameLayout)

		rootFrameLayout.addView(
			customDrawerLayout ,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT)
							   )

		mainFrame = FrameLayout(this).apply {
			setBackgroundColor(Color.WHITE)
			id=1235
		}
		supportFragmentManager.commit {
			setReorderingAllowed(true)
			replace<MainFragment>(1235)
		}



		secondFrame = LinearLayout(this).apply {
			gravity = Gravity.CENTER_VERTICAL
			orientation = LinearLayout.VERTICAL
			setBackgroundColor(Color.TRANSPARENT)

			item.addMenuItem(R.drawable.ic_launcher_background ,"Home" ,true) {
				Toast.makeText(this@MainActivity ,"item1" ,Toast.LENGTH_SHORT).show()

			}
			item.addMenuItem(R.drawable.ic_launcher_background ,"Category" ,true) {
				Toast.makeText(this@MainActivity ,"item2" ,Toast.LENGTH_SHORT).show()
			}

			item.addMenuItem(R.drawable.ic_launcher_background ,"Track Order" ,true) {
				Toast.makeText(this@MainActivity ,"item3" ,Toast.LENGTH_SHORT).show()
			}
			item.addMenuItem(R.drawable.ic_launcher_background ,"Contact us " ,true) {
				Toast.makeText(this@MainActivity ,"item4" ,Toast.LENGTH_SHORT).show()

			}
			addView(item ,ListPopupWindow.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT)

		}

		val params1 = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT)
		customDrawerLayout.addView(mainFrame ,params1)
		val params2 = DrawerLayout.LayoutParams(700 ,ViewGroup.LayoutParams.MATCH_PARENT)
		params2.gravity = Gravity.START
		customDrawerLayout.addView(secondFrame ,params2)


		actionBarDrawerToggle = object:ActionBarDrawerToggle(
			this ,customDrawerLayout ,R.string.app_name ,R.string.app_name
															) {
			override fun onDrawerSlide(drawerView:View ,slideOffset:Float) {
				super.onDrawerSlide(drawerView ,slideOffset)
				val slideX = drawerView.width * slideOffset
				mainFrame.scaleX = 1 - (slideOffset / 10f);
				mainFrame.scaleY = 1 - (slideOffset / 10f);

				mainFrame.translationX = slideX

			}

		}
		customDrawerLayout.addDrawerListener(actionBarDrawerToggle)


	}
}