package ru.stqa.jpfste.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 6);

        System.out
                .println("The distance between two points (using function): = " + getDistanceBetweenTwoPoints(p1, p2));
        System.out.println("The distance between two points (using method): = " + p1.getDistanceBetweenTwoPoints2(p2));
    }

    public static double getDistanceBetweenTwoPoints(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.y - p1.x), 2) + Math.pow((p2.y - p2.x), 2));
    }
}