package com.mathsgame.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var addition: Button
    private lateinit var subtraction: Button
    private lateinit var multiplication: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar()

        addition = findViewById(R.id.addButton)
        subtraction = findViewById(R.id.subtractButton)
        multiplication = findViewById(R.id.multiplyButton)

        addition.setOnClickListener {
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setStatusBar() {
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.ice_blue)))
        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.ice_blue)
        }
    }
}