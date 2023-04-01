package com.runekit.panels

import com.jagex.RS2Applet
import com.runekit.backgroundColor
import com.runekit.borderColor
import com.runekit.frameDimensions
import com.runekit.pluginView
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.JPanel

/**
 * @author Alycia <https://github.com/alycii>
 */


/**
 * This class represents a JPanel that contains a Java applet. It includes functionality to refresh the applet when the
 * panel is resized.
 */
class Applet : JPanel() {

    companion object {
        /** The original size of the applet. */
        val originalSize = Dimension(774, 506)

        /** The y offset based on the navigation bar height. */
        const val NAV_OFFSET_Y = 32

        /**
         * The y offset based on the frames height
         */
        const val FRAME_OFFSET_Y = 34

        /**
         * The x offset
         */
        const val X_OFFSET = 3
    }

    /** The Java applet to display on the panel. */
    private val applet = RS2Applet().apply {
        loadApplet()
        preferredSize = originalSize
        minimumSize = originalSize
        size = originalSize
    }

    /**
     * Initializes the panel by setting its bounds, background color, and adding the applet to the center of the
     * panel.
     */
    init {
        setBounds(X_OFFSET, NAV_OFFSET_Y, frameDimensions.width - if(pluginView) 278 else 6, frameDimensions.height - FRAME_OFFSET_Y)
        background = backgroundColor
        border = BorderFactory.createLineBorder(borderColor)
        layout = BorderLayout()
        add(applet, BorderLayout.CENTER)
        isVisible = true
    }

    /**
     * Refreshes the applet when the panel is resized by updating the border, setting the new bounds, and resizing the
     * applet.
     */
    fun refresh() {
        val newWidth = frameDimensions.width - if(pluginView) 278 else 6
        val newHeight = frameDimensions.height - FRAME_OFFSET_Y
        border = null
        setBounds(X_OFFSET, NAV_OFFSET_Y, newWidth, newHeight)
        if(pluginView) {
            border = BorderFactory.createLineBorder(borderColor)
        }
        applet.size = Dimension(newWidth, newHeight)
        applet.repaint()
        applet.revalidate()
    }

}
