package com.example.assignment3.TouchControl

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.R
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
        val btn = findViewById<Button>(R.id.btnStart)

        btn.setOnClickListener(View.OnClickListener {

            val minX = 2 * radius
            val maxX = surfaceView.width - 2 * radius - minX;

            val minY = 2 * radius
            val maxY = surfaceView.height - 2 * radius - minY;

            circle.x = Math.random().toFloat() * maxX + minX
            circle.y = Math.random().toFloat() * maxY + minY

            drawCircle(surfaceView)

            Log.d("$TAG-Start", "Generated Circle at: $circle")
        })
    }

    fun drawCircle(surface: SurfaceView?) {
        val canvas: Canvas? = surface?.holder?.lockCanvas()
        canvas?.drawColor(Color.WHITE)
        canvas?.drawCircle(circle.x, circle.y, radius, Paint(Paint.ANTI_ALIAS_FLAG))
        surface?.holder?.unlockCanvasAndPost(canvas)
    }
}