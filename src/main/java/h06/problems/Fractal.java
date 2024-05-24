package h06.problems;

import javax.swing.*;
import java.awt.*;

public abstract class Fractal extends JPanel {
    private int depth = 5;

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        drawFractal(g, depth);
    }

    public abstract void drawFractal(Graphics g, int depth);
}
