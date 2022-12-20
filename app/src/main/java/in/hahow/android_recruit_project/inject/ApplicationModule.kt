package `in`.hahow.android_recruit_project.inject

import `in`.hahow.android_recruit_project.repository.CourseListLoaderRepository
import `in`.hahow.android_recruit_project.repository.CourseListLoaderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun provideListRepository(): CourseListLoaderRepository {
        return CourseListLoaderRepositoryImpl()
    }
}
