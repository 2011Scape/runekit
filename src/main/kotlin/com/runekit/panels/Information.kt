package com.runekit.panels

import com.runekit.frameDimensions
import com.runekit.panel
import org.jdesktop.swingx.JXPanel
import java.awt.Dimension

/**
 * @author Alycia <https://github.com/alycii>
 */


/**
 * A custom JPanel that displays information.
 */
object Information {


    /** The original size of the panel. */
    val originalSize = Dimension(233, 510)

    /** The x offset based on the navigation bar width. */
    const val NAV_OFFSET_X = 40

    /**
     * The y offset based on the frames height
     */
    const val FRAME_OFFSET_Y = 29

    fun build(): JXPanel {
        return panel {
            bounds = bounds.apply {
                x = frameDimensions.width - originalSize.width - NAV_OFFSET_X
                y = FRAME_OFFSET_Y
                width = originalSize.width
                height = frameDimensions.height - FRAME_OFFSET_Y
            }
        }
    }

}
