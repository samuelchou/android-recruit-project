package `in`.hahow.android_recruit_project.inject

import `in`.hahow.android_recruit_project.repository.CourseListLoaderRepository
import `in`.hahow.android_recruit_project.repository.CourseListLoaderRepositoryImpl
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun provideListRepository(
        @ApplicationContext
        context: Context,
    ): CourseListLoaderRepository {
        return CourseListLoaderRepositoryImpl(context)
    }
}
