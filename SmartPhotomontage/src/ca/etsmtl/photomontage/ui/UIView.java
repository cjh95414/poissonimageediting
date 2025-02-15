/*
 * SmartPhotomontage
 * Copyright (C) 2007
 * François Proulx, Olivier Bilodeau, Jean-Philippe Plante, Kim Lebel
 * http://poissonimageediting.googlecode.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package ca.etsmtl.photomontage.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.Timer;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;

import ca.etsmtl.photomontage.ui.containers.ImageFramesContainer;
import ca.etsmtl.photomontage.ui.containers.WindowItem;
import ca.etsmtl.photomontage.ui.controllers.MenuController;

import com.developpez.gfx.swing.drag.GhostGlassPane;

/**
 * The application's main frame.
 */
public class UIView extends FrameView implements Observer {
	
	/**
	 * Construtor for the main UIView
	 * @param app
	 */
	public UIView(SingleFrameApplication app) {
		super(app);
		
		// initialisation de tous les components
		initComponents();
	}

	/**
	 * Show About Box for our application
	 * 
	 * @param e
	 */
	@Action
	public void showAboutBox(ActionEvent e) {
		if (aboutBox == null) {
			JFrame mainFrame = UIApp.getApplication().getMainFrame();
			aboutBox = new UIAboutBox(mainFrame);
			aboutBox.setLocationRelativeTo(mainFrame);
		}
		UIApp.getApplication().show(aboutBox);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
		GhostGlassPane glassPane = new GhostGlassPane();
        getFrame().setGlassPane(glassPane);
		
		// Our code 
		//TODO: REfactor me please !
		
		// creation du image browser
		ImageBrowser.currentSize = 180;

		// ajouter UIView en temps que observer pour les containers de image
		// frame
		container.addObserver(this);
		
		mainPanel = new javax.swing.JPanel();
	
		mdi = new javax.swing.JDesktopPane();
		browser = new javax.swing.JPanel();
		rightpanel = new javax.swing.JPanel();
		menuBar = new javax.swing.JMenuBar();
		javax.swing.JMenu fileMenu = new javax.swing.JMenu();
		openFileMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
		WindowsMenu = new javax.swing.JMenu();
		javax.swing.JMenu helpMenu = new javax.swing.JMenu();
		javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
		statusPanel = new javax.swing.JPanel();
		javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
		statusMessageLabel = new javax.swing.JLabel();
		statusAnimationLabel = new javax.swing.JLabel();
		progressBar = new javax.swing.JProgressBar();

		mainPanel.setName("mainPanel"); // NOI18N

		browser.setAutoscrolls(true);
		browser.setName("browser"); // NOI18N
		browser.setPreferredSize(new java.awt.Dimension(200, 0));

		org.jdesktop.layout.GroupLayout browserLayout = new org.jdesktop.layout.GroupLayout(
				browser);
		browser.setLayout(browserLayout);
		browserLayout.setHorizontalGroup(browserLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 200,
				Short.MAX_VALUE));
		browserLayout.setVerticalGroup(browserLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 380,
				Short.MAX_VALUE));

		mdi.setName("mdi"); // NOI18N

		rightpanel.setName("rightpanel"); // NOI18N

		org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
				.getInstance(ca.etsmtl.photomontage.ui.UIApp.class).getContext().getResourceMap(
						UIView.class);

		//org.jdesktop.layout.GroupLayout rightpanelLayout = new org.jdesktop.layout.GroupLayout(rightpanel);
		//rightpanel.setLayout(rightpanelLayout);
