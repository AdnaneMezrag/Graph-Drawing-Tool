package Graph;

public class clsEdge {

	private clsNode _StartNode;
	private clsNode _EndNode;
	private int _Weigth;
	
	public clsNode get_StartNode() {
		return _StartNode;
	}
	public void set_StartNode(clsNode _StartNode) {
		this._StartNode = _StartNode;
	}
	public clsNode get_EndNode() {
		return _EndNode;
	}
	public void set_EndNode(clsNode _EndNode) {
		this._EndNode = _EndNode;
	}
}
