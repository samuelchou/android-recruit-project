package `in`.hahow.android_recruit_project.viewModel

import `in`.hahow.android_recruit_project.data.CourseBundle
import `in`.hahow.android_recruit_project.repository.CourseListLoaderRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val repository: CourseListLoaderRepository,
) : ViewModel() {

    val debugMessage = MutableLiveData("ViewModel")
    val list = MutableLiveData<List<CourseBundle>>()

    init {
        debugMessage.postValue(repository.debugMessage())
    }

    fun loadData() {
        // might be time-consuming in the future.
        repository.getDisplayListBundle().let {
            list.postValue(it.list)
        }
    }
}

class CourseListViewModelFactory(
    val repository: CourseListLoaderRepository,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CourseListViewModel(repository) as T
    }
}