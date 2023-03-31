package com.runekit.panels

import com.runekit.backgroundColor
import com.runekit.borderColor
import com.runekit.frameDimensions
import java.awt.Dimension
import javax.swing.BorderFactory
import javax.swing.JPanel

/**
 * @author Alycia <https://github.com/alycii>
 */


/**
 * A custom JPanel that displays information.
 */
class Information : JPanel() {


    companion object {

        /** The original size of the panel. */
        val originalSize = Dimension(233, 510)

        /** The x offset based on the navigation bar width. */
        const val NAV_OFFSET_X = 40

        /**
         * The y offset based on the frames height
         */
        const val FRAME_OFFSET_Y = 29

    }

    /**
     * Constructs an Information panel instance with a fixed size and position on the screen.
     */
    init {

        // Set the bounds of the panel based on the screen dimensions and original size
        setBounds(
            frameDimensions.width - originalSize.width - NAV_OFFSET_X,
            FRAME_OFFSET_Y,
            originalSize.width,
            frameDimensions.height - FRAME_OFFSET_Y
        )

        // Set the background color of the panel
        background = backgroundColor

        // Set the border of the panel
        border = BorderFactory.createLineBorder(borderColor)

        // Set the visibility of the panel
        isVisible = true
    }

}
