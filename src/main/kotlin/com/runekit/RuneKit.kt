package com.runekit

import com.runekit.panels.NavigationBar
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * @author Alycia <https://github.com/alycii>
 */
class RuneKit : JFrame() {

    private val panels = mutableListOf<JPanel>()
    init {

        // build any main panels
        buildPanels()

        // build the content pane
        val content = JPanel().apply {
            background = BACKGROUND_COLOR
            layout = null
        }

        contentPane = content

        // add any main panels
        panels.forEach {
            content.add(it)
        }

        // set frame defaults
        title = "2011Scape - Powered by RuneKit"
        isUndecorated = true
        size = MAIN_FRAME_SIZE
        setLocationRelativeTo(null)
        isVisible = true
    }

    private fun buildPanels() {
        panels.add(NavigationBar())
    }
}