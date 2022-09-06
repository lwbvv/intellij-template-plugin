package com.richone.fluttertemplate.action

import com.richone.fluttertemplate.generator.PresentationLayerGenerator
import com.richone.fluttertemplate.generator.PresentationLayerGeneratorFactory
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiDocumentManager
import org.jetbrains.kotlin.psi.KtPsiFactory

class PresentationAction : AnAction(), GenerateDialog.Listener {

    private lateinit var dataContext: DataContext

    override fun actionPerformed(e: AnActionEvent) {
        GenerateDialog(this, null).show()
    }

    override fun onGenerateClicked(name: String) {
        val blocGenerators = PresentationLayerGeneratorFactory.getBlocGenerators(name)
        val widgetComponentGenerators = PresentationLayerGeneratorFactory.getWidgetComponentsGenerators(name)
        generate(blocGenerators, widgetComponentGenerators)
    }

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    protected fun generate(blocGenerators: List<PresentationLayerGenerator>, widgetComponentGenerators: List<PresentationLayerGenerator>) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory
        ApplicationManager.getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                    project,
                    {
                        val presentationDir = directory!!.createSubdirectory("presentation")
                        val blocDir = presentationDir.createSubdirectory("bloc")
                        blocGenerators.forEach { createFile(project!!, it, blocDir) }
                        widgetComponentGenerators.forEach { createFile(project!!, it, presentationDir) }
                    },
                    "Generate a new Presentation",
                    null
            )
        }
    }

    private fun createFile(project: Project, generator: PresentationLayerGenerator, directory: PsiDirectory) {
        val fileName = generator.fileName()
        val existingPsiFile = directory.findFile(fileName)
        if (existingPsiFile != null) {
            val document = PsiDocumentManager.getInstance(project).getDocument(existingPsiFile)
            document?.insertString(document.textLength, "\n" + generator.generate())
            return
        }

        val ktPsiFactory = KtPsiFactory(project)
        directory.add(ktPsiFactory.createFile(fileName, generator.generate()))
    }
}