package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class BlocGenerator(name: String) : PresentationLayerGenerator(name, templateName = "bloc") {

    override fun fileName() = "${snakeCase()}_bloc.${fileExtension()}"
}