package kr.co.adflow.nms.web.vo.servicesid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class ServiceVo {
	
	  protected List<ServiceIdNameVo> idName;

	    public List<ServiceIdNameVo> getServiceList() {
	        if (idName == null) {
	        	idName = new ArrayList<ServiceIdNameVo>();
	        }
	        return this.idName;
	    }

}
