package com.runekit.panels

import com.runekit.BACKGROUND_COLOR
import com.runekit.MAIN_FRAME_SIZE
import com.runekit.buildIcon
import javax.swing.JPanel


/**
 * @author Alycia <https://github.com/alycii>
 */
class NavigationPanel : JPanel() {
    init {
        val width = MAIN_FRAME_SIZE.width
        setBounds(0, 0, width, 30)
        background = BACKGROUND_COLOR
        layout = null
        add(buildIcon("/menu-bar.png", width, 30))
        isVisible = true
    }
}