package kr.co.adflow.nms.web.vo;

public class SchoedOutage {


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	private String type;
	private String name;

	private Time time;
	private Node node;
	

	public static class Time {
		
		
		public String getEnds() {
			return ends;
		}
		public void setEnds(String ends) {
			this.ends = ends;
		}
		public String getBegins() {
			return begins;
		}
		public void setBegins(String begins) {
			this.begins = begins;
		}
		
		private String ends;
		private String begins;
		
	}
	
	public static class Node {
		
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
	
}
