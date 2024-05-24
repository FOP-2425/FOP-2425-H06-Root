package h06.ui;

import h06.problems.Fractal;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;

@DoNotTouch
public class FractalVisualizer extends JFrame {

    public FractalVisualizer(Fractal frac) {
        super("Fractal Visualizer");
        setSize(new Dimension(600,750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JPanel control = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JSlider depthSlider = new JSlider(JSlider.HORIZONTAL, 0, 8, 5);
        depthSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            int depth = source.getValue();
            frac.setDepth(depth);
            frac.repaint();
        });
        depthSlider.setMajorTickSpacing(1);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);

        JLabel depthLabel = new JLabel("Recursion Depth");

        control.add(depthLabel);
        control.add(depthSlider);

        frac.setPreferredSize(new Dimension(600, 600));

        add(frac);
        add(control);
    }
}
