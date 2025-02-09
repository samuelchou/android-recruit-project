package `in`.hahow.android_recruit_project.ui

import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.databinding.ActivityMainRecyclerviewBinding
import `in`.hahow.android_recruit_project.ui.component.CancellableDividerItemDecoration
import `in`.hahow.android_recruit_project.viewModel.CourseListViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainRecyclerviewBinding

    private val viewModel: CourseListViewModel by viewModels()
    // or by old school Factory + Provider
//    private val viewModel: CourseListViewModel by lazy {
//        ViewModelProvider(
//            this, CourseListViewModelFactory(CourseListLoaderRepositoryImpl(this))
//        )[CourseListViewModel::class.java]
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBinding()

        loadData()
    }

    private fun setupBinding() {
        val adapter = CourseListAdapter()
        binding.container.run {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            setAdapter(adapter)
            (object : CancellableDividerItemDecoration(context, LinearLayoutManager.VERTICAL) {
                override fun shouldShowDivider(
                    index: Int, startIndex: Int, lastIndex: Int
                ): Boolean = index < lastIndex
            }.apply {
                ResourcesCompat.getDrawable(resources, R.drawable.divider_course, context.theme)
                    ?.also { setDrawable(it) }
            }).let { addItemDecoration(it) }
        }

        viewModel.list.observe(this) {
            Log.d(TAG, "list: list size: ${it.size}")
            adapter.submitList(it)
        }
    }

    private fun loadData() {
        viewModel.loadData()
    }
}