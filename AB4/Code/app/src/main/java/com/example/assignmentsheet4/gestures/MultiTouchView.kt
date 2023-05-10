package com.example.assignmentsheet4.gestures

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.PointF
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MultiTouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val positions = mutableMapOf<Int, PointF>()
    private val colors = listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA)
    private val paint = Paint(ANTI_ALIAS_FLAG) //? ANTI_ALIAS_FLAG ensures that the Brush makes a smooth circle.

    init {
        //? Add the TextView to the Activities layout, if the activity itself exists.
        (context as? Activity)?.let {
            (it.findViewById<ViewGroup>(android.R.id.content)).addView(formatedTextView)
        }
    }



    //Canvas respresents an area on which graphics can be drawn
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //? Point out each finger with a circle
        positions.forEach  { (index, point) ->
            paint.color = colors[index % colors.size]
            canvas?.drawCircle(point.x, point.y, 50f, paint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //actionMasked returns the action of the event (f.e. ACTION_DOWN)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_POINTER_DOWN -> {
                //? A finger touches the View and its presence is cached.
                val index = event.actionIndex
                val id = event.getPointerId(index)
                positions[id] = PointF(event.getX(index), event.getY(index))
            }
            MotionEvent.ACTION_MOVE -> {
                //? A finger has moved while touching, updates the positions of involved fingers.
                for (i in 0 until event.pointerCount) {
                    val id = event.getPointerId(i)
                    positions[id] = PointF(event.getX(i), event.getY(i))
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                //? Remove the touch-event from the cache.
                val id = event.getPointerId(event.actionIndex)
                positions.remove(id)
            }
        }

        formatedTextView.text = positions.size.toString()

        //? Update the view
        invalidate()
        return true
    }

    /**
     * Formats the TextView so that the amount of fingers, that touch the screen, is properly displayed.
     */
    private val formatedTextView = TextView(context).apply {
        textSize = 96f
        gravity = Gravity.CENTER
        setTextColor(Color.RED)
    }


}