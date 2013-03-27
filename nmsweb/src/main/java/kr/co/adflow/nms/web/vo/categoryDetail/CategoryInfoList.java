package kr.co.adflow.nms.web.vo.categoryDetail;

import java.util.Hashtable;


public class CategoryInfoList {
	
	protected Hashtable<String, CategoryInfo> cateGoryInfo;
	
	public Hashtable<String, CategoryInfo> getCateGoryInfo() {
		if (cateGoryInfo == null) {
			cateGoryInfo = new Hashtable<String, CategoryInfo>();
		}
		return this.cateGoryInfo;
	}
 
}
