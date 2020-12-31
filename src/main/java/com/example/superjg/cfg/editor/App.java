package com.example.superjg.cfg.editor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.example.superjg.cfg.Configuration;
import com.example.superjg.cfg.Difficulty;
import com.example.superjg.utils.ConfigurationManager;

/*
 * @todo fix GUI components alignment
 * @todo add icon toolbox
 */
@SuppressWarnings("serial")
public class App extends JFrame {

	public App(Configuration cfg) {
		this.cfg = cfg;
		init();
	}

	public void init() {
		logLabel = new JLabel("App started.");
		logLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

		difficultyJCB = new JComboBox<Difficulty>(Difficulty.values());
		upsTextField = new JTextField();
		upsTextField.setFont(DEFAULT_MONOSPACED_FONT);

		widthTextField = new JTextField();
		heightTextField = new JTextField();
		enableAntialisingCheckBox = new JCheckBox();
		fpsTextField = new JTextField();

		musicSlider = new JSlider(0, 100);
		musicSlider.setPaintTicks(true);
		musicSlider.setPaintLabels(true);
		musicSlider.setSnapToTicks(true);
		musicSlider.setMajorTickSpacing(20);
		musicSlider.setMinorTickSpacing(10);
		sfxSlider = new JSlider(0, 100);
		sfxSlider.setPaintTicks(true);
		sfxSlider.setPaintLabels(true);
		sfxSlider.setSnapToTicks(true);
		sfxSlider.setMajorTickSpacing(20);
		sfxSlider.setMinorTickSpacing(10);

		loadConfigurationToControls();

		// content pane
		var gameCfgPaneInner = new JPanel(new GridLayout(2, 2));
		gameCfgPaneInner.add(new JLabel("Difficulty:"));
		gameCfgPaneInner.add(difficultyJCB);
		gameCfgPaneInner.add(new JLabel("UPS:"));
		gameCfgPaneInner.add(upsTextField);
		var gameCfgPane = new JPanel();
		gameCfgPane.add(gameCfgPaneInner);

		var videoCfgPaneInner = new JPanel(new GridLayout(4, 2));
		videoCfgPaneInner.add(new JLabel("Width:"));
		videoCfgPaneInner.add(widthTextField);
		videoCfgPaneInner.add(new JLabel("Height"));
		videoCfgPaneInner.add(heightTextField);
		videoCfgPaneInner.add(new JLabel("Enable antialising:"));
		videoCfgPaneInner.add(enableAntialisingCheckBox);
		videoCfgPaneInner.add(new JLabel("FPS:"));
		videoCfgPaneInner.add(fpsTextField);
		var videoCfgPane = new JPanel();
		videoCfgPane.add(videoCfgPaneInner);

		var audioCfgPaneInner = new JPanel(new GridLayout(2, 2));
		audioCfgPaneInner.add(new JLabel("Music:"));
		audioCfgPaneInner.add(musicSlider);
		audioCfgPaneInner.add(new JLabel("SFX:"));
		audioCfgPaneInner.add(sfxSlider);
		var audioCfgPane = new JPanel();
		audioCfgPane.add(audioCfgPaneInner);

		var tabbedPane = new JTabbedPane();
		tabbedPane.add("Game", gameCfgPane);
		tabbedPane.add("Video", videoCfgPane);
		tabbedPane.add("Audio", audioCfgPane);

		var contentPane = new JPanel(new BorderLayout());
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		contentPane.add(logLabel, BorderLayout.SOUTH);

		// menu bar
		var saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(e -> save());
		var exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(e -> exit());
		var appMenu = new JMenu("App");
		appMenu.add(saveMenuItem);
		appMenu.addSeparator();
		appMenu.add(exitMenuItem);

		var aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(e -> showAbout());
		var helpMenu = new JMenu("Help");
		helpMenu.add(aboutMenuItem);

		var menuBar = new JMenuBar();
		menuBar.add(appMenu);
		menuBar.add(helpMenu);

		// jframe properties
		setContentPane(contentPane);
		setJMenuBar(menuBar);
		setTitle(String.format("%s config editor v%s", cfg.getGameInfo().getArtifactId(),
				cfg.getGameInfo().getVersion()));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void loadConfigurationToControls() {
		difficultyJCB.setSelectedItem(cfg.getGameCfg().getDifficulty());
		upsTextField.setText(String.valueOf(cfg.getGameCfg().getUPS()));

		widthTextField.setText(String.valueOf(cfg.getVideoCfg().getWidth()));
		heightTextField.setText(String.valueOf(cfg.getVideoCfg().getHeight()));
		enableAntialisingCheckBox.setSelected(cfg.getVideoCfg().isEnabledAntialiasing());
		fpsTextField.setText(String.valueOf(cfg.getVideoCfg().getFPS()));

		musicSlider.setValue((int) (cfg.getAudioCfg().getMusicLevel() * 100));
		sfxSlider.setValue((int) (cfg.getAudioCfg().getSfxLevel() * 100));
	}

	public void loadConfigurationFromControls() {
		cfg.getGameCfg().setDifficulty((Difficulty) difficultyJCB.getSelectedItem());
		cfg.getGameCfg().setUPS(getIntValueFromControl(upsTextField, "UPS"));

		cfg.getVideoCfg().setWidth(getIntValueFromControl(widthTextField, "width"));
		cfg.getVideoCfg().setHeight(getIntValueFromControl(heightTextField, "height"));
		cfg.getVideoCfg().setEnabledAntialiasing(enableAntialisingCheckBox.isSelected());
		cfg.getVideoCfg().setFPS(getIntValueFromControl(fpsTextField, "FPS"));

		cfg.getAudioCfg().setMusicLevel(getFloatValueFromControl(musicSlider));
		cfg.getAudioCfg().setSfxLevel(getFloatValueFromControl(sfxSlider));
	}

	public int getIntValueFromControl(JTextField textField, String name) {
		try {
			return Integer.parseInt(textField.getText());
		} catch (Exception ex) {
			showErrorMessage("Invalid input", String.format("The field %d must be a valid integer."));
			return 0;
		}
	}

	public float getFloatValueFromControl(JSlider slider) {
		return ((int) slider.getValue()) / 100.0f;
	}

	public void start() {
		setVisible(true);
	}

	public void exit() {
		System.exit(SUCCESS_RETURN_CODE);
	}

	public void showAbout() {
		var dialogBody = String.format("%s config editor v%s", cfg.getGameInfo().getArtifactId(),
				cfg.getGameInfo().getVersion());
		showInfoMessage("About", dialogBody);
	}

	public void log(String message) {
		logLabel.setText(message);
	}

	public void save() {
		loadConfigurationFromControls();

		if (cfg.getCfgFilePath() == null) {
			if (!showSaveDialog()) {
				return;
			}
		}

		try {
			ConfigurationManager.saveGameConfiguration(cfg);
			log("Configuration saved!");
			showInfoMessage("Save", "Configuration saved!");
		} catch (Exception ex) {
			showErrorMessage(ex.getClass().getSimpleName(), ex.getMessage());
		}
	}

	public void showErrorMessage(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public void showInfoMessage(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean showSaveDialog() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(
					new File(ConfigurationManager.DEFAULT_FILE_NAME + ConfigurationManager.DEFAULT_FILE_EXTENSION));
		}
		var res = fileChooser.showSaveDialog(this);
		if (res == JFileChooser.APPROVE_OPTION) {
			var filePath = fileChooser.getSelectedFile().getAbsolutePath();
			if (!filePath.endsWith(ConfigurationManager.DEFAULT_FILE_EXTENSION)) {
				filePath += ConfigurationManager.DEFAULT_FILE_EXTENSION;
			}
			cfg.setCfgFilePath(filePath);
			return true;
		}
		return false;
	}

	private Configuration cfg;
	private JLabel logLabel;

	private JComboBox<Difficulty> difficultyJCB;
	private JTextField upsTextField;

	private JTextField widthTextField;
	private JTextField heightTextField;
	private JCheckBox enableAntialisingCheckBox;
	private JTextField fpsTextField;

	private JSlider musicSlider;
	private JSlider sfxSlider;

	private JFileChooser fileChooser;

	public static final int SUCCESS_RETURN_CODE = 0;
	public static final Font DEFAULT_MONOSPACED_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 14);

}
