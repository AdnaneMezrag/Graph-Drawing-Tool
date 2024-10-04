package Graph;

import java.io.Serializable;

public class clsNode implements Serializable {

	private String _NodeValue ;
	private int _Positin;
	private int _XCordinate;
	private int _YCordinate;
	
	
	public String get_NodeValue() {
		return _NodeValue;
	}
	public void set_NodeValue(String _NodeValue) {
		this._NodeValue = _NodeValue;
	}
	public int get_XCordinate() {
		return _XCordinate;
	}
	public void set_XCordinate(int _XCordinate) {
		this._XCordinate = _XCordinate;
	}
	public int get_YCordinate() {
		return _YCordinate;
	}
	public void set_YCordinate(int _YCordinate) {
		this._YCordinate = _YCordinate;
	}
	public int get_Positin() {
		return _Positin;
	}
	public void set_Positin(int _Positin) {
		this._Positin = _Positin;
	}
	
}
