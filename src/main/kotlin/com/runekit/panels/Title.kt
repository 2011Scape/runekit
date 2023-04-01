package com.runekit.panels

import com.runekit.*
import org.jdesktop.swingx.JXPanel
import java.awt.Color
import java.awt.Point
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import javax.swing.SwingUtilities


/**
 * @author Alycia <https://github.com/alycii>
 */
object Title {

    private const val panelHeight: Int = 30

    private var dragOffset: Point? = null

    fun build(): JXPanel {
        return panel {
            bounds = bounds.apply {
                width = frameDimensions.width
                height = panelHeight
            }

            label {
                text = appTitle
                bounds = bounds.apply {
                    x = 15
                    width = frameDimensions.width
                    height = panelHeight
                }
                foreground = Color.decode("#c8bd9e")
            }

            button(icon("/close.png"), icon("/close-hover.png")) {
                bounds = bounds.apply {
                    x = frameDimensions.width - 30
                    y = 6
                }
                addActionListener {
                    pluginView = !pluginView
                    manuallyResizing = false
                    RuneKit.rebuildMain()
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