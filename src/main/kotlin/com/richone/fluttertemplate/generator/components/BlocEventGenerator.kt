package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class BlocEventGenerator(name: String) : PresentationLayerGenerator(name, templateName = "bloc_event") {

    override fun fileName() = "${snakeCase()}_bloc_event.${fileExtension()}"
}