//		rightpanelLayout.setHorizontalGroup(rightpanelLayout
//				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//				.add(selections,
//						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 196,
//						org.jdesktop.layout.GroupLayout.PREFERRED_SIZE);
//		rightpanelLayout.setVerticalGroup(rightpanelLayout.createParallelGroup(
//				org.jdesktop.layout.GroupLayout.LEADING).add(
//				org.jdesktop.layout.GroupLayout.TRAILING,
//				rightpanelLayout.createSequentialGroup()
//				.add(selections,
//								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
//								237, Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		
		mainPanelLayout.setHorizontalGroup(mainPanelLayout
						.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
						.add(mainPanelLayout
										.createSequentialGroup()
										.add(browser,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED)
										.add(mdi)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
										.add(rightpanel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(rightpanel,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
				org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.add(org.jdesktop.layout.GroupLayout.TRAILING, mdi).add(
						browser, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
						380, Short.MAX_VALUE));

		menuBar.setName("menuBar"); // NOI18N

		fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
		fileMenu.setName("fileMenu"); // NOI18N

		openFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_O,
				java.awt.event.InputEvent.CTRL_MASK));
		openFileMenuItem
				.setText(resourceMap.getString("openFileMenuItem.text")); // NOI18N
		openFileMenuItem.setName("openFileMenuItem"); // NOI18N
		openFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openFileMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(openFileMenuItem);

		saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(
				java.awt.event.KeyEvent.VK_S,
				java.awt.event.InputEvent.CTRL_MASK));
		saveMenuItem.setText(resourceMap.getString("saveMenuItem.text")); // NOI18N
		saveMenuItem.setName("saveMenuItem"); // NOI18N
		saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(saveMenuItem);

		javax.swing.ActionMap actionMap = org.jdesktop.application.Application
				.getInstance(ca.etsmtl.photomontage.ui.UIApp.class).getContext().getActionMap(
						UIView.class, this);
		exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
		exitMenuItem.setName("exitMenuItem"); // NOI18N
		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		WindowsMenu.setText(resourceMap.getString("WindowsMenu.text")); // NOI18N
		WindowsMenu.setName("WindowsMenu"); // NOI18N
		menuBar.add(WindowsMenu);

		helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
		helpMenu.setName("helpMenu"); // NOI18N

		aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
		aboutMenuItem.setName("aboutMenuItem"); // NOI18N
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		statusPanel.setName("statusPanel"); // NOI18N

		statusMessageLabel.setName("statusMessageLabel"); // NOI18N

		statusAnimationLabel
				.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

		progressBar.setName("progressBar"); // NOI18N

		org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(
				statusPanel);
		statusPanel.setLayout(statusPanelLayout);
		statusPanelLayout
				.setHorizontalGroup(statusPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(statusPanelSeparator,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								828, Short.MAX_VALUE)
						.add(
								statusPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.add(statusMessageLabel)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED,632, Short.MAX_VALUE)
										.add(progressBar,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
										.add(statusAnimationLabel)
										.addContainerGap()));
		statusPanelLayout
				.setVerticalGroup(statusPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(
								statusPanelLayout
										.createSequentialGroup()
										.add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												2,org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(statusPanelLayout
														.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
														.add(statusMessageLabel)
														.add(statusAnimationLabel)
														.add(progressBar,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
														.add(3, 3, 3)));

		setComponent(mainPanel);
		setMenuBar(menuBar);
		setStatusBar(statusPanel);

		// param�triser le imageframe et ajouter � l'interface
		imagebrowser = new ImageBrowser(container);
		JScrollPane imageBrowserScrollpane = new JScrollPane(imagebrowser);
		imageBrowserScrollpane.setLayout(new ScrollPaneLayout());
		imageBrowserScrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		imageBrowserScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		imageBrowserScrollpane.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);

		browser.setLayout(new BorderLayout());
		browser.add(imageBrowserScrollpane);
		
		// param�triser le imageframe et ajouter � l'interface
		selectionBrowser = new SelectionBrowser();
		JScrollPane selectionBrowserScrollpane = new JScrollPane(selectionBrowser );
		selectionBrowserScrollpane.setLayout(new ScrollPaneLayout());
		selectionBrowserScrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		selectionBrowserScrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		selectionBrowserScrollpane.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
		rightpanel.setLayout(new BorderLayout());
		rightpanel.add(selectionBrowserScrollpane);

		// status bar initialization - message timeout, idle icon and busy
		// animation, etc
		int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
		messageTimer = new Timer(messageTimeout, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				statusMessageLabel.setText("");
			}
		});
		messageTimer.setRepeats(false);
		int busyAnimationRate = resourceMap
				.getInteger("StatusBar.busyAnimationRate");
		for (int i = 0; i < busyIcons.length; i++) {
			busyIcons[i] = resourceMap
					.getIcon("StatusBar.busyIcons[" + i + "]");
		}
		busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
				statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
			}
		});
		idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
		statusAnimationLabel.setIcon(idleIcon);
		progressBar.setVisible(false);

		// connecting action tasks to status bar via TaskMonitor
		TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
		taskMonitor
				.addPropertyChangeListener(new java.beans.PropertyChangeListener() {

					public void propertyChange(
							java.beans.PropertyChangeEvent evt) {
						String propertyName = evt.getPropertyName();
						if ("started".equals(propertyName)) {
							if (!busyIconTimer.isRunning()) {
								statusAnimationLabel.setIcon(busyIcons[0]);
								busyIconIndex = 0;
								busyIconTimer.start();
							}
							progressBar.setVisible(true);
							progressBar.setIndeterminate(true);
						} else if ("done".equals(propertyName)) {
							busyIconTimer.stop();
							statusAnimationLabel.setIcon(idleIcon);
							progressBar.setVisible(false);
							progressBar.setValue(0);
						} else if ("message".equals(propertyName)) {
							String text = (String) (evt.getNewValue());
							statusMessageLabel.setText((text == null) ? ""
									: text);
							messageTimer.restart();
						} else if ("progress".equals(propertyName)) {
							int value = (Integer) (evt.getNewValue());
							progressBar.setVisible(true);
							progressBar.setIndeterminate(false);
							progressBar.setValue(value);
						}
					}
				});
	}

	private void openFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		menuCtrl.openFile(imagebrowser);
	}

	private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		if (mdi.getSelectedFrame() != null) {
			new Thread() {
				public void run() {
					//saving...
					statusMessageLabel.setText("Saving...");
					ImageFrame f = (ImageFrame) mdi.getSelectedFrame();
					String path = menuCtrl.saveFile(f.getImageHolder().getImage());
					statusMessageLabel.setText("");
					
					//set path and change image state
					f.getImageHolder().setFilename(path);
					f.setModified(false);
				}
			}.start();	
		}
	}

	// Variables declaration
	protected static javax.swing.JMenu WindowsMenu;
	private javax.swing.JPanel browser;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JDesktopPane mdi;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openFileMenuItem;
	private javax.swing.JProgressBar progressBar;
	private javax.swing.JPanel rightpanel;
	private javax.swing.JMenuItem saveMenuItem;
	private javax.swing.JLabel statusAnimationLabel;
	private javax.swing.JLabel statusMessageLabel;
	private javax.swing.JPanel statusPanel;

	private Timer messageTimer;
	private Timer busyIconTimer;
	private Icon idleIcon;
	private Icon[] busyIcons = new Icon[15];
	private int busyIconIndex = 0;
	private JDialog aboutBox;

	// image frame container
	private ImageFramesContainer container = new ImageFramesContainer();

	// ca.etsmtl.photomontage.ui menu controller
	private MenuController menuCtrl = new MenuController();

	// image browser
	private ImageBrowser imagebrowser;
	
	private SelectionBrowser selectionBrowser;

	/**
	 * Update des observers
	 */
	public void update(Observable arg0, Object arg1) {

		// update pour le container de imageframe
		if (arg0 instanceof ImageFramesContainer) {
			// keep a copy of the components
			ArrayList<ImageFrame> mdiframes = new ArrayList<ImageFrame>();

			// remove old frames
			if (mdi.getComponents().length > 0) {
				for (int i = 0; i < mdi.getComponents().length; i++) {
					if (mdi.getComponent(i) instanceof ImageFrame) {
						ImageFrame frame = (ImageFrame) mdi.getComponent(i);	
						if (!container.contains(frame)) {
							mdi.remove(frame);
						} else {
							mdiframes.add(frame);
						}
					}
				}
			}

			// add new frames
			for (ImageFrame frame : container.getFrames()) {
				if (!mdiframes.contains(frame)) {
					// ajouter item dans le menu window
					frame.setMenuItem(new WindowItem(frame, mdi));
					UIView.WindowsMenu.add(frame.getMenuItem());

					// ajouter le frame dans le desktop et activer le frame
					mdi.add(frame);
					mdi.getDesktopManager().activateFrame(frame);
				}
			}

			// clean mdi frames
			mdiframes = null;
		}
	}

	/**
	 * 
	 * @return the selection browser component
	 */
	public SelectionBrowser getSelectionBrowser() {
		return selectionBrowser;
	}

	/**
	 * 
	 * @return the mdi component
	 */
	public javax.swing.JDesktopPane getImageFramesDesktop() {
		return mdi;
	}
}