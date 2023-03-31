package com.runekit

import java.awt.Color
import java.awt.Dimension
import java.awt.Image
import javax.swing.ImageIcon
import javax.swing.JLabel

/**
 * @author Alycia <https://github.com/alycii>
 */

val BACKGROUND_COLOR = Color.decode("#181818")
var MAIN_FRAME_SIZE = Dimension(1050, 530)

fun buildIcon(location: String, width: Int, height: Int, x: Int = 0, y: Int = 0) : JLabel {
    val background = JLabel()
    val image = ImageIcon(RuneKit::class.java.getResource(location))
    background.icon = resizeIcon(image, width, height)
    background.setBounds(x, y, width, height)
    return background
}

fun resizeIcon(icon: ImageIcon, width: Int, height: Int): ImageIcon? {
    val img = icon.image
    val resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH)
    return ImageIcon(resizedImg)
}