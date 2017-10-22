package g.game.vo;
public class UserInfoVO {

	private String _id; // id
	
	private String _passWord;
	
	private String _name;
	
	private int _type;
	
	public UserInfoVO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId(){
		return _id;
	}
	public void setId(String id){
		_id = id;
	}
	
	public String getPassword(){
		return _passWord;
	}
	public void setPassword(String pa){
		_passWord = pa;
	}
	
	public String getName(){
		return _name;
	}
	public void setName(String name){
		_name = name;
	}
	
	public int getType(){
		return  _type;
	}
	public void setType(int type){
		_type = type;
	}
	

}
