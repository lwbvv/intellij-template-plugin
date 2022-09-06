package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class BlocStateGenerator(name: String) : PresentationLayerGenerator(name, templateName = "bloc_state") {

    override fun fileName() = "${snakeCase()}_bloc_state.${fileExtension()}"
}