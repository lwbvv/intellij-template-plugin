package com.richone.fluttertemplate.generator

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase
import com.fleshgrinder.extensions.kotlin.toUpperCamelCase
import com.google.common.io.CharStreams
import org.apache.commons.lang.text.StrSubstitutor
import java.io.InputStreamReader
import java.lang.RuntimeException

abstract class PresentationLayerGenerator(private var name: String, templateName: String) {
    private val TEMPLATE_PASCAL_CASE = "pascal_case"
    private val TEMPLATE_SNAKE_CASE = "snake_case"

    private val templateString: String
    private val templateValues: MutableMap<String, String>

    init {
        templateValues = mutableMapOf(
                TEMPLATE_PASCAL_CASE to pascalCase(),
                TEMPLATE_SNAKE_CASE to snakeCase()
        )
        try {
            val templateFolder = if (templateName.contains("bloc")) "presentation_layer/bloc" else "presentation_layer"
            val resource = "/templates/$templateFolder/$templateName.dart.template"
            val resourceAsStream = PresentationLayerGenerator::class.java.getResourceAsStream(resource)
            templateString = CharStreams.toString(InputStreamReader(resourceAsStream, Charsets.UTF_8))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


    abstract fun fileName(): String

    fun generate(): String {
        val substitutor = StrSubstitutor(templateValues)
        return substitutor.replace(templateString)
    }

    fun pascalCase(): String = name.toUpperCamelCase()

    fun snakeCase() = name.toLowerSnakeCase()

    fun fileExtension() = "dart"
}