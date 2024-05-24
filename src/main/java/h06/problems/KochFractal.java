package h06.problems;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.awt.*;

public class KochFractal extends Fractal {

    /**
     * Angle between the sides
     */
    private final double deg60 = Math.toRadians(60); // Winkel zwischen den Seiten

    /**
     * Default Constructor for a KochFractal.
     */
    @DoNotTouch
    public KochFractal() {}

    /**
     * Draws a line.
     */
    @DoNotTouch
    private void drawLine(Graphics g, double x, double y, double length, double angle) {
        double x2 = x + length * Math.cos(angle);
        double y2 = y - length * Math.sin(angle);
        g.drawLine((int)x, (int)y, (int)x2, (int)y2);
    }

    /**
     * Draws the Koch Fractal on the canvas.
     */
    @DoNotTouch
    @Override
    public void drawFractal(Graphics g, int depth) {
        int triangleSideLength = (int)(Math.max(getWidth(), getHeight()) * 0.75);
        int circumradius = (int)(Math.sqrt(3)/3 * triangleSideLength);

        int midX = getWidth() / 2;
        int midY = getHeight() / 2;

        // We call our drawing method three times. With depth == 0, this results in drawing in a triangle, one call for each side of the triangle.
        drawKochSnowflake(g, depth, midX + circumradius * Math.cos(Math.toRadians(30)), midY + circumradius * Math.sin(Math.toRadians(30)), triangleSideLength, Math.toRadians(180));
        drawKochSnowflake(g, depth, midX + circumradius * Math.cos(Math.toRadians(150)), midY + circumradius * Math.sin(Math.toRadians(150)), triangleSideLength, Math.toRadians(60));
        drawKochSnowflake(g, depth, midX + circumradius * Math.cos(Math.toRadians(270)), midY + circumradius * Math.sin(Math.toRadians(270)), triangleSideLength, Math.toRadians(300));
    }



    @StudentImplementationRequired
    private void drawKochSnowflake(Graphics g, int depth, double x, double y, double length, double angle) {
        if (depth == 0) {
            // Rekursionsanker: Zeichne eine Linie
            drawLine(g, x, y, length, angle);
        } else {
            // Teile die Linie in drei Abschnitte und zeichne das Koch-Schneeflocken-Fraktal für jeden Abschnitt
            double newLength = length / 3.0;

            double x1 = x + newLength * Math.cos(angle);
            double y1 = y - newLength * Math.sin(angle);

            double x2 = x1 + newLength * Math.cos(angle + deg60);
            double y2 = y1 - newLength * Math.sin(angle + deg60);

            double x3 = x2 + newLength * Math.cos(angle - deg60);
            double y3 = y2 - newLength * Math.sin(angle - deg60);

            drawKochSnowflake(g, depth - 1, x, y, newLength, angle);
            drawKochSnowflake(g, depth - 1, x1, y1, newLength, angle + deg60);
            drawKochSnowflake(g, depth - 1, x2, y2, newLength, angle - deg60);
            drawKochSnowflake(g, depth - 1, x3, y3, newLength, angle);
        }
    }
}
