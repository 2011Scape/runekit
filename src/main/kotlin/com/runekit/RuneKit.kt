package com.runekit

import com.runekit.panels.AppletPanel
import com.runekit.panels.NavigationPanel
import com.runekit.panels.InformationPanel
import org.ngrinder.recorder.ui.component.ComponentResizer
import java.awt.CardLayout
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
class RuneKit : JFrame() {

    // List of main panels
    private val framePanels = mutableListOf<JPanel>()

    // Applet panel
    private val applet = AppletPanel()

    // Main content panel for the frame
    private val content = JPanel(CardLayout()).apply {
        background = backgroundColor
        layout = null
    }

    /**
     * Constructs the main frame for the application.
     * Initializes the panels and sets up the frame defaults.
     */
    init {

        // Set the content pane for the frame
        contentPane = content
        rebuildPanels()

        // Add the game applet
        content.add(applet)

        // Set the frame defaults
        title = "2011Scape - Powered by RuneKit"
        isUndecorated = true
        size = frameDimensions
        minimumSize = frameDimensions
        setLocationRelativeTo(null)
        isVisible = true

        // Set up the component resizer for the frame
        ComponentResizer().run {
            dragInsets = Insets(5, 5, 5, 5)
            registerComponent(this@RuneKit)
        }

        // Add a component listener for resize events
        addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                rebuildMain()
                applet.refresh()
            }
        })

    }

    /**
     * Rebuilds the main frame when the frame size changes.
     */
    private fun rebuildMain() {
        frameDimensions = Dimension(width, height)
        content.components.filter { it != applet }.forEach(content::remove)
        rebuildPanels()
    }

    /**
     * Rebuilds the list of frame panels and updates the content pane of the frame.
     * Clears the current list of panels and adds new instances.
     */
    private fun rebuildPanels() {
        framePanels.clear()
        framePanels.add(NavigationPanel())
        framePanels.add(InformationPanel())
        framePanels.forEach(content::add)
    }

}
