package com.example.superjg.game;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import com.example.superjg.cfg.Configuration;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	public GameWindow(Configuration gameConfiguration) throws HeadlessException {
		super();
		this.gameConfiguration = gameConfiguration;
		this.game = new Game(this);
		init();
	}

	private void init() {
		setTitle(String.format("%s v%s", gameConfiguration.getGameInfo().getName(),
				gameConfiguration.getGameInfo().getVersion()));
		add(game);
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() throws InterruptedException {
		setVisible(true);

		final int MAX_UPDATES_PER_SECOND = gameConfiguration.getGameCfg().getUPS();
		final int MAX_FRAMES_PER_SECOND = gameConfiguration.getVideoCfg().getFPS();

		final double uOPTIMAL_TIME = 1000000000 / MAX_UPDATES_PER_SECOND;
		final double fOPTIMAL_TIME = 1000000000 / MAX_FRAMES_PER_SECOND;

		double uDeltaTime = 0, fDeltaTime = 0;
		int frames = 0, updates = 0;
		long startTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		for (;;) {

			long currentTime = System.nanoTime();
			uDeltaTime += (currentTime - startTime);
			fDeltaTime += (currentTime - startTime);
			startTime = currentTime;

			if (uDeltaTime >= uOPTIMAL_TIME) {
				if (game.isRunning()) {
					game.update();
					updates++;
				}
				uDeltaTime -= uOPTIMAL_TIME;
			}

			if (fDeltaTime >= fOPTIMAL_TIME) {
				game.render();
				frames++;
				fDeltaTime -= fOPTIMAL_TIME;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				updateUPSAndFPS(updates, frames);
				updates = 0;
				frames = 0;
				timer += 1000;
			}

		}
	}

	private void updateUPSAndFPS(int ups, int fps) {
		setTitle(String.format("%s v%s (%d FPS, %d UPS)", gameConfiguration.getGameInfo().getName(),
				gameConfiguration.getGameInfo().getVersion(), ups, fps));
	}

	public Configuration getGameConfiguration() {
		return gameConfiguration;
	}

	protected final Configuration gameConfiguration;
	protected Game game;

}
