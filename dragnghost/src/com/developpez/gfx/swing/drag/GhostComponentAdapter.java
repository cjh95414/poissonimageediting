package com.developpez.gfx.swing.drag;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

/**
 * Adapter class that handles events related to the translucent drag and drop.
 * TODO not sure about this javadoc 
 * TODO this class doesn't seem to be defined in this project. Consider removal? But before we must make sure we are not doing something stupid in our reimplementation of this class (like why do we have ImageFrameMouseListener and SelectionBrowserMouseListener, should it be merged here?)
 */
public class GhostComponentAdapter extends GhostDropAdapter
{
	/**
	 * Instantiate a GhostComponentAdapter
	 * @param glassPane a glasspane where the transparent drag and drop is drawn 
	 * @param action TODO !? remove?
	 */
    public GhostComponentAdapter(GhostGlassPane glassPane, String action) {
        super(glassPane, action);
    }

    public void mousePressed(MouseEvent e)
    {
        Component c = e.getComponent();

        BufferedImage image = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        c.paint(g);

        glassPane.setVisible(true);

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);
        SwingUtilities.convertPointFromScreen(p, glassPane);

        glassPane.setPoint(p);
        glassPane.setImage(image);
        glassPane.repaint();
    }

    public void mouseReleased(MouseEvent e)
    {
        Component c = e.getComponent();

        Point p = (Point) e.getPoint().clone();
        SwingUtilities.convertPointToScreen(p, c);

        Point eventPoint = (Point) p.clone();
        SwingUtilities.convertPointFromScreen(p, glassPane);

        glassPane.setPoint(p);
        glassPane.setVisible(false);
        glassPane.setImage(null);

        fireGhostDropEvent(new GhostDropEvent(action, eventPoint));
    }
}