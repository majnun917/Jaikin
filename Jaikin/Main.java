import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        DrawingPanel frame = new DrawingPanel();
        JFrame window = new JFrame();
        window.setTitle("Jaikin");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(frame);
        window.pack();
        window.setVisible(true);
        
    }
}