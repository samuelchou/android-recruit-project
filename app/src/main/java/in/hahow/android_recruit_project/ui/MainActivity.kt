package `in`.hahow.android_recruit_project.ui

import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.databinding.ActivityMainBinding
import `in`.hahow.android_recruit_project.databinding.ItemCourseListBinding
import `in`.hahow.android_recruit_project.viewModel.CourseListViewModel
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CourseListViewModel by viewModels()
    // or by old school Factory + Provider
//    private val viewModel: CourseListViewModel by lazy {
//        ViewModelProvider(
//            this, CourseListViewModelFactory(CourseListLoaderRepositoryImpl(this))
//        )[CourseListViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()

        loadData()
    }

    private fun setupBinding() {
        viewModel.debugMessage.observe(this) {
            binding.textView.text = "Hello $it"
        }

        viewModel.list.observe(this) {
            Log.d(TAG, "list: list size: ${it.size}")
        }

        // TODO: FATAL: test-only. remove it after finished.
        binding.testItem1.setAsProgress()
        binding.testItem2.setAsIncubating()
    }

    private fun ItemCourseListBinding.setAsProgress() {
        val color = ContextCompat.getColor(this@MainActivity, R.color.teal)
        textStatus.text = getString(R.string.desc_course_status_success)
        textStatus.backgroundTintList = ColorStateList.valueOf(color)
        textTitle.text = "進行中課程"
        progressBar.setIndicatorColor(color)
        progressBar.progress = 65
        progressBar.max = 100
        textProgress.text = getString(R.string.desc_course_progress, "65")
        textCountdown.isInvisible = true
    }

    private fun ItemCourseListBinding.setAsIncubating() {
        val color = ContextCompat.getColor(this@MainActivity, R.color.orange)
        textStatus.text = getString(R.string.desc_course_status_incubating)
        textStatus.backgroundTintList = ColorStateList.valueOf(color)
        textTitle.text = "募資中課程"
        progressBar.setIndicatorColor(color)
        progressBar.progress = 10
        progressBar.max = 30
        textProgress.text = getString(R.string.desc_course_incubating_progress_people, 10, 30)
        textCountdown.isInvisible = false
        textCountdown.text = getString(R.string.desc_course_countdown, "13天")
    }

    private fun loadData() {
        viewModel.loadData()
    }
}