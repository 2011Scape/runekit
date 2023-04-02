package com.runekit.panels

import com.runekit.*
import org.jdesktop.swingx.JXPanel
import java.awt.Color
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import javax.swing.JFrame
import javax.swing.SwingUtilities
import kotlin.system.exitProcess


/**
 * The title bar for the main application frame.
 * @author Alycia <https://github.com/alycii>
 */
object Title {

    private const val PANEL_HEIGHT = 30

    private var dragOffset: Point? = null

    /**
     * Builds the title bar panel with all necessary components.
     *
     * @return the built title bar panel
     */
    fun build(): JXPanel {
        return panel {
            // Set bounds for the panel
            bounds = bounds.apply {
                width = frameDimensions.width
                height = PANEL_HEIGHT
            }

            // Add the label for the application title
            label {
                text = appTitle
                bounds = bounds.apply {
                    x = 15
                    width = frameDimensions.width
                    height = PANEL_HEIGHT
                }
                foreground = Color.decode("#c8bd9e")
            }

            // Add the close button
            button(icon("/title-bar/close.png"), icon("/title-bar/close-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 30
                    y = 7
                }
                addActionListener {
                    exitProcess(0)
                }
            }

            // Add the maximize button
            button(icon("/title-bar/maximize.png"), icon("/title-bar/maximize-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 55
                    y = 7
                }
            }

            // Add the minimize button
            button(icon("/title-bar/minimize.png"), icon("/title-bar/minimize-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 80
                    y = 7
                }
                addActionListener {
                    SwingUtilities.getWindowAncestor(this@panel)?.let { window ->
                        if (window is JFrame) {
                            window.extendedState = JFrame.ICONIFIED
                        }
                    }
                }
            }

            // Add the divider label
            label {
                text = "."
                bounds = bounds.apply {
                    x = frameDimensions.width - 95
                    y = 2
                    width = 5
                    height = 20
                }
                foreground = Color.decode("#c8bd9e")
            }

            // Add the plugin button
            button(icon("/title-bar/plugin.png"), icon("/title-bar/plugin-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 120
                    y = 6
                }

                // Flip the icon if the plugin view is currently enabled
                icon = if (pluginView) flipIcon(icon) else icon
                rolloverIcon = if (pluginView) flipIcon(rolloverIcon) else rolloverIcon

                addActionListener {
                    pluginView = !pluginView
                    manuallyResizing = false
                    RuneKit.rebuildMain()
                }
            }

            // Add the Discord button
            button(icon("/title-bar/discord.png"), icon("/title-bar/discord-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 145
                    y = 6
                }
                addActionListener {
                    openLink(discordLink)
                }
            }

            // Add the Screenshot button
            button(icon("/title-bar/camera.png"), icon("/title-bar/camera-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 170
                    y = 6
                }
                addActionListener {
                    captureScreenshot()
                }
            }


            addMouseListener(object : MouseAdapter() {
                override fun mousePressed(e: MouseEvent) {
                    // Store the offset between the mouse position and the top-left corner of the panel
                    dragOffset = e.point
                }

                override fun mouseReleased(e: MouseEvent) {
                    // Reset the drag offset
                    dragOffset = null
                }
            })

            addMouseMotionListener(object : MouseMotionAdapter() {
                override fun mouseDragged(e: MouseEvent) {
                    dragOffset?.let {
                        // Calculate the new position of the JFrame based on the current mouse position and the drag offset
                        val newX = e.xOnScreen - it.x
                        val newY = e.yOnScreen - it.y
                        SwingUtilities.getWindowAncestor(this@panel)?.let { window ->
                            window.location = Point(newX, newY)
                        }
                    }
                }
            })
        }
    }
}