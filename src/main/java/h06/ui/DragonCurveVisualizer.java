package h06.ui;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;

@DoNotTouch
public class DragonCurveVisualizer extends JFrame {

    public DragonCurveVisualizer(String[] drawInstructions) {
        super("Dragon Curve Visualizer");
        setSize(new Dimension(600,750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        DragonCurveDrawer dragonCurveDrawer = new DragonCurveDrawer(drawInstructions);
        dragonCurveDrawer.setPreferredSize(new Dimension(600,600));
        add(dragonCurveDrawer);

        JPanel control = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JSlider depthSlider = new JSlider(JSlider.HORIZONTAL, 0, 8, 5);
        depthSlider.addChangeListener(e -> {
            JSlider source = (JSlider)e.getSource();
            int depth = source.getValue();
        });
        depthSlider.setMajorTickSpacing(1);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);

        JLabel depthLabel = new JLabel("Fractal Depth");
        JCheckBox useIterativeCheckBox = new JCheckBox("Use Iterative Method");
        useIterativeCheckBox.addActionListener(e -> {
            JCheckBox source = (JCheckBox) e.getSource();
        });

        control.add(depthLabel);
        control.add(depthSlider);
        control.add(useIterativeCheckBox);

        add(control);
    }
}
