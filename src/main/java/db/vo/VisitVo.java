package db.vo;

// VO(Value Object)
// 3위일체 이름똑같이 맞춰라 3개
// DB Column == VO Property == Form Parameter
public class VisitVo {
	int idx;
	String name;
	String content;
	String pwd;
	String ip;
	String regdate;
	String gdgd;
	
	
	//생성자만들어서 값을 넣으면 초기화생성자(아무것도없는)걸만들어야함
	public VisitVo() {
		// TODO Auto-generated constructor stub
	}
	
	

	//insert용 포장 생성자
	public VisitVo(String name, String content, String pwd, String ip) {
		super();
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}

	//update수정용 포장 생성자
	public String getPwd() {
		return pwd;
	}
	public VisitVo(int idx, String name, String content, String pwd, String ip) {
		super();
		this.idx = idx;
		this.name = name;
		this.content = content;
		this.pwd = pwd;
		this.ip = ip;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRegdate() {

		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
