package com.richone.fluttertemplate.generator.components

import com.richone.fluttertemplate.generator.PresentationLayerGenerator

class ModelMapperGenerator(name: String) : PresentationLayerGenerator(name, templateName = "model_mapper") {

    override fun fileName() = "${snakeCase()}_model_mapper.${fileExtension()}"
}