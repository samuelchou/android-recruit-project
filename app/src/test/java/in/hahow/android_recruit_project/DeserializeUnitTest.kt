package `in`.hahow.android_recruit_project

import `in`.hahow.android_recruit_project.data.CourseListBundle
import `in`.hahow.android_recruit_project.util.jsonToObject
import `in`.hahow.android_recruit_project.util.readStringFromResourcesFile
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class DeserializeUnitTest {
    private fun readRaw(fileName: String) = readStringFromResourcesFile(fileName, "raw/")

    @Test
    fun deserializeJson() {
        val jsonString = readRaw("course-list-data.json")
        println("jsonString length: ${jsonString.length}")
        assertTrue(jsonString.isNotBlank())
    }

    @Test
    fun deserializeList() {
        val jsonString = readRaw("course-list-data.json")
        val listBundle = jsonString.jsonToObject<CourseListBundle>()
        assertNotNull(listBundle)
        assertEquals(24, listBundle.list.size)
        listBundle.list[0].let { bundle ->
            assertEquals("學習 AI 一把抓：點亮人工智慧技能樹", bundle.title)
            assertEquals(
                "https://images.api.hahow.in/images/614eca15a39712000619b672", bundle.coverImageUrl
            )
            assertEquals("INCUBATING", bundle.status)
            assertEquals(0, bundle.numSoldTickets)
            assertEquals(30, bundle.successCriteria.numSoldTickets)
            assertEquals(LocalDateTime.of(2022, 1, 6, 16, 0, 0), bundle.getDueTime())
        }
        listBundle.list[2].let { bundle ->
            assertEquals("米其林三星主廚教你做！時尚法式甜點的秘密", bundle.title)
            assertEquals(
                "https://images.api.hahow.in/images/619fcfd1072d3d0006c4f2f5", bundle.coverImageUrl
            )
            assertEquals("INCUBATING", bundle.status)
            assertEquals(88, bundle.numSoldTickets)
            assertEquals(30, bundle.successCriteria.numSoldTickets)
            assertEquals(LocalDateTime.of(2021, 12, 30, 16, 0, 0), bundle.getDueTime())
        }
        listBundle.list.last().let { bundle ->
            assertEquals("RyuuuTV看動漫看日劇，零到N4道地說日文", bundle.title)
            assertEquals(
                "https://images.api.hahow.in/images/61541433666b1b0006b32be3", bundle.coverImageUrl
            )
            assertEquals("SUCCESS", bundle.status)
            assertEquals(5619, bundle.numSoldTickets)
            assertEquals(30, bundle.successCriteria.numSoldTickets)
            assertEquals(null, bundle.getDueTime())
        }
    }
}