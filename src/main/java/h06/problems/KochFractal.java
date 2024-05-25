package h06.problems;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.awt.*;

public class KochFractal extends DrawableFractal {

    /**
     * Angle between the sides
     */
    private final double DEG60 = Math.toRadians(60); // Winkel zwischen den Seiten

    /**
     * Default Constructor for a KochFractal.
     */
    @DoNotTouch
    public KochFractal() {}

    /**
     * Draws a line.
     */
    @DoNotTouch
    private void drawSegment(Graphics g, Segment seg) {
        g.drawLine((int)seg.getX1(), (int)seg.getY1(), (int)seg.getX2(), (int)seg.getY2());
    }

    /**
     * Draws the Koch Fractal on the canvas.
     * Defines the three edge segments of the initial triangle and then calls
     */
    @DoNotTouch
    @Override
    public void drawFractal(Graphics g) {
        int triangleSideLength = (int)(Math.max(getWidth(), getHeight()) * 0.75);
        int circumradius = (int)(Math.sqrt(3)/3 * triangleSideLength);

        int midX = getWidth() / 2;
        int midY = getHeight() / 2;

        double x1 = midX + circumradius * Math.cos(Math.toRadians(150));
        double y1 = midY + circumradius * Math.sin(Math.toRadians(150));
        double x2 = midX + circumradius * Math.cos(Math.toRadians(30));
        double y2 = midY + circumradius * Math.sin(Math.toRadians(30));
        double x3 = midX + circumradius * Math.cos(Math.toRadians(270));
        double y3 = midY + circumradius * Math.sin(Math.toRadians(270));

        // We call our drawing method three times. With depth == 0, this results in drawing a triangle, one call for each side of the triangle.
        // When we increase the depth, we draw the fractal on each side of the triangle. Overall, this results in the Koch Snowflake.
        if(isUseIterative()) {
            drawKochFractalIterative(g, getDepth(), new Segment(x1, y1, x2, y2));
            drawKochFractalIterative(g, getDepth(), new Segment(x2, y2, x3, y3));
            drawKochFractalIterative(g, getDepth(), new Segment(x3, y3, x1, y1));
        } else {
            drawKochFractalRecursive(g, getDepth(), new Segment(x1, y1, x2, y2));
            drawKochFractalRecursive(g, getDepth(), new Segment(x2, y2, x3, y3));
            drawKochFractalRecursive(g, getDepth(), new Segment(x3, y3, x1, y1));
        }
    }


    @StudentImplementationRequired
    private void drawKochFractalRecursive(Graphics g, int depth, Segment seg) {
        if (depth == 0) {
            // Rekursionsanker: Zeichne eine Linie
            drawSegment(g, seg);
        } else {
            // Teile die Linie in drei Abschnitte und zeichne das Koch-Schneeflocken-Fraktal für jeden Abschnitt
            double dx = (seg.getX2() - seg.getX1()) / 3;
            double dy = (seg.getY2() - seg.getY1()) / 3;

            double xA = seg.getX1() + dx;
            double yA = seg.getY1() + dy;
            double xB = seg.getX2() - dx;
            double yB = seg.getY2() - dy;

            double xC = xA + dx * Math.cos(DEG60) - dy * Math.sin(DEG60);
            double yC = yA + dx * Math.sin(DEG60) + dy * Math.cos(DEG60);

            drawKochFractalRecursive(g, depth - 1, new Segment(seg.getX1(), seg.getY1(), xA, yA));
            drawKochFractalRecursive(g, depth - 1, new Segment(xA, yA, xC, yC));
            drawKochFractalRecursive(g, depth - 1, new Segment(xC, yC, xB, yB));
            drawKochFractalRecursive(g, depth - 1, new Segment(xB, yB, seg.getX2(), seg.getY2()));
        }
    }


    // Darauf hinweisen dass das jetzt umständlicher wird da wir erst alle Ergebnisse in einem Array zwischenspeichern müssen
    // Soweit ich weiß geht das auch nicht schlauer iterativ (?)
    @StudentImplementationRequired
    public void drawKochFractalIterative(Graphics g, int depth, Segment startSegment) {
        Segment[] segments = { startSegment };

        for (int i = 0; i < depth; i++) {
            Segment[] newSegments = new Segment[segments.length * 4];
            int index = 0;

            // Kam diese Art von Schleife schon dran?
            for (Segment seg : segments) {
                double dx = (seg.getX2() - seg.getX1()) / 3;
                double dy = (seg.getY2() - seg.getY1()) / 3;

                double xA = seg.getX1() + dx;
                double yA = seg.getY1() + dy;
                double xB = seg.getX2() - dx;
                double yB = seg.getY2() - dy;

                double xC = xA + dx * Math.cos(DEG60) - dy * Math.sin(DEG60);
                double yC = yA + dx * Math.sin(DEG60) + dy * Math.cos(DEG60);

                newSegments[index++] = new Segment(seg.getX1(), seg.getY1(), xA, yA);
                newSegments[index++] = new Segment(xA, yA, xC, yC);
                newSegments[index++] = new Segment(xC, yC, xB, yB);
                newSegments[index++] = new Segment(xB, yB, seg.getX2(), seg.getY2());
            }
            segments = newSegments;
        }

        for (Segment seg : segments) {
            drawSegment(g, seg);
        }
    }
}
