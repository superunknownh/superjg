package com.example.superjg.cfg.editor;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AppUtils {

	private AppUtils() {
	}

	@Deprecated
	public static JLabel createLeftAlignedJLabel(String message) {
		var label = new JLabel(message);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		return label;
	}
	
	@Deprecated
	public static JPanel createInputRow(String message, Component component) {
		var innerPanel = new JPanel(new BorderLayout());
		innerPanel.add(createLeftAlignedJLabel(message), BorderLayout.WEST);
		innerPanel.add(component, BorderLayout.CENTER);
		var panel = new JPanel();
		panel.add(innerPanel);
		return panel;
	}

}
