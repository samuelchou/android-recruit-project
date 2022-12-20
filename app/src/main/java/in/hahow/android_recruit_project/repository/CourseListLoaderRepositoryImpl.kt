package `in`.hahow.android_recruit_project.repository

import `in`.hahow.android_recruit_project.R
import `in`.hahow.android_recruit_project.data.CourseListBundle
import `in`.hahow.android_recruit_project.util.jsonToObject
import `in`.hahow.android_recruit_project.util.readResAsString
import android.content.Context

class CourseListLoaderRepositoryImpl constructor(
    private val context: Context
): CourseListLoaderRepository {
    override fun debugMessage(): String = "CourseListLoaderRepositoryImpl"

    override fun getDisplayListBundle(): CourseListBundle {
        return context.readResAsString(R.raw.course_list_data).jsonToObject()
    }
}