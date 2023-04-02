package com.runekit

import org.jdesktop.swingx.JXButton
import org.jdesktop.swingx.JXPanel
import java.awt.*
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import java.io.File
import java.net.URI
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.imageio.ImageIO
import javax.swing.*


/**
 * @author Alycia <https://github.com/alycii>
 */

const val appTitle: String = "2011Scape"
const val discordLink: String = "https://discord.gg/jDbBAKjhxh"
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


fun flipIcon(icon: Icon) : Icon {
    val image = (icon as ImageIcon).image
    val newIcon = ImageIcon(
        image.getScaledInstance(
            icon.getIconWidth(),
            icon.getIconHeight(),
            Image.SCALE_DEFAULT
        )
    )
    newIcon.image = createFlippedImage(image)
    return newIcon
}
fun createFlippedImage(image: Image): Image {
    val bufferedImage = BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB)
    val g = bufferedImage.createGraphics()
    g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null)
    val tx = AffineTransform.getScaleInstance(-1.0, 1.0)
    tx.translate(-image.getWidth(null).toDouble(), 0.0)
    val op = AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
    return op.filter(bufferedImage, null)
}

fun openLink(url: String) {
    if (Desktop.isDesktopSupported()) {
        val desktop = Desktop.getDesktop()
        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(URI(url))
        }
    }
}

fun captureScreenshot() {
    try {
        // Create a Robot instance to capture the screen
        val robot = Robot()

        // Get the size of the screen
        val screenSize = RuneKit.bounds

        // Create a rectangle to represent the entire screen
        val screenRect = Rectangle(screenSize)

        // Capture the screen image
        val image = robot.createScreenCapture(screenRect)

        // Format the date to include in the filename
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
        val filename = "Screenshot-$date.png"

        // Create a file to save the screenshot to
        val file = File("./screenshots/$filename")

        // Write the image to the file as a PNG
        ImageIO.write(image, "png", file)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}