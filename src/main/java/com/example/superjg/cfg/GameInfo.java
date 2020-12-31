package com.example.superjg.cfg;

public class GameInfo {

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return String.format("%s {artifactId:%s, name:%s, version:%s}", getClass().getSimpleName(), artifactId, name,
				version);
	}

	private String artifactId;
	private String name;
	private String version;

}
