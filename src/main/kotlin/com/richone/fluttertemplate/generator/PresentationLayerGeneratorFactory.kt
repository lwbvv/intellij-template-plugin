package com.richone.fluttertemplate.generator

import com.richone.fluttertemplate.generator.components.*

object PresentationLayerGeneratorFactory {
    fun getBlocGenerators(name: String): List<PresentationLayerGenerator> {
        val bloc = BlocGenerator(name)
        val event = BlocEventGenerator(name)
        val state = BlocStateGenerator(name)
        return listOf(bloc, event, state)
    }

    fun getWidgetComponentsGenerators(name: String): List<PresentationLayerGenerator> {
        val builder = BuilderGenerator(name)
        val widgetModel = WidgetModelGenerator(name)
        val widget = WidgetGenerator(name)
        return listOf(builder, widgetModel, widget)
    }
}