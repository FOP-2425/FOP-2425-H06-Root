package h06.ui;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

@DoNotTouch
public class DragonCurveDrawer extends JPanel {
    String[] drawInstructions;
    Line2D[] lines;

    public DragonCurveDrawer(String[] drawInstructions) {
        this.drawInstructions = drawInstructions;
        this.lines = new Line2D[drawInstructions.length + 1];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        Graphics2D g2d = (Graphics2D) g;

        // Set the initial position and direction
        double x = 0;
        double y = 0;
        // The first line is drawn upwards
        double angle = 3*Math.PI/2;

        double minX = x;
        double minY = y;
        double maxX = x;
        double maxY = y+1;

        // Draw the first line upwards
        lines[0] = new Line2D.Double(x, y, x, y+1);

        // Store the dragon curve lines based on instructions
        for (int i = 0; i < drawInstructions.length; i++) {
            String instruction = drawInstructions[i];

            if (instruction.equals("L")) {
                angle -= Math.PI / 2; // Turn left 90 degrees
            } else if (instruction.equals("R")) {
                angle += Math.PI / 2; // Turn right 90 degrees
            }

            double x2 = x + Math.cos(angle);
            double y2 = y + Math.sin(angle);
            lines[i+1] = new Line2D.Double(x, y, x2, y2);
            x = x2;
            y = y2;

            // Update Bounding Box values
            minX = Math.min(minX, x);
            minY = Math.min(minY, y);
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
        }

        // Define Bounding Box
        Rectangle2D bounds = new Rectangle2D.Double(minX - 1, minY - 1, maxX - minX + 2, maxY - minY + 2);

        // Calculate scaling factor to fit all the lines within the panel
        double scaleX = getWidth() / bounds.getWidth();
        double scaleY = getHeight() / bounds.getHeight();
        double scale = Math.min(scaleX, scaleY);

        // Calculate the initial position to center the fractal
        double offsetX = (getWidth() - bounds.getWidth() * scale) / 2 - bounds.getX() * scale;
        double offsetY = (getHeight() - bounds.getHeight() * scale) / 2 - bounds.getY() * scale;

        // Draw the stored lines with scaling and offset
        for (Line2D line : lines) {
            double x1 = offsetX + line.getX1() * scale;
            double y1 = offsetY + line.getY1() * scale;
            double x2 = offsetX + line.getX2() * scale;
            double y2 = offsetY + line.getY2() * scale;
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }
}
