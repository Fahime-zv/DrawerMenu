package com.fahimezv.drawermenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity:AppCompatActivity() {
	private lateinit var rootLayout:FrameLayout
	private lateinit var containerFrame:CardView
	private lateinit var menuLayout:LinearLayout
	private lateinit var customDrawerLayout:CustomDrawerLayout
	private lateinit var actionBarDrawerToggle:ActionBarDrawerToggle
	private lateinit var item:MenuItem
	private lateinit var openMenuButton:Button

	@SuppressLint("ResourceType")
	override fun onCreate(savedInstanceState:Bundle?) {
		super.onCreate(savedInstanceState)
		rootLayout = FrameLayout(this).apply {
			setBackgroundColor(Color.parseColor("#c1805e"))

			item = createItemMenu()
			addView(item ,ListPopupWindow.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT)

			customDrawerLayout = createCustomDrawerLayout()

			addView(
				customDrawerLayout ,ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT
														  )
				   )

		}

		setContentView(rootLayout)



		containerFrame = createContainerFrame().also {
			it.addView(openMenuButton ,WRAP_CONTENT ,WRAP_CONTENT)
		}

		supportFragmentManager.commit {
			setReorderingAllowed(true)
			replace<MainFragment>(1235)

		}

		menuLayout = createMenuLayout()
		customDrawerLayout.also {
			val params1 = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,ViewGroup.LayoutParams.MATCH_PARENT)
			it.addView(containerFrame ,params1)

			val params2 = DrawerLayout.LayoutParams(500 ,ViewGroup.LayoutParams.MATCH_PARENT)
			params2.gravity = Gravity.LEFT

			it.addView(menuLayout ,params2)

			actionBarDrawerToggle = createActionBarDrawerToggle()
			it.addDrawerListener(actionBarDrawerToggle)
		}


	}

	private fun createMenuLayout() = LinearLayout(this).apply {
		gravity = Gravity.CENTER_VERTICAL
		orientation = LinearLayout.VERTICAL
		setBackgroundColor(Color.TRANSPARENT)

	}

	@SuppressLint("ResourceType")
	private fun createContainerFrame() = CardView(this).apply {
		id = 1235            //OpenCloseMenu
		openMenuButton = createButtonOpenMenu()

	}

	private fun createCustomDrawerLayout() = CustomDrawerLayout(this@MainActivity).apply {            //shadow
		setScrimColor(Color.TRANSPARENT)
	}

	private fun createItemMenu() = MenuItem(this).apply {
		addMenuItem(R.drawable.ic_launcher_background ,"Home" ,true) {
			Toast.makeText(this@MainActivity ,"item1" ,Toast.LENGTH_SHORT).show()
		}
		addMenuItem(R.drawable.ic_launcher_background ,"Category" ,true) {
			Toast.makeText(this@MainActivity ,"item2" ,Toast.LENGTH_SHORT).show()
		}

		addMenuItem(R.drawable.ic_launcher_background ,"Track Order" ,true) {
			Toast.makeText(this@MainActivity ,"item3" ,Toast.LENGTH_SHORT).show()
		}
		addMenuItem(R.drawable.ic_launcher_background ,"Contact us " ,true) {
			Toast.makeText(this@MainActivity ,"item4" ,Toast.LENGTH_SHORT).show()


		}
	}


	private fun createButtonOpenMenu() = Button(this).apply {
		text = "Open/close Menu"
		setOnClickListener {
			if(customDrawerLayout.isDrawerOpen(GravityCompat.START)) customDrawerLayout.closeDrawer(GravityCompat.START)
			else customDrawerLayout.openDrawer(GravityCompat.START)
		}
	}

	private fun createActionBarDrawerToggle() = object:ActionBarDrawerToggle(this ,customDrawerLayout ,R.string.app_name ,R.string.app_name) {
		override fun onDrawerSlide(drawerView:View ,slideOffset:Float) {
			super.onDrawerSlide(drawerView ,slideOffset)
			val slideX = drawerView.width * slideOffset
			containerFrame.scaleX = 1 - (slideOffset / 10f);
			containerFrame.scaleY = 1 - (slideOffset / 10f);
			containerFrame.translationX = slideX
			containerFrame.radius = slideOffset * 80

		}
	}
}
