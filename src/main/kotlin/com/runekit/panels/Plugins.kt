package com.runekit.panels

import com.runekit.frameDimensions
import com.runekit.panel
import org.jdesktop.swingx.JXPanel
import java.awt.Dimension

/**
 * @author Alycia <https://github.com/alycii>
 */
object Plugins {


    /** The original size of the applet. */
    val originalSize = Dimension(41, 510)

    /**
     * The y offset based on the frames height
     */
    const val FRAME_OFFSET_Y = 29


    fun build(): JXPanel {
        return panel {
            bounds = bounds.apply {
                x = frameDimensions.width - originalSize.width
                y = FRAME_OFFSET_Y
                width = originalSize.width
                height = frameDimensions.height - FRAME_OFFSET_Y
            }
        }
    }

}