package kr.co.adflow.nms.web.vo.group;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

//<group><name>adflow222</name><comments>The adflow222</comments><user>chan222</user></group>
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "comments", "user" })
@XmlRootElement(name = "group")
public class Groupinfo {

	public Groupinfo() {

	}

	@XmlElement(name = "name", required = true)
	protected String name;
	@XmlElement(name = "comments")
	protected String comments;
	@XmlElement(name = "user")
	protected String user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
