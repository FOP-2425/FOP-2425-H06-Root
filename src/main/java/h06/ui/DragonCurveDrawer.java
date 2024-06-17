package h06.ui;

import javax.swing.*;
import java.awt.*;

public class DragonCurveDrawer extends JPanel {
    String[] drawInstructions;

    public DragonCurveDrawer(String[] drawInstructions) {
        this.drawInstructions = drawInstructions;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        // calculate an angle with cosine?

        int stepSize = 10;
        int x1 = 50;
        int y1 = 50;
        int x2 = 50;
        int y2 = y1 - stepSize;
        // initial line
        g.drawLine(x1, y1, x2, y2);

        for (String s : drawInstructions) {
            g.drawLine(x1, y1, x2, y2-10);
        }
    }
}
