package `in`.hahow.android_recruit_project.repository

import `in`.hahow.android_recruit_project.data.CourseListBundle

interface CourseListLoaderRepository {
    fun debugMessage(): String

    fun getDisplayListBundle(): CourseListBundle
}