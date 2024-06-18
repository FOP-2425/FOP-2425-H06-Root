package h06.ui;

import h06.problems.DrawInstruction;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;

@DoNotTouch
public class FractalVisualizer extends JFrame {

    public FractalVisualizer(DrawInstruction[] drawInstructions, int angle) {
        super("Fractal Visualizer");
        setSize(new Dimension(800,800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1));

        FractalDrawer fractalDrawer = new FractalDrawer(drawInstructions, angle);
        add(fractalDrawer);
    }
}
