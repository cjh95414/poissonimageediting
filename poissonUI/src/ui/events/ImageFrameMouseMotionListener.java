package ui.events;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import ui.containers.Selection;

import com.developpez.gfx.swing.drag.GhostGlassPane;
import com.developpez.gfx.swing.drag.GhostMotionAdapter;

/**
 * 
 * @author Olivier Bilodeau <olivier.bilodeau.1@gmail.com>, Kim Lebel
 * <lebel.kim@gmail.com>, Jean-Philippe Plante
 * <jphilippeplante@gmail.com>, Francois Proulx
 * <francois.proulx@gmail.com>
 * 
 */

public class ImageFrameMouseMotionListener extends GhostMotionAdapter {
	
	private Selection selection;
	
	public ImageFrameMouseMotionListener(GhostGlassPane glassPane) {
		super(glassPane);
		// TODO Auto-generated constructor stub
	}
	
	public ImageFrameMouseMotionListener(GhostGlassPane glassPane, Selection selection) {
		super(glassPane);
		// TODO Auto-generated constructor stub
		
		this.selection = selection;
	}
	
	public void mouseDragged(MouseEvent e) {
		if (selection.isSelectionMode() == true) {
			if (!selection.getPoints().contains(e.getPoint())) {
	
				//System.out.println("mouseDragged at " + e.getPoint());
				selection.getPoints().add(e.getPoint());
				Graphics g = e.getComponent().getGraphics();
				g.drawRect(e.getPoint().x, e.getPoint().y, 1, 1);
	
			}
		}
		else
			super.mouseDragged(e);
	}


}
