package com.example.superjg.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.example.superjg.cfg.Configuration;
import com.example.superjg.game.act.Updateable;
import com.example.superjg.game.audio.Audio;
import com.example.superjg.game.entities.Ball;
import com.example.superjg.game.entities.Racquet;
import com.example.superjg.game.entities.Sprite;
import com.example.superjg.game.graphics.Rendereable;
import com.example.superjg.game.hud.HUD;
import com.example.superjg.utils.DifficultyUtils;
import com.example.superjg.utils.GraphicUtils;

@SuppressWarnings("serial")
public class Game extends JPanel implements Updateable, Rendereable, MouseListener, KeyListener {

	public Game(GameWindow gameWindow) {
		super();
		this.gameWindow = gameWindow;
		this.hud = new HUD(gameWindow.getGameConfiguration(), 0);
		this.width = gameWindow.getGameConfiguration().getVideoCfg().getWidth();
		this.height = gameWindow.getGameConfiguration().getVideoCfg().getHeight();
		init();
	}

	private void init() {
		running = true;
		playing = true;

		setPreferredSize(new Dimension(width, height));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus();
		addMouseListener(this);
		addKeyListener(this);

		var difficultyValues = DifficultyUtils
				.getValues(gameWindow.getGameConfiguration().getGameCfg().getDifficulty());
		System.out.printf("info: using %s%n", difficultyValues);

		Ball mainBall = new Ball("mainBall", width / 5, 0, this, difficultyValues.getBallSpeed());
		Racquet racquet1 = new Racquet("racquet1", width / 2 - (Racquet.DEFAULT_WIDTH / 2),
				height - Racquet.DEFAULT_HEIGHT, this, difficultyValues.getRacquetSpeed());

		sprites = new ArrayList<>();
		sprites.add(mainBall);
		sprites.add(racquet1);

		players = new ArrayList<>();
		players.add(racquet1);

		mainTheme = new Audio("./res/sfx/main-theme.wav");
		mainTheme.setVolume(gameWindow.getGameConfiguration().getAudioCfg().getMusicLevel());
		mainTheme.loop();
	}

	@Override
	public void update() {
		sprites.forEach(sprite -> sprite.update());
	}

	@Override
	public void render() {
		repaint();
	}

	@Override
	public void render(Graphics g) {
		sprites.forEach(sprite -> sprite.render(g));
		hud.render(g);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (gameWindow.getGameConfiguration().getVideoCfg().isEnabledAntialiasing()) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		render(g);
		if (!running && playing) {
			g2d.setColor(Color.WHITE);
			GraphicUtils.drawCenteredString(g2d, "PAUSED", new Rectangle(width, height),
					new Font("Sans", Font.BOLD, 50));
		}
		if (!playing) {
			g2d.setColor(Color.RED);
			GraphicUtils.drawCenteredString(g2d, "Game Over", new Rectangle(width, height),
					new Font("Sans", Font.BOLD, 50));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		sprites.forEach(sprite -> sprite.mouseClicked(e));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		sprites.forEach(sprite -> sprite.mousePressed(e));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sprites.forEach(sprite -> sprite.mouseReleased(e));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		sprites.forEach(sprite -> sprite.mouseEntered(e));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		sprites.forEach(sprite -> sprite.mouseExited(e));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		sprites.forEach(sprite -> sprite.keyTyped(e));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			running = !running;
		} else {
			sprites.forEach(sprite -> sprite.keyPressed(e));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		sprites.forEach(sprite -> sprite.keyReleased(e));
	}

	public Sprite[] getPlayers() {
		return players.toArray(new Sprite[players.size()]);
	}

	public void gameOver() {
		removeMouseListener(this);
		removeKeyListener(this);

		running = false;
		playing = false;
	}

	public void displaySpritesInformation() {
		sprites.forEach(sprite -> System.out.println(sprite));
	}

	public Configuration getConfiguration() {
		return gameWindow.getGameConfiguration();
	}

	public HUD getHUD() {
		return hud;
	}

	public boolean isRunning() {
		return running;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private final GameWindow gameWindow;
	private final HUD hud;
	private boolean running;
	private boolean playing;
	private final int width;
	private final int height;
	private List<Sprite> sprites;
	private List<Sprite> players;
	private Audio mainTheme;

}
