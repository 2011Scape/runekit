package com.runekit

import org.jdesktop.swingx.JXButton
import org.jdesktop.swingx.JXPanel
import java.awt.Color
import java.awt.Dimension
import java.awt.Image
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel

/**
 * @author Alycia <https://github.com/alycii>
 */

const val appTitle: String = "2011Scape - Powered by RuneKit"
val borderColor: Color = Color.decode("#49422d")
val backgroundColor: Color = Color.decode("#181818")
var pluginView = true
var frameDimensions = Dimension(if(pluginView) 1050 else 780, 540)
var manuallyResizing = true

fun buildIcon(location: String, width: Int, height: Int, x: Int = 0, y: Int = 0) : JLabel {
    val background = JLabel()
    val image = ImageIcon(RuneKit::class.java.getResource(location))
    background.icon = resizeIcon(image, width, height)
    background.setBounds(x, y, width, height)
    return background
}

fun resizeIcon(icon: ImageIcon, width: Int, height: Int): ImageIcon? {
    val img = icon.image
    val resizedImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT)
    return ImageIcon(resizedImg)
}

fun icon(path: String): ImageIcon {
    val icon = ImageIcon(RuneKit::class.java.getResource(path))
    return ImageIcon(icon.image)
}


fun panel(init: JXPanel.() -> Unit): JXPanel {
    val panel = JXPanel()
    panel.layout = null
    panel.background = backgroundColor
    panel.border = BorderFactory.createLineBorder(borderColor)
    init(panel)
    return panel
}

fun JXPanel.label(init: JLabel.() -> Unit) {
    val label = JLabel()
    add(label)
    label.init()
}

fun JXPanel.button(icon: ImageIcon, rolloverIcon: ImageIcon, init: JButton.() -> Unit): JXButton {
    val button = JXButton()
    button.border = null
    button.isContentAreaFilled = false
    button.isFocusPainted = false
    button.isOpaque = false
    button.icon = icon
    button.rolloverIcon = rolloverIcon
    button.bounds = bounds.apply {
        width = icon.iconWidth
        height = icon.iconHeight
    }
    add(button)
    init(button)
    return button
}