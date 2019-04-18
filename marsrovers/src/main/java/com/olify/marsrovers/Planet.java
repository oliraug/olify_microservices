package com.olify.marsrovers;

import java.util.List;

public class Planet {

	private Point max;
	private List<Point> obstacles;
	
	public Planet() {
	
	}
	public Planet(Point max, List<Point> obstacles) {
		super();
		this.max = max;
		this.obstacles = obstacles;
	}
	public Point getMax() {
		return max;
	}
	public void setMax(Point max) {
		this.max = max;
	}
	public List<Point> getObstacles() {
		return obstacles;
	}
	public void setObstacles(List<Point> obstacles) {
		this.obstacles = obstacles;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(max).append(obstacles);
		return builder.toString();
	}
}
