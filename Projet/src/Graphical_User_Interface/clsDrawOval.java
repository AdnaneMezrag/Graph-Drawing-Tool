package Graphical_User_Interface;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class clsDrawOval extends JComponent implements MouseListener{
	

	private int _X;
	private int _Y;
	private String _Text;
	Rectangle2D.Double r;
	boolean Clear = false;
	
	public clsDrawOval() {
		
	}
	
	public void clear() {
	    // Remove the component from its parent container
	    Container parent = getParent();
	    if (parent != null) {
	        parent.remove(this);
	        parent.revalidate(); // Ensure proper layout after removal
	        parent.repaint();   // Trigger repaint of the parent container
	    }
	}

	
	public clsDrawOval(int x,int y, String text) {
		_X = x;
		_Y = y;
		set_Text(text);
	}
	
	public void paint(Graphics g) {
	
 
		Graphics2D G2D = (Graphics2D) g;
		G2D.setColor(new Color(34,193,34));
		Rectangle2D.Double r = new Rectangle2D.Double(_X,_Y,50,50);
		G2D.fill(r);
		
		
		
	    G2D.setColor(Color.BLACK);
		G2D.setFont(new Font("Arial", Font.PLAIN, 12)); // Choose the font and size

	    
	    // Get the dimensions of the text
	    FontMetrics fm = G2D.getFontMetrics();
	    int textWidth = fm.stringWidth(_Text);
	    int textHeight = fm.getHeight();
	    
	    // Calculate the position to center the text inside the rectangle
	    int x = (int) (_X + (r.getWidth() - textWidth) / 2);
	    int y = (int) (_Y + (r.getHeight() + textHeight) / 2);
	    
	    G2D.drawString(_Text, x, y);
	    
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public String get_Text() {
		return _Text;
	}

	public void set_Text(String _Text) {
		this._Text = _Text;
	}
	

	
}
