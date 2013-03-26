package kr.co.adflow.nms.web.vo.categoryDetail;

import java.util.Hashtable;

public class CategoryMain {

	
	protected Hashtable<String, CategoryInfoList> cateGoryTable;
	
	public Hashtable<String, CategoryInfoList> getCateGoryTable() {
		if (cateGoryTable == null) {
			cateGoryTable = new Hashtable<String, CategoryInfoList>();
		}
		return this.cateGoryTable;
	}
	
	
}
