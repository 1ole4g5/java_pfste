package ru.stqa.jpfste.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

	@Test
	public void testArea() {
		Point p1 = new Point(1, 2);
		Point p2 = new Point(3, 6);
		Assert.assertEquals(p1.getDistanceBetweenTwoPoints2(p2), 3.1622776601683795);
	}

	@Test
	public void testArea2() {
		Point p1 = new Point(0, 2);
		Point p2 = new Point(5, 6);
		Assert.assertEquals(p1.getDistanceBetweenTwoPoints2(p2), 2.23606797749979);
	}

	@Test
	public void testArea3() {
		Point p1 = new Point(0, 0);
		Point p2 = new Point(0, 0);
		Assert.assertEquals(p1.getDistanceBetweenTwoPoints2(p2), 0.0);
	}
}