package kr.co.adflow.nms.web.mapper;

import java.util.Iterator;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.vo.graph.GraphNodeList;
import kr.co.adflow.nms.web.vo.graph.GraphNodeVO;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GraphMapper {
	private static final Logger logger = LoggerFactory
			.getLogger(GraphMapper.class);

	public GraphNodeList graphInfo(String data) throws MapperException {

		GraphNodeList graphList = new GraphNodeList();
		try {
			logger.debug("mappingdata::" + data);
			ObjectMapper om = new ObjectMapper();
			JsonNode jNode = om.readTree(data);
			if (jNode.path("records").isArray()) {
				JsonNode temp = null;
				Iterator<JsonNode> it = jNode.path("records").iterator();
				while (it.hasNext()) {
					GraphNodeVO graphData = new GraphNodeVO();
					temp = it.next();
					graphData.setRecords(temp.path("id").getTextValue());
					graphList.getGraphs().add(graphData);
				}

				logger.debug(" graphList.getGraphs().get(0).getRecords()::"
						+ graphList.getGraphs().get(0).getRecords());

			}
		} catch (Exception e) {
			throw new MapperException(e);

		}
		return graphList;
	}

}
