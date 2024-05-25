package h06.problems;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;

@DoNotTouch
public abstract class DrawableFractal extends JPanel {
    private int depth = 5;
    private boolean useIterative = false;

    public boolean isUseIterative() {
        return useIterative;
    }

    public void setUseIterative(boolean useIterative) {
        this.useIterative = useIterative;
    }

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
        drawFractal(g);
    }

    public abstract void drawFractal(Graphics g);
}
