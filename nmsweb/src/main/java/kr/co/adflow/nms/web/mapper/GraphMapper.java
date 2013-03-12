package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.graph.GraphNodeList;
import kr.co.adflow.nms.web.vo.graph.GraphNodeVO;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphMapper {
	private static final Logger logger = LoggerFactory
			.getLogger(GraphMapper.class);

	/**
	 * singleton
	 * 
	 */
	private GraphMapper() {
	}

	public static GraphMapper mapper = new GraphMapper();

	public static GraphMapper getMapper() {
		return mapper;
	}

	// {total:"14", records:[{id:"node[106]", value:"Node: ", type:"Node"}
	public GraphNodeList graphInfo(String data) throws MapperException {
	
		GraphNodeList graphList=new GraphNodeList();
		try {
			logger.debug("mappingdata::" + data);
			logger.debug("mapper step...1");
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			logger.debug("mapper step...2");
			if (jNode.path("records").isArray()) {
				logger.debug("if..");
				JsonNode temp = null;

				Iterator<JsonNode> it = jNode.path("records").iterator();

				while (it.hasNext()) {
					logger.debug("mapper step...3");
					GraphNodeVO graphData = new GraphNodeVO();
					temp = it.next();
					graphData.setRecords(temp.path("id").getTextValue());
					graphList.getGraphs().add(graphData);
				}

				logger.debug("graphData.getRecords():::"
						+ graphList.getGraphs().get(0).getRecords());
				
			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return graphList;
	}

}
