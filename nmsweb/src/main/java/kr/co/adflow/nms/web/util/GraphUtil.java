package kr.co.adflow.nms.web.util;

import java.util.ArrayList;

import kr.co.adflow.nms.web.exception.MapperException;
import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.mapper.GraphMapper;
import kr.co.adflow.nms.web.vo.graph.GraphNodeList;
import kr.co.adflow.nms.web.vo.graph.GraphNodeVO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GraphUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(GraphUtil.class);

	private @Value("#{config['OPENNMSDEFAULTURL']}")
	String opennmsDefaultUrl;

	public String graphNode(String grNode) throws UtilException {
		String result = null;
		try {
			Document doc = Jsoup.parse(grNode);
			// print all available links on page
			Elements links = doc.select("script");
			Element el = null;
			ArrayList<String> arr = new ArrayList<String>();
			for (int i = 0; i < links.size(); i++) {
				el = links.get(i);
				arr.add(el.data().trim());
				logger.debug("data:::" + el.data());
			}

			for (int i = 0; i < arr.size(); i++) {
				String data = arr.get(i);
				logger.debug("arrData::" + data);
				if (arr.get(i).length() > 1) {
					String subData = arr.get(i).substring(4, 24);
					if (subData.equals("standardResourceData")) {
						String graphJson = arr.get(i);
						logger.debug("graphJson::" + graphJson);
						String subStr = graphJson.substring(27,
								graphJson.length());
						result = subStr.replaceAll("\\p{Space}", "");
						result = result.replaceAll("total", "\"total\"");
						result = result.replaceAll("records", "\"records\"");
						result = result.replaceAll("id", "\"id\"");
						result = result.replaceAll("value", "\"value\"");
						result = result.replaceAll("type", "\"type\"");
						logger.debug("result::" + result);

					}

				}
			}
		} catch (Exception e) {
			throw new UtilException(e);
		}

		return result;
	}

	public String graphNodeJson(String parsingData) throws MapperException,
			UtilException {
		String result = null;
		try {
			GraphMapper mapper = GraphMapper.getMapper();
			GraphNodeList gr = mapper.graphInfo(parsingData);
			java.util.List<GraphNodeVO> listdata = gr.getGraphs();

			StringBuffer strBuf = new StringBuffer();
			strBuf.append("{");
			for (int i = 0; i < listdata.size(); i++) {
				logger.debug("succed::" + listdata.get(i).getRecords());
				strBuf.append("\"resourceID" + i + "\":");
				strBuf.append("\"" + listdata.get(i).getRecords() + "\"");
				strBuf.append(",");
			}
			strBuf.delete(strBuf.length() - 1, strBuf.length());
			strBuf.append("}");

			result = strBuf.toString();
		} catch (Exception e) {
			throw new UtilException(e);
		}

		return result;
	}

	public String graphDetail(String detailGraph) throws UtilException {
		String result = null;
		try {
			Document doc = Jsoup.parse(detailGraph);
			// print all available links on page
			Elements links = doc.select("script");
			Element el = null;
			ArrayList<String> arr = new ArrayList<String>();
			for (int i = 0; i < links.size(); i++) {
				el = links.get(i);
				arr.add(el.data().trim());
				logger.debug("data:::" + el.data());
			}

			for (int i = 0; i < arr.size(); i++) {
				String data = arr.get(i);
				logger.debug("arrData::" + data);
				// arr.get(i).replaceAll("\\p{Space}", "");
				// logger.debug("replacedata:"+arr.get(i).replaceAll("\\p{Space}",
				// ""));
				if (arr.get(i).length() > 1) {
					String subData = arr.get(i).substring(0, 8);
					logger.debug("subDATA::" + subData);
					if (subData.equals("var data")) {
						String graphJson = arr.get(i);
						logger.debug("graphJson::" + graphJson);
						String subStr = graphJson.substring(11,
								graphJson.length());
						result = subStr.replaceAll("\\p{Space}", "");
						result = result.replaceAll("total", "\"total\"");
						result = result.replaceAll("records", "\"records\"");
						result = result.replaceAll("id", "\"id\"");
						result = result.replaceAll("value", "\"value\"");
						result = result.replaceAll("type", "\"type\"");
						logger.debug("result::" + result);

					}

				}
			}
		} catch (Exception e) {
			throw new UtilException(e);
		}

		return result;
	}

	public String graphDetailJson(String parsingData) throws MapperException,
			UtilException {
		String result = null;
		try {
			GraphMapper mapper = GraphMapper.getMapper();
			GraphNodeList gr = mapper.graphInfo(parsingData);
			java.util.List<GraphNodeVO> listdata = gr.getGraphs();

			StringBuffer strBuf = new StringBuffer();
			strBuf.append("{");
			for (int i = 0; i < listdata.size(); i++) {
				logger.debug("succed::" + listdata.get(i).getRecords());
				strBuf.append("\"resourceID" + i + "\":");
				strBuf.append("\"" + listdata.get(i).getRecords() + "\"");
				strBuf.append(",");
			}
			strBuf.delete(strBuf.length() - 1, strBuf.length());
			strBuf.append("}");

			result = strBuf.toString();
		} catch (Exception e) {
			throw new UtilException(e);
		}

		return result;
	}

	public String graphUrl(String data) throws UtilException {

		String result = "";
		try {
			Document doc = Jsoup.parse(data);
			// print all available links on page
			Elements links = doc.select("img");
			Element el = null;
			ArrayList arr = new ArrayList();
			for (int i = 0; i < links.size(); i++) {
				el = links.get(i);
				arr.add(el.attr("src"));
				logger.debug("src:::" + el.attr("src"));
			}

			StringBuffer bf = new StringBuffer();
			bf.append("{");
			for (int i = 1; i < arr.size(); i++) {
				logger.debug("arr:::" + arr.get(i));
				bf.append("\"url" + i + "\":\"");
				bf.append(opennmsDefaultUrl);
				bf.append(arr.get(i) + "\"");
				bf.append(",");

			}
			bf.delete(bf.length() - 3, bf.length());
			bf.append("\"}");
			result = bf.toString();
			logger.debug("UTILresult::" + result);
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return result;

	}

}
