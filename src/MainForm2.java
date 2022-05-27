import javax.swing.*;
import java.awt.*;

public class MainForm2 extends JFrame {
    private TrafficLights2 lights;
    private int countSections;

    public MainForm2(int timeChangeColor){
        this(3, timeChangeColor);
    }

    public MainForm2(int countSections, int timeChangeColor){
        this.countSections = countSections;
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        if (this.countSections == 0){
            lights = new TrafficLights2(3, timeChangeColor, (TrafficLights2 t) -> {
                changeColor();
            });
        }else {
            lights = new TrafficLights2(this.countSections, timeChangeColor, (TrafficLights2 t) ->{
                changeColor();
            });
        }
    }

    public void paint(Graphics g){
        if (this.lights != null){
            g.setColor(lights.getCurrentColor());
            g.fillRect(0, 0, 500, 500);
        }
    }

    private void changeColor(){repaint();}
}
