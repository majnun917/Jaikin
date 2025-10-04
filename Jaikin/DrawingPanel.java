import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {

    private static final int MAX_ITERATIONS = 7;
    private static final int ANIMATION_DELAY = 500; 

    private final List<Point> controlPoints = new ArrayList<>();
    private List<Point> smoothedPoints = new ArrayList<>();

    private int currentIteration = 0;
    private boolean isAnimating = false;
    private Timer animationTimer;

    public DrawingPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!isAnimating) {
                    controlPoints.add(e.getPoint());
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && controlPoints.size() > 2 && !isAnimating) {
                    startAnimation();
                }
                if (e.getKeyCode() ==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
        });
        setupAnimationTimer();
    }

    private void startAnimation() {
        isAnimating = true;
        currentIteration = 0;
        smoothedPoints = new ArrayList<>(controlPoints);
        animationTimer.start();
    }

    private void setupAnimationTimer() {
        animationTimer = new Timer(ANIMATION_DELAY, e -> {
            if (currentIteration >= MAX_ITERATIONS) {
                currentIteration = 0;
                smoothedPoints = new ArrayList<>(controlPoints);
            } else {
                currentIteration++;
                smoothedPoints = chaikinStep(smoothedPoints);
            }
            repaint();
        });
    }

   private List<Point> chaikinStep(List<Point> points) {
        if (points.size() < 2) {
            return points;
        }
        List<Point> newPoints = new ArrayList<>();
        // first point
        newPoints.add(points.get(0));

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);

            Point q = new Point((int) (0.75 * p1.x + 0.25 * p2.x), (int) (0.75 * p1.y + 0.25 * p2.y));
            Point r = new Point((int) (0.25 * p1.x + 0.75 * p2.x), (int) (0.25 * p1.y + 0.75 * p2.y));

            newPoints.add(q);
            newPoints.add(r);
        }
        //last point
        newPoints.add(points.get(points.size() - 1));
        return newPoints;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        g2d.setColor(Color.ORANGE);
        for (Point p : controlPoints) {
            g2d.fillOval(p.x-2, p.y-2, 5, 5);
        }

      
        if (isAnimating && !smoothedPoints.isEmpty()) {
            g2d.setColor(Color.CYAN);
            for (int i = 0; i < smoothedPoints.size() - 1; i++) {
                Point p1 = smoothedPoints.get(i);
                Point p2 = smoothedPoints.get(i + 1);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
            g2d.setColor(Color.WHITE);
            g2d.drawString("Steps: " + currentIteration, 10, 25);

        } else if (!controlPoints.isEmpty()) {
            g2d.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < controlPoints.size() - 1; i++) {
                Point p1 = controlPoints.get(i);
                Point p2 = controlPoints.get(i + 1);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}