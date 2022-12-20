package `in`.hahow.android_recruit_project.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseBundle(
    val title: String,
    val status: String,
    val successCriteria: CourseSuccessCriteria,
    val numSoldTickets: Int,
    val proposalDueTime: String? = null,
    val coverImageUrl: String,
    val totalVideoLengthInSeconds: Int? = null,
)

@Serializable
data class CourseSuccessCriteria(
    val numSoldTickets: Int,
)

@Serializable
data class CourseListBundle(
    @SerialName("data")
    val list: List<CourseBundle>,
)
