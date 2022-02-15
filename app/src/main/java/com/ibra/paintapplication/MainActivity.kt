package com.ibra.paintapplication


import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.ibra.paintapplication.PaintView.Companion.currentColor
import me.ibrahimsn.lib.SmoothBottomBar

class MainActivity : AppCompatActivity() {
    companion object {
        var path = Path()
        var paintBrush = Paint()
        var rect = RectF()
        var oval = RectF()
        var shape = "pencil"
    }

    lateinit var iconBar: SmoothBottomBar
    lateinit var colorBar: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iconBar = findViewById<SmoothBottomBar>(R.id.iconBar)
        colorBar = findViewById<LinearLayout>(R.id.BottomBar)
        var imgRed = findViewById<ImageButton>(R.id.imgRed)
        var imgGreen = findViewById<ImageButton>(R.id.imgGreen)
        var imgBlue = findViewById<ImageButton>(R.id.imgBlue)
        var imgBlack = findViewById<ImageButton>(R.id.imgBlack)
        iconBar.setOnItemSelectedListener {
            when (it) {
                0 -> {
                    shape = "pencil"
                }
                1 -> {
                    shape = "arrow"
                }
                2 -> {
                    shape = "square"
                }
                3 -> {
                    shape = "circle"
                }
                4 -> {
                    colorBar.visibility = View.VISIBLE
                }
            }
        }
        iconBar.setOnItemReselectedListener {
            when (it) {
                0 -> {
                    shape = "pencil"
                }
                1 -> {
                    shape = "arrow"
                }
                2 -> {
                    shape = "square"
                }
                3 -> {
                    shape = "circle"
                }
                4 -> {
                    colorBar.visibility = View.VISIBLE
                }
            }
        }
        imgRed.setOnClickListener {
            currentColor = Color.RED
            colorBar.visibility = View.GONE
        }
        imgGreen.setOnClickListener {
            currentColor = Color.GREEN
            colorBar.visibility = View.GONE
        }
        imgBlue.setOnClickListener {
            currentColor = Color.BLUE
            colorBar.visibility = View.GONE
        }
        imgBlack.setOnClickListener {
            currentColor = Color.BLACK
            colorBar.visibility = View.GONE
        }


    }


}