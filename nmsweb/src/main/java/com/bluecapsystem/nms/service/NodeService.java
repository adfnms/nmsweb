package com.bluecapsystem.nms.service;

import java.util.List;
import java.util.Map;

import kr.co.adflow.nms.web.vo.requisition.RequisitionsNodes;

import com.bluecapsystem.nms.dto.NodeTbl;

public interface NodeService {
	List<NodeTbl> searchNodList(Map<String,Object> paramter);
	boolean nodeIdUpdate(Integer nodeid,Integer oldNodeid);
	boolean nodeRequisitionsUpdate(RequisitionsNodes renodes);
}
