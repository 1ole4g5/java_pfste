package ru.stqa.jpfste.sandbox;


public class Point {

	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getDistanceBetweenTwoPoints2(Point p) {
		return Math.sqrt(Math.pow((this.y - this.x), 2) + Math.pow((p.y - p.x), 2));
	}
}