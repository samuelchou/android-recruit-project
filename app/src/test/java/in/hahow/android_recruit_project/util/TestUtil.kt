package `in`.hahow.android_recruit_project.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Read from resources folder and get string back.
 * @param fileName the file name of mock response. (type is needed. ex. "response.json")
 * @param filePathSuffix the folder path of mock response file. (last slash is needed. ex. "response-raw/")
 */
fun Any.readStringFromResourcesFile(
    fileName: String, filePathSuffix: String,
): String {
    val classLoader = javaClass.classLoader!!
    val inputStream: InputStream = classLoader.getResource("$filePathSuffix$fileName").openStream()
        ?: throw NullPointerException("Cannot find file $filePathSuffix$fileName")
    inputStream.use {
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(it))
        var str: String? = reader.readLine()
        while (str != null) {
            builder.append(str)
            str = reader.readLine()
        }
        return builder.toString()
    }
}