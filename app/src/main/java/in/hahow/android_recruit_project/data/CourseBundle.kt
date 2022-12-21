package `in`.hahow.android_recruit_project.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Serializable
data class CourseBundle(
    val title: String,
    val status: String,
    val successCriteria: CourseSuccessCriteria,
    val numSoldTickets: Int,
    val proposalDueTime: String? = null,
    val coverImageUrl: String,
    val totalVideoLengthInSeconds: Int? = null,
) {
    fun getDueTime(): LocalDateTime? {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        return proposalDueTime?.let { LocalDateTime.parse(it, format) }
    }
}

@Serializable
data class CourseSuccessCriteria(
    val numSoldTickets: Int,
)

@Serializable
data class CourseListBundle(
    @SerialName("data")
    val list: List<CourseBundle>,
)
