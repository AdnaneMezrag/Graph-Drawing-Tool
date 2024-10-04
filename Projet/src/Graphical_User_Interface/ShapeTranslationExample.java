package Graphical_User_Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShapeTranslationExample extends JFrame {

    private Rectangle shape;
    private int offsetX, offsetY;

    public ShapeTranslationExample() {
        setTitle("Shape Translation Example");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shape = new Rectangle(50, 50, 100, 100);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLUE);
                g2d.fill(shape);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (shape.contains(e.getPoint())) {
                    offsetX = e.getX() - shape.x;
                    offsetY = e.getY() - shape.y;
                }
            }
        });
        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shape.x = e.getX() - offsetX;
                shape.y = e.getY() - offsetY;
                panel.repaint();
            }
        });

        getContentPane().add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShapeTranslationExample::new);
    }
}
