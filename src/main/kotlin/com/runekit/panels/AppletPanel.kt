package com.runekit.panels

import com.jagex.RS2Loader
import com.runekit.frameDimensions
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

/**
 * @author Alycia <https://github.com/alycii>
 */
class AppletPanel : JPanel() {

    companion object {
        val originalSize = Dimension(765, 503)

        // The y offset is based on the navigation bar height
        const val OFFSET_Y = 34
    }

    private val applet = RS2Loader().apply {
        loadApplet()
        preferredSize = originalSize
        minimumSize = originalSize
        size = originalSize
    }

    init {
        setBounds(3, OFFSET_Y, frameDimensions.width - 283, frameDimensions.height - OFFSET_Y - 3)
        background = Color.decode("#000000")
        layout = BorderLayout()
        add(applet, BorderLayout.CENTER)
        isVisible = true
    }
    fun refresh() {
        val newWidth = frameDimensions.width - 283
        val newHeight = frameDimensions.height - OFFSET_Y - 3
        setBounds(3, OFFSET_Y, newWidth, newHeight)
        applet.preferredSize = Dimension(newWidth, newHeight)
        applet.minimumSize = Dimension(newWidth, newHeight)
        applet.size = Dimension(newWidth, newHeight)
    }

}
