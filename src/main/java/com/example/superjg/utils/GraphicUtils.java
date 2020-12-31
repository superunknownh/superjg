package com.example.superjg.utils;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GraphicUtils {

	/**
	 * Draw a string centered in the middle of a Rectangle.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 * @param font The font to be used.
	 */
	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}

	/**
	 * Draw a string in the left upper corner of a Rectangle.
	 *
	 * @param g    The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 * @param font The font to be used.
	 */
	public static void drawLeftUpperCornerString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = 0;
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + (metrics.getHeight() / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}

	/**
	 * Draw a string in the right upper corner of a Rectangle.
	 * 
	 * @param g    The Graphics instance.
	 * @param text The string to draw.
	 * @param rect The Rectangle to determine the string position.
	 * @param font The font to be used.
	 */
	public static void drawRightUpperCornerString(Graphics g, String text, Rectangle rect, Font font) {
		FontMetrics metrics = g.getFontMetrics(font);
		// Determine the X coordinate for the text
		int x = rect.x + (rect.width - metrics.stringWidth(text));
		// Determine the Y coordinate for the text (note we add the ascent, as in java
		// 2d 0 is top of the screen)
		int y = rect.y + (metrics.getHeight() / 2) + metrics.getAscent();
		g.setFont(font);
		g.drawString(text, x, y);
	}

}
