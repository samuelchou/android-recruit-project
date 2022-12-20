package `in`.hahow.android_recruit_project.ui

import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.data.CourseBundle
import `in`.hahow.android_recruit_project.databinding.ItemCourseListBinding
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CourseListAdapter : ListAdapter<CourseBundle, RecyclerView.ViewHolder>(CourseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseListBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.setItem(getItem(position))
        }
    }

    class ItemViewHolder(private val binding: ItemCourseListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(item: CourseBundle) {
            // TODO: FATAL: set item
            when (item.status) {
                "INCUBATING" -> binding.setAsIncubating(item)
                else -> binding.setAsProgress(item)
            }
            binding.executePendingBindings()
        }

        private fun ItemCourseListBinding.setAsProgress(item: CourseBundle) {
            val context = root.context
            val color = ContextCompat.getColor(context, R.color.teal)
            textStatus.text = context.getString(R.string.desc_course_status_success)
            textStatus.backgroundTintList = ColorStateList.valueOf(color)
            textTitle.text = "進行中課程"
            progressBar.setIndicatorColor(color)
            progressBar.progress = 65
            progressBar.max = 100
            textProgress.text = context.getString(R.string.desc_course_progress, "65")
            textCountdown.isInvisible = true
        }

        private fun ItemCourseListBinding.setAsIncubating(item: CourseBundle) {
            val context = root.context
            val color = ContextCompat.getColor(context, R.color.orange)
            textStatus.text = context.getString(R.string.desc_course_status_incubating)
            textStatus.backgroundTintList = ColorStateList.valueOf(color)
            textTitle.text = "募資中課程"
            progressBar.setIndicatorColor(color)
            progressBar.progress = 10
            progressBar.max = 30
            textProgress.text =
                context.getString(R.string.desc_course_incubating_progress_people, 10, 30)
            textCountdown.isInvisible = false
            textCountdown.text = context.getString(R.string.desc_course_countdown, "13天")
        }
    }
}

object CourseDiffCallback : DiffUtil.ItemCallback<CourseBundle>() {
    override fun areItemsTheSame(oldItem: CourseBundle, newItem: CourseBundle): Boolean {
        return oldItem.title == newItem.title // if true, check content
    }

    override fun areContentsTheSame(oldItem: CourseBundle, newItem: CourseBundle): Boolean {
        return oldItem == newItem // if false, update
    }

}