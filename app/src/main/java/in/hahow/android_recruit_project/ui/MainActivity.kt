package `in`.hahow.android_recruit_project.ui

import `in`.hahow.android_recruit_project.databinding.ActivityMainBinding
import `in`.hahow.android_recruit_project.viewModel.CourseListViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
    }

    private fun setupBinding() {
        viewModel.debugMessage.observe(this) {
            binding.textView.text = "Hello $it"
        }
    }
}