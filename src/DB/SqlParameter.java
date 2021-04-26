package DB;

//sql 내부 변수들 
//매개 변수들 

public class SqlParameter {
	
	private String userid;
	private String userpw;
	private String username;
	private String phone;
	private String gender;
	private String birth;
	private String commets;
	
	public SqlParameter() {}
	public SqlParameter(String userid, String userpw, String username, String phone, String gender, String birth,
			String commets) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.commets = commets;
		
	}
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCommets() {
		return commets;
	}
	public void setCommets(String commets) {
		this.commets = commets;
	}
	
	
	@Override
	public String toString() {
		return "SqlParameter [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", phone=" + phone
				+ ", gender=" + gender + ", birth=" + birth + ", commets=" + commets + "]";
	}
	
	

}
