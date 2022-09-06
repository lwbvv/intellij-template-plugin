package com.richone.fluttertemplate.action

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class GenerateDialog(private val listener: Listener, project: Project?) : DialogWrapper(project) {
    private val contentPanel: JPanel = JPanel(BorderLayout()).apply {
        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)
    }
    private val nameLabel: JLabel = JLabel("Name: ").apply {
        this.preferredSize = Dimension(100, 50)
    }
    private val nameTextField: JTextField = JTextField()


    init {
        init()
    }

    override fun createCenterPanel(): JComponent {
        contentPanel.add(nameLabel)
        contentPanel.add(nameTextField)
        return contentPanel
    }

    override fun doOKAction() {
        super.doOKAction()
        println("text trxt: ${nameTextField.text}")
        listener.onGenerateClicked(nameTextField.text)
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return nameTextField
    }

    interface Listener {
        fun onGenerateClicked(name: String)
    }
}