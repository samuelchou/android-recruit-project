package `in`.hahow.android_recruit_project.ui.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlin.math.roundToInt

/**
 * an mock-up class based on [androidx.recyclerview.widget.DividerItemDecoration]. but with optional cancel draw rule.
 *
 * Credit: Samuel.T.Chou since 2022.05
 */
abstract class CancellableDividerItemDecoration(
    context: Context, orientation: Int
) : ItemDecoration() {
    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL
        const val VERTICAL = LinearLayout.VERTICAL
        private const val TAG = "DividerItem"
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }

    /**
     * @return the [Drawable] for this divider.
     */
    var drawable: Drawable?
        private set

    /**
     * Current orientation. Either [CancellableDividerItemDecoration.HORIZONTAL] or [CancellableDividerItemDecoration.VERTICAL].
     */
    private var mOrientation = 0
    private val mBounds = Rect()

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        drawable = a.getDrawable(0)
        if (drawable == null) {
            Log.w(
                TAG,
                "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()"
            )
        }
        a.recycle()
        setOrientation(orientation)
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * [RecyclerView.LayoutManager] changes orientation.
     *
     * @param orientation Either [CancellableDividerItemDecoration.HORIZONTAL] or [CancellableDividerItemDecoration.VERTICAL].
     */
    fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL && orientation != VERTICAL)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        mOrientation = orientation
    }

    /**
     * Sets the [Drawable] for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    fun setDrawable(drawable: Drawable?) {
        requireNotNull(drawable) { "Drawable cannot be null." }
        this.drawable = drawable
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null || drawable == null) {
            return
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(
                left, parent.paddingTop, right,
                parent.height - parent.paddingBottom
            )
        } else {
            left = 0
            right = parent.width
        }
//        if (cancelDividerMode and LinearLayoutCompat.SHOW_DIVIDER_MIDDLE != 0) return
        val childCount = parent.childCount
//        val endCount: Int = if (cancelDividerMode and LinearLayoutCompat.SHOW_DIVIDER_END != 0) childCount - 2 else childCount - 1
        for (i in 0 until childCount) {
            if (shouldShowDivider(i, 0, childCount - 1).not()) continue
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, mBounds)
            val bottom = mBounds.bottom + child.translationY.roundToInt()
            val top = bottom - drawable!!.intrinsicHeight
            drawable!!.setBounds(left, top, right, bottom)
            drawable!!.draw(canvas)
        }
        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(
                parent.paddingLeft, top,
                parent.width - parent.paddingRight, bottom
            )
        } else {
            top = 0
            bottom = parent.height
        }
//        if (cancelDividerMode and LinearLayoutCompat.SHOW_DIVIDER_MIDDLE != 0) return
        val childCount = parent.childCount
//        val endCount: Int = if (cancelDividerMode and LinearLayoutCompat.SHOW_DIVIDER_END != 0) childCount - 2 else childCount - 1
        for (i in 0 until childCount) {
            if (shouldShowDivider(i, 0, childCount - 1).not()) continue
            val child = parent.getChildAt(i)
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            val right = mBounds.right + child.translationX.roundToInt()
            val left = right - drawable!!.intrinsicWidth
            drawable!!.setBounds(left, top, right, bottom)
            drawable!!.draw(canvas)
        }
        canvas.restore()
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (drawable == null) {
            outRect[0, 0, 0] = 0
            return
        }
        if (mOrientation == VERTICAL) {
            outRect[0, 0, 0] = drawable!!.intrinsicHeight
        } else {
            outRect[0, 0, drawable!!.intrinsicWidth] = 0
        }
    }

    /**
     * to tell should draw divider or not.
     *
     * @param index the drawing process index.
     * @param startIndex the first index. (usually 0)
     * @param lastIndex the last index. (usually child count)
     */
    abstract fun shouldShowDivider(index: Int, startIndex: Int, lastIndex: Int): Boolean
}
