package com.runekit.panels

import com.runekit.backgroundColor
import com.runekit.borderColor
import com.runekit.frameDimensions
import javax.swing.BorderFactory
import javax.swing.JPanel


/**
 * @author Alycia <https://github.com/alycii>
 */
class Title : JPanel() {

    companion object {

        /** The height of the title bar. */
        const val HEIGHT = 30
    }

    init {
        val width = frameDimensions.width


        // Set the bounds of the panel based on the screen dimensions and original size
        setBounds(
            0, 0, width, HEIGHT
        )

        // Set the background color of the panel
        background = backgroundColor

        // Set the border of the panel
        border = BorderFactory.createLineBorder(borderColor)

        // Set the visibility of the panel
        isVisible = true
    }
}