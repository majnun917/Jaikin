
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel();

        JFrame window = new JFrame();
        window.setTitle("Jaikin");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null); // Center on screen
        window.setVisible(true);

        // Request focus so keyboard works immediately
        panel.requestFocusInWindow();
    }
}