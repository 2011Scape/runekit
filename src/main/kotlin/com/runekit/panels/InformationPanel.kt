package com.runekit.panels

import com.runekit.BACKGROUND_COLOR
import com.runekit.MAIN_FRAME_SIZE
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

        setBounds(MAIN_FRAME_SIZE.width - originalSize.width, offsetY, originalSize.width, MAIN_FRAME_SIZE.height - offsetY)
        background = BACKGROUND_COLOR
        layout = null
        add(buildIcon("/side-bar.png", width, MAIN_FRAME_SIZE.height - 30))
        isVisible = true
    }

}