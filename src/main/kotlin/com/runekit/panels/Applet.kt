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
 * This class represents a JPanel that contains a Java applet. It includes functionality to refresh the applet when the
 * panel is resized.
 * @author Alycia <https://github.com/alycii>
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

    /**
     * The offset for if plugins are being viewed
     */
    private var pluginOffset = if (pluginView) 278 else 6

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

        // Set the panels bounds
        bounds = bounds.apply {
            x = X_OFFSET
            y = NAV_OFFSET_Y
            width = frameDimensions.width - pluginOffset
            height = frameDimensions.height - FRAME_OFFSET_Y
        }

        // Set the background color
        background = backgroundColor

        // Set the border
        border = BorderFactory.createLineBorder(borderColor)

        // Add the layout and applet
        layout = BorderLayout()
        add(applet, BorderLayout.CENTER)

        // Display
        isVisible = true
    }

    /**
     * Refreshes the applet when the panel is resized by updating the border, setting the new bounds, and resizing the
     * applet.
     */
    fun refresh() {

        // Reset the plugin offset
        pluginOffset = if (pluginView) 278 else 6


        // Reset the border
        border = null

        // Set the panels bounds
        bounds = bounds.apply {
            x = X_OFFSET
            y = NAV_OFFSET_Y
            width = frameDimensions.width - pluginOffset
            height = frameDimensions.height - FRAME_OFFSET_Y
        }

        // Only show the border if the plugins are in view
        if (pluginView) {
            border = BorderFactory.createLineBorder(borderColor)
        }

        // Finally, resize the applet
        applet.size = size
        applet.repaint()
        applet.revalidate()
    }

}
