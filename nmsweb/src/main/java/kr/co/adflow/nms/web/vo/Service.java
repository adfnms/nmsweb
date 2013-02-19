package kr.co.adflow.nms.web.vo;

public class Service {
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIpInterfaceId() {
		return ipInterfaceId;
	}


	public void setIpInterfaceId(String ipInterfaceId) {
		this.ipInterfaceId = ipInterfaceId;
	}


	public String getNotify() {
		return notify;
	}


	public void setNotify(String notify) {
		this.notify = notify;
	}


	public ServiceType getServiceType() {
		return serviceType;
	}


	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	private String status;
	private String source;
	private String id;
	private String ipInterfaceId;
	private String notify;
	private ServiceType serviceType;
	
	
	public static class ServiceType {
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		private String id;
		private String name;
		
	}

}
