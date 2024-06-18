package h06.ui;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import javax.swing.*;
import java.awt.*;

@DoNotTouch
public class DragonCurveVisualizer extends JFrame {

    public DragonCurveVisualizer(String[] drawInstructions) {
        super("Dragon Curve Visualizer");
        setSize(new Dimension(800,800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,1));

        DragonCurveDrawer dragonCurveDrawer = new DragonCurveDrawer(drawInstructions);
        add(dragonCurveDrawer);
    }
}
