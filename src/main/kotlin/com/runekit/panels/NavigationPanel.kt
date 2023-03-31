package com.runekit.panels

import com.runekit.backgroundColor
import com.runekit.frameDimensions
import com.runekit.buildIcon
import javax.swing.JPanel


/**
 * @author Alycia <https://github.com/alycii>
 */
class NavigationPanel : JPanel() {
    init {
        val width = frameDimensions.width
        setBounds(0, 0, width, 30)
        background = backgroundColor
        layout = null
        add(buildIcon("/menu-bar.png", width, 30))
        isVisible = true
    }
}