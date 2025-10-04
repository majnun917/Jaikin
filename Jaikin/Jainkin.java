
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

class DrawingPanel extends JPanel {

    private ArrayList<Point> points;
    private ArrayList<Point> newPoints;
    private boolean drawing = false;
    private int round = 0;
    private boolean error = false;

    public DrawingPanel() {
        points = new ArrayList<>();
        newPoints = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);

        // // Mouse input
        // addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mousePressed(MouseEvent e) {
        //         drawing = true;
        //         points.add(e.getPoint());
        //         repaint();
        //     }

        //     @Override
        //     public void mouseReleased(MouseEvent e) {
        //         drawing = false;
        //     }
        // });

        // addMouseMotionListener(new MouseMotionAdapter() {
        //     @Override
        //     public void mouseDragged(MouseEvent e) {
        //         if (drawing) {
        //             points.add(e.getPoint());
        //             repaint();
        //         }
        //     }
        // });

        // Keyboard input
        // addKeyListener(new KeyAdapter() {
        //     @Override
        //     public void keyPressed(KeyEvent e) {
        //         switch (e.getKeyCode()) {
        //             case KeyEvent.VK_R:
        //                 round++;
        //                 repaint();
        //                 break;
        //             case KeyEvent.VK_E:
        //                 error = !error;
        //                 repaint();
        //                 break;
        //             case KeyEvent.VK_C:
        //                 points.clear();
        //                 repaint();
        //                 break;
        //         }
        //     }
        // });
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw points in white
        g.setColor(Color.WHITE);
        for (Point p : points) {
            g.fillOval(p.x, p.y, 4, 4);
        }

        // Draw round number in green
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Round: " + round, 10, 25);

        // Draw error message if error is true
        if (error) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Error!", 10, 50);
        }
    }
}
