package com.runekit

import com.runekit.panels.Applet
import com.runekit.panels.Information
import com.runekit.panels.Plugins
import com.runekit.panels.Title
import org.ngrinder.recorder.ui.component.ComponentResizer
import java.awt.Dimension
import java.awt.Insets
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JFrame
import javax.swing.JPanel

/**
 * @author Alycia <https://github.com/alycii>
 */


/**
 * The main application frame for the RuneKit application.
 *
 * This frame contains the main panels that make up the application.
 * It is responsible for handling resize events, rebuilding the UI when the frame size changes.
 */
object RuneKit : JFrame() {

    // List of main panels
    private val panels = mutableListOf<JPanel>()

    // Applet panel
    private val applet = Applet()

    // Main content panel for the frame
    private var content = panel {}

    /**
     * Constructs the main frame for the application.
     * Initializes the panels and sets up the frame defaults.
     */
    init {

        // Set the content pane for the frame
        contentPane = content

        // Add the game applet
        content.add(applet)

        // Set the frame defaults
        title = appTitle
        isUndecorated = true
        size = frameDimensions
        minimumSize = minimumSize.apply {
            width = 780
            height = 540
        }
        setLocationRelativeTo(null)
        isVisible = true


        // Rebuild the main frame
        rebuildMain()

        // Set up the component resizer for the frame
        ComponentResizer().run {
            dragInsets = Insets(5, 5, 5, 5)
            registerComponent(this@RuneKit)
        }

        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
               if(manuallyResizing) {
                    rebuildMain()
               } else {
                   manuallyResizing = true
               }
            }
        })
    }

    /**
     * Rebuilds the main frame when the frame size changes.
     */
    fun rebuildMain() {
        frameDimensions = when {
            !pluginView && !manuallyResizing -> Dimension(width - 270, height)
            pluginView && !manuallyResizing -> Dimension(width + 274, height)
            else -> Dimension(width, height)
        }
        bounds = bounds.apply {
            width = frameDimensions.width
            height = frameDimensions.height
        }
        content.components.filter { it != applet }.forEach(content::remove)
        rebuildPanels()
        applet.refresh()
        repaint()
        revalidate()
    }


    /**
     * Rebuilds the list of frame panels and updates the content pane of the frame.
     * Clears the current list of panels and adds new instances.
     */
    private fun rebuildPanels() {
        panels.clear()
        panels.add(Title.build())
        if(pluginView) {
            panels.add(Plugins.build())
            panels.add(Information.build())
        }
        panels.forEach(content::add)
    }

}
