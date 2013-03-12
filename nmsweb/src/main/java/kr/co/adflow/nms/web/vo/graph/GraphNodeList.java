package kr.co.adflow.nms.web.vo.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeList {
	
	 public List<GraphNodeVO> graph;
	 
	 public List<GraphNodeVO> getGraphs() {
	        if (graph == null) {
	        	graph = new ArrayList<GraphNodeVO>();
	        }
	        return this.graph;
	    }

}
