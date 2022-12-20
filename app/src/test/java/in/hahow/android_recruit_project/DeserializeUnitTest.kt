package `in`.hahow.android_recruit_project

import `in`.hahow.android_recruit_project.util.readStringFromResourcesFile
import org.junit.Assert.assertTrue
import org.junit.Test

class DeserializeUnitTest {
    private fun readRaw(fileName: String) = readStringFromResourcesFile(fileName, "raw/")

    @Test
    fun deserializeJson() {
        val jsonString = readRaw("course-list-data.json")
        println("jsonString length: ${jsonString.length}")
        assertTrue(jsonString.isNotBlank())
    }
}