package com.example.superjg.cfg;

public class VideoCfg {

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isEnabledAntialiasing() {
		return enabledAntialiasing;
	}

	public void setEnabledAntialiasing(boolean enabledAntialiasing) {
		this.enabledAntialiasing = enabledAntialiasing;
	}

	public int getFPS() {
		return fps;
	}

	public void setFPS(int fps) {
		this.fps = fps;
	}

	@Override
	public String toString() {
		return String.format("%s {width=%d, height=%d, enabledAntialiasing=%b, fps:%d}", getClass().getSimpleName(),
				width, height, enabledAntialiasing, fps);
	}

	private int width;
	private int height;
	private boolean enabledAntialiasing;
	private int fps;

}
