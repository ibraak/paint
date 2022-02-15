package com.ibra.paintapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.ibra.paintapplication.MainActivity.Companion.oval
import com.ibra.paintapplication.MainActivity.Companion.paintBrush
import com.ibra.paintapplication.MainActivity.Companion.path
import com.ibra.paintapplication.MainActivity.Companion.rect
import com.ibra.paintapplication.MainActivity.Companion.shape
import com.ibra.paintapplication.data.ArrowData
import com.ibra.paintapplication.data.OvalData
import com.ibra.paintapplication.data.PencilData
import com.ibra.paintapplication.data.square

class PaintView : View {
    private var params: ViewGroup.LayoutParams? = null
    private var startPointX = 0f
    private var startPointY = 0f
    private var endPointX = 0f
    private var endPointY = 0f

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    companion object {
        lateinit var pencilData: PencilData
        lateinit var arrowData: ArrowData
        lateinit var squareData: square
        lateinit var ovalData: OvalData
        var pathList = ArrayList<PencilData>()
        var arrowList = ArrayList<ArrowData>()
        var rectList = ArrayList<square>()
        var ovalList = ArrayList<OvalData>()
        var currentColor = Color.BLACK
    }


    private fun init() {
        paintBrush.isAntiAlias = true
        paintBrush.color = currentColor
        paintBrush.style = Paint.Style.STROKE
        paintBrush.strokeJoin = Paint.Join.ROUND
        paintBrush.strokeWidth = 10f
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (shape) {
            "pencil" -> {
                pencilTouchEvent(event)
            }
            "square" -> {
                squareTouchEvent(event)
            }
            "circle" -> {
                ovalTouchEvent(event)
            }
            "arrow" -> {
                arrowTouchEvent(event)
            }
            else -> false
        }


    }

    private fun arrowTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //colorList.add(currentColor)
                arrowData = ArrowData()
                startPointX = event.x
                startPointY = event.y
                arrowData.sX = startPointX
                arrowData.sY = startPointY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                //colorList.add(currentColor)
                endPointX = event.x
                endPointY = event.y
                arrowData.eX = endPointX
                arrowData.eY = endPointY
                arrowData.color = currentColor
                arrowList.add(arrowData)
            }
            else -> return false
        }
        invalidate()
        return false
    }

    private fun squareTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                squareData = square()
                startPointX = event.x
                startPointY = event.y
                rect = RectF()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // colorList.add(currentColor)
                endPointX = event.x
                endPointY = event.y
                rect.set(startPointX, startPointY, endPointX, endPointY)
                squareData.rect = rect
                squareData.colorSquare = currentColor
                rectList.add(squareData)

            }
            else -> return false
        }
        invalidate()
        return false
    }

    private fun pencilTouchEvent(event: MotionEvent): Boolean {
        startPointX = event.x
        startPointY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pencilData = PencilData()
                path.moveTo(startPointX, startPointY)
                pencilData.colorPencil = currentColor
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(startPointX, startPointY)
                pencilData.path = path
                pathList.add(pencilData)
            }
            else -> return false
        }

        invalidate()
        return false
    }

    private fun ovalTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                ovalData = OvalData()
                startPointX = event.x
                startPointY = event.y
                oval = RectF()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // colorList.add(currentColor)
                endPointX = event.x
                endPointY = event.y
                oval.set(startPointX, startPointY, endPointX, endPointY)
                ovalData.oval = oval
                ovalData.colorOval = currentColor
                ovalList.add(ovalData)
            }
            else -> return false
        }
        invalidate()
        return false
    }

    override fun onDraw(canvas: Canvas) {

        for (i in rectList.indices) {
            paintBrush.setColor(rectList[i].colorSquare!!)
            canvas.drawRect(rectList[i].rect!!, paintBrush)
            invalidate()
        }

        for (i in pathList.indices) {
            paintBrush.setColor(pathList[i].colorPencil!!)
            canvas.drawPath(pathList[i].path!!, paintBrush)
            invalidate()
        }
        for (i in arrowList.indices) {
            paintBrush.setColor(arrowList[i].color!!)
            canvas.drawLine(
                arrowList[i].sX!!, arrowList[i].sY!!, arrowList[i].eX!!, arrowList[i].eY!!,
                paintBrush
            )
            invalidate()
        }

        for (i in ovalList.indices) {
            paintBrush.setColor(ovalList[i].colorOval!!)
            canvas.drawOval(ovalList[i].oval!!, paintBrush)
            invalidate()
        }
    }


}