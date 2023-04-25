package com.example.tasks.TouchControl

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.R
import kotlin.math.abs


class SurfaceActivity : AppCompatActivity() {
    private val TAG = "AppDev"
    private var circle = PointF(0f, 0f)
    private var radius = 50f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_surface)
        Log.d(TAG, this.localClassName + " is now running!")

        val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
        surfaceView.visibility = View.INVISIBLE;

        val btn = findViewById<Button>(R.id.btnStart)

        btn.setOnClickListener(View.OnClickListener {
            surfaceView.visibility = View.VISIBLE;

            val minX = 2 * radius
            val maxX = surfaceView.width - 2 * radius - minX;

            val minY = 2 * radius
            val maxY = surfaceView.height - 2 * radius - minY;

            circle.x = Math.random().toFloat() * maxX + minX
            circle.y = Math.random().toFloat() * maxY + minY

            drawCircle(surfaceView)

            Log.d("$TAG-Start", "Generated Circle at: $circle")
        })

        surfaceView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val x = event.x
                val y = event.y
                val radius = 50F

                val xDistance = abs(x-circle.x)
                val yDistance = abs(y-circle.y)

                if(xDistance <= radius && yDistance <= radius) {
                    Toast.makeText(this, "Very well done, you caught that one! Lets go again!", Toast.LENGTH_SHORT).show()
                    btn.performClick()
                }else{
                    Log.d("$TAG-SurfaceClick", "Difference: x=$xDistance, y=$yDistance")
                }



                surfaceView.performClick();
                return@setOnTouchListener true
            }
            false
        }
    }

    fun drawCircle(surface: SurfaceView?) {
        val canvas: Canvas? = surface?.holder?.lockCanvas()
        canvas?.drawColor(Color.WHITE)
        canvas?.drawCircle(circle.x, circle.y, radius, Paint(Paint.ANTI_ALIAS_FLAG))
        surface?.holder?.unlockCanvasAndPost(canvas)
    }
}