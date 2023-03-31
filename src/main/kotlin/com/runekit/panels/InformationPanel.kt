package com.runekit.panels

import com.runekit.backgroundColor
import com.runekit.frameDimensions
import com.runekit.buildIcon
import java.awt.Dimension
import javax.swing.JPanel

/**
 * @author Alycia <https://github.com/alycii>
 */
class InformationPanel : JPanel() {

    init {
        val originalSize = Dimension(277, 520)

        // The y offset is based on the navigation bar height
        val offsetY = 30

        setBounds(frameDimensions.width - originalSize.width, offsetY, originalSize.width, frameDimensions.height - offsetY)
        background = backgroundColor
        layout = null
        add(buildIcon("/side-bar.png", width, frameDimensions.height - 30))
        isVisible = true
    }

}