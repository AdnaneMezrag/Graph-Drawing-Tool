package Graphical_User_Interface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class clsDrawEdge extends JComponent {

	private int _XStart;
	private int _YStart;
	
	private int _XEnd;
	private int _YEnd;
	
	public clsDrawEdge(int XStart , int YStart , int XEnd , int YEnd) {
		_XStart = XStart;
		_YStart = YStart;
		_XEnd = XEnd;
		_YEnd = YEnd;
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
	
	public void paint(Graphics g) {
		g.drawLine(_XStart, _YStart, _XEnd, _YEnd);
		//super.paint(g);
	}

	public int get_XStart() {
		return _XStart;
	}

	public void set_XStart(int _XStart) {
		this._XStart = _XStart;
	}

	public int get_YStart() {
		return _YStart;
	}

	public void set_YStart(int _YStart) {
		this._YStart = _YStart;
	}

	public int get_XEnd() {
		return _XEnd;
	}

	public void set_XEnd(int _XEnd) {
		this._XEnd = _XEnd;
	}

	public int get_YEnd() {
		return _YEnd;
	}

	public void set_YEnd(int _YEnd) {
		this._YEnd = _YEnd;
	}
	
}
