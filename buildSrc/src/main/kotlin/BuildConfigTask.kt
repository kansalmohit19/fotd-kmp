import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.FileWriter

abstract class BuildConfigTask : DefaultTask() {
    @get:InputFiles
    abstract val inputDirectories: ConfigurableFileCollection

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun buildConfig() {
        val gson = GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
        val outputJson = JsonObject()
        inputDirectories.forEach { directory ->
            val directoryJson = JsonObject()
            directory.walkBottomUp().forEach { file ->
                if (file.isFile && file.extension == "json") {
                    try {
                        val element =
                            file.inputStream().bufferedReader().use { JsonParser.parseReader(it) }

                        if (!element.isJsonObject) {
                            println("Skipping ${file.name}: JSON is not an object")
                            return@forEach
                        }

                        val node = element.asJsonObject

                        for (key in node.keySet()) {
                            directoryJson.add(key, node[key])
                        }
                    } catch (e: Exception) {
                        // invalid JSON syntax
                        println("Skipping ${file.name}: Invalid JSON (${e.message})")
                    }
                }
            }
            for (key in directoryJson.keySet()) {
                outputJson.add(key, directoryJson[key])
            }
        }

        FileWriter(outputFile.get().asFile, Charsets.UTF_8).use {
            gson.toJson(outputJson, it)
        }
    }
}