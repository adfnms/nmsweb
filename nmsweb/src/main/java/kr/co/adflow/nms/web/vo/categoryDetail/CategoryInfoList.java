package kr.co.adflow.nms.web.vo.categoryDetail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryInfoList {
	
	protected List<CategoryInfo> nodeinfo;
	
	public List<CategoryInfo> getnodeInfo() {
		if (nodeinfo == null) {
			nodeinfo = new ArrayList<CategoryInfo>();
		}
		return this.nodeinfo;
	}
	

}
