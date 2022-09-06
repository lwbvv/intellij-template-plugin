package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class WidgetModelGenerator(name: String) : PresentationLayerGenerator(name, templateName = "widget_model") {

    override fun fileName() = "${snakeCase()}_widget_model.${fileExtension()}"
}