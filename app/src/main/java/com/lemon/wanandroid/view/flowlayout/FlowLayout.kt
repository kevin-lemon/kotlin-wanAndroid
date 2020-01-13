package com.lemon.wanandroid.view.flowlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import java.util.*
import kotlin.math.max

class FlowLayout(context: Context, attributeSet: AttributeSet?, defStyle: Int) : ViewGroup(context, attributeSet, defStyle), FlowAdapter.OnDataChangedListener{

    constructor (context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor (context: Context) : this(context, null)
    var mFlowAdapter:FlowAdapter<*>? = null
    var mAllViews = ArrayList<ArrayList<View>>()
    var mLineHeight = ArrayList<Int>()
    var mLineWidth = ArrayList<Int>()
    var lineViews = ArrayList<View>()
    var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(
            view: View?,
            position: Int
        )
    }

    fun setOnItemClickListener(onClickListener: OnItemClickListener) {
        mOnItemClickListener = onClickListener
    }
    override fun onMeasure(
        widthMeasureSpec: Int,
        heightMeasureSpec: Int
    ) {
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        var width = 0
        var height = 0

        var lineWidth = 0
        var lineHeight = 0
        val cCount = childCount

        for (i in 0 until cCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) {
                if (i == cCount - 1) {
                    width = max(lineWidth, width)
                    height += lineHeight
                }
                continue
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp = child
                .layoutParams as MarginLayoutParams
            val childWidth = (child.measuredWidth + lp.leftMargin
                    + lp.rightMargin)
            val childHeight = (child.measuredHeight + lp.topMargin
                    + lp.bottomMargin)
            if (lineWidth + childWidth > sizeWidth - paddingLeft - paddingRight) {
                width = max(width, lineWidth)
                lineWidth = childWidth
                height += lineHeight
                lineHeight = childHeight
            } else {
                lineWidth += childWidth
                lineHeight = max(lineHeight, childHeight)
            }
            if (i == cCount - 1) {
                width = max(lineWidth, width)
                height += lineHeight
            }
        }
        setMeasuredDimension(
            if (modeWidth == MeasureSpec.EXACTLY) sizeWidth else width + paddingLeft + paddingRight,
            if (modeHeight == MeasureSpec.EXACTLY) sizeHeight else height + paddingTop + paddingBottom
        )
    }
    fun setAdapter(adapter: FlowAdapter<*>) {
        mFlowAdapter = adapter
        mFlowAdapter?.setOnDataChangedListener(this)
        changeAdapter()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mAllViews.clear()
        mLineHeight.clear()
        mLineWidth.clear()
        lineViews.clear()

        val width = width

        var lineWidth = 0
        var lineHeight = 0

        val cCount = childCount

        for (i in 0 until cCount) {
            val child = getChildAt(i)
            if (child.visibility == View.GONE) continue
            val lp = child
                .layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - paddingLeft - paddingRight) {
                mLineHeight.add(lineHeight)
                mAllViews.add(lineViews)
                mLineWidth.add(lineWidth)
                lineWidth = 0
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin
                lineViews = ArrayList<View>()
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin
            lineHeight = Math.max(
                lineHeight, childHeight + lp.topMargin
                        + lp.bottomMargin
            )
            lineViews.add(child)
        }
        mLineHeight.add(lineHeight)
        mLineWidth.add(lineWidth)
        mAllViews.add(lineViews)

        val lineNum = mAllViews.size
        var top = paddingTop
        for (i in 0 until lineNum) {
            lineViews = mAllViews[i]
            lineHeight = mLineHeight[i]
            var left = paddingLeft
            for (j in lineViews.indices) {
                val child: View = lineViews[j]
                if (child.visibility == View.GONE) {
                    continue
                }
                val lp = child
                    .layoutParams as MarginLayoutParams
                val lc = left + lp.leftMargin
                val tc = top + lp.topMargin
                val rc = lc + child.measuredWidth
                val bc = tc + child.measuredHeight
                child.layout(lc, tc, rc, bc)
                left += (child.measuredWidth + lp.leftMargin
                        + lp.rightMargin)
            }
            top += lineHeight
        }

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams? {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams? {
        return MarginLayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams? {
        return MarginLayoutParams(p)
    }

    override fun onChanged() {
        changeAdapter()
    }

    private fun changeAdapter() {
        removeAllViews()
        val adapter: FlowAdapter<*>? = mFlowAdapter
        adapter?.let {
            var tagViewContainer: FrameLayout? = null
            for (i in 0 until it.getCount()) {
                val tagView: View? = it.getView(this, i,it.getItem(i))
                tagViewContainer = FrameLayout(context)
                tagView?.isDuplicateParentStateEnabled = true
                if (tagView?.layoutParams != null) {
                    tagViewContainer.layoutParams = tagView.layoutParams
                } else {
                    val lp = MarginLayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    lp.setMargins(
                        dip2px(context, 5f),
                        dip2px(context, 5f),
                        dip2px(context, 5f),
                        dip2px(context, 5f)
                    )
                    tagViewContainer.layoutParams = lp
                }
                val lp =
                    LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                tagView?.layoutParams = lp
                tagViewContainer.addView(tagView)
                addView(tagViewContainer)
                tagView?.isClickable = false
                val finalTagViewContainer: FrameLayout? = tagViewContainer
                tagViewContainer.setOnClickListener(OnClickListener {
                    mOnItemClickListener?.onItemClick(
                        finalTagViewContainer, i
                    )
                })
            }
        }
    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}