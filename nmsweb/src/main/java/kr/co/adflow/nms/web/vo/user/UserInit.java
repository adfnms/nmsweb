package kr.co.adflow.nms.web.vo.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "userId", "fullName", "userComments",
		"password", })
@XmlRootElement(name = "user")
public class UserInit {

	public UserInit() {

	}

	@XmlElement(name = "user-id", required = true)
	protected String userId;
	@XmlElement(name = "full-name")
	protected String fullName;
	@XmlElement(name = "user-comments")
	protected String userComments;
	protected String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
