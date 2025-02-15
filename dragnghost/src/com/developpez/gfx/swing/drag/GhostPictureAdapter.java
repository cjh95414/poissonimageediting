package com.developpez.gfx.swing.drag;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 * TODO fix Javadoc: not sure about this one 
 */
public class GhostPictureAdapter extends GhostDropAdapter
{
	private BufferedImage image;

	/**
	 * Public constructor
	 * @param glassPane pane where the drawing takes place
	 * @param action action fired on drop
	 * @param picture TODO picture string?
	 */
	public GhostPictureAdapter(GhostGlassPane glassPane, String action, String picture) {
	   super(glassPane, action);
	   try {
	       this.image = ImageIO.read(new BufferedInputStream(GhostPictureAdapter.class.getResourceAsStream(picture)));
	   } catch (MalformedURLException mue) {
	       throw new IllegalStateException("Invalid picture URL.");
	   } catch (IOException ioe) {
           throw new IllegalStateException("Invalid picture or picture URL.");
       }
	}

    public void mousePressed(MouseEvent e)
    {
        Component c = e.getComponent();

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