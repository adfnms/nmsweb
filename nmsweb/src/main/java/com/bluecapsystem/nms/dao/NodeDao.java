package com.bluecapsystem.nms.dao;

import java.util.List;
import java.util.Map;

import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;

import com.bluecapsystem.nms.dto.NodeTbl;

public interface NodeDao {
	List<NodeTbl> searchNodList(Map<String,Object> paramter);
	boolean nodeIdUpdate(Map<String,Object> paramter);
	boolean interfaceNodeIdUpdate(Map<String,Object> paramter);
	boolean serviceNodeIdUpdate(Map<String,Object> paramter);
	boolean nodeRequisitionsUpdate(RequisitionsNodes renodes);
	
	
}
