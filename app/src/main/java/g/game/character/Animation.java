package g.game.character;

public class Animation {
	protected int _actionFrameIndex = 0;  
	protected int _startFrame;
	protected int _endFrame;
	
	private boolean circulate = true;
	public Animation() {
		// TODO Auto-generated constructor stub
		_actionFrameIndex = _startFrame;
	}
	
	public void step(){
		
		if( _actionFrameIndex >=_endFrame|| _actionFrameIndex < _startFrame)
		{
			if(isCirculate())
				_actionFrameIndex = _startFrame;
		}
		else
			_actionFrameIndex ++;
	}

	public boolean isCirculate() {
		return circulate;
	}

	public void setCirculate(boolean circulate) {
		this.circulate = circulate;
	}
	
	protected boolean isEnd(){
		if(!isCirculate() && _actionFrameIndex == _endFrame)
			return true;
		return false;
	}
}
