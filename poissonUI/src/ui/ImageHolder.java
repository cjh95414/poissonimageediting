/*
 * ImageHolder.java
 * 
 * Created on Oct 28, 2007, 11:57:46 AM
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @author Olivier Bilodeau <olivier.bilodeau.1@gmail.com>, Kim Lebel
 *         <lebel.kim@gmail.com>, Jean-Philippe Plante
 *         <jphilippeplante@gmail.com>, Francois Proulx
 *         <francois.proulx@gmail.com>
 */
public class ImageHolder {

	private BufferedImage scaledImage;

	private BufferedImage original;

	public ImageHolder(BufferedImage originalImage) {
		this.original = originalImage;
		this.scaledImage = getImage(ImageBrowser.currentSize);
	}

	public BufferedImage getScaledImage() {
		return scaledImage;
	}

	public BufferedImage getOriginal() {
		return original;
	}

	/**
	 * This method returns an image with the specified width. It finds the
	 * pre-scaled size with the closest/larger width and scales down from it, to
	 * provide a fast and high-quality scaed version at the requested size.
	 */
	BufferedImage getImage(int width) {
		float scaleFactor = (float) width / original.getWidth();
		int scaledH = (int) (original.getHeight() * scaleFactor);

		BufferedImage img = new BufferedImage(width, scaledH, original
				.getType());
		Graphics2D g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.drawImage(original, 0, 0, width, scaledH, null);
		g2d.dispose();

		return img;
	}
}
