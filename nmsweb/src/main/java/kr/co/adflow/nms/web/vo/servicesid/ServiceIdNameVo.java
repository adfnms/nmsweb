package kr.co.adflow.nms.web.vo.servicesid;

import org.springframework.stereotype.Service;

@Service
public class ServiceIdNameVo {

	private String serviceId;
	private String serviceName;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
