package `in`.hahow.android_recruit_project.util

import android.content.Context
import androidx.annotation.RawRes

/**
 * Read resource file and return as string.
 */
fun Context.readResAsString(@RawRes id: Int): String {
    return resources.openRawResource(id).bufferedReader().use { it.readText() }
}
