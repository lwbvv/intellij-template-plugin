package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class WidgetGenerator(name: String) : PresentationLayerGenerator(name, templateName = "widget") {

    override fun fileName() = "${snakeCase()}_widget.${fileExtension()}"
}