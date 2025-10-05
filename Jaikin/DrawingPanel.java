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
import javax.swing.JPanel;
import javax.swing.Timer;

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
                if (!isAnimating && e.getButton() == MouseEvent.BUTTON1) {
                    controlPoints.add(e.getPoint());
                    repaint();
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Graphics g = getGraphics();
                Graphics2D g2d = (Graphics2D) g;
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !isAnimating) {
                    if (controlPoints.size() == 0) {
                        g2d.setColor(Color.red);
                        g2d.drawString("ADD AT LEAST ONE POINT BEFORE STARTING THE ANIMATION", 10, 80);
                    }
                    if (controlPoints.size() <= 2) {
                        isAnimating = false;
                    } else {
                        startAnimation();
                    }
                    if (!controlPoints.isEmpty()) {
                        g2d.setColor(Color.LIGHT_GRAY);
                        for (int i = 0; i < controlPoints.size() - 1; i++) {
                            Point p1 = controlPoints.get(i);
                            Point p2 = controlPoints.get(i + 1);
                            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
                        }
                    }
                }else if (e.getKeyCode() == KeyEvent.VK_C) {
                    controlPoints.clear();
                    smoothedPoints.clear();
                    isAnimating = false;
                    currentIteration = 0;
                    if (animationTimer != null && animationTimer.isRunning()) {
                        animationTimer.stop();
                    }
                    repaint();
                    
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                currentIteration = 1;
                smoothedPoints = new ArrayList<>(controlPoints);
            } else {
                currentIteration++;
                smoothedPoints = Chaikin.chaikinStep(smoothedPoints);
            }
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.drawString("USING TWO POINTS AT LEAST TO START DRAWING. ESC TO EXIT THE PROGRAM", 10, 60);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.ORANGE);
        for (Point p : controlPoints) {
            g2d.drawOval(p.x - 3, p.y - 3, 6, 6);
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

        }
    }
}