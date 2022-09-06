package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class BuilderGenerator(name: String) : PresentationLayerGenerator(name, templateName = "builder") {

    override fun fileName() = "${snakeCase()}_builder.${fileExtension()}"
}