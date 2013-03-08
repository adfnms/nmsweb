package kr.co.adflow.nms.web.util;

import java.util.ArrayList;

import kr.co.adflow.nms.web.exception.UtilException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReportUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(ReportUtil.class);
	private @Value("#{config['OPENNMS']}")
	String opennmUrl;

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
				bf.append(opennmUrl);
				bf.append(arr.get(i)+"\"");
				bf.append(",");

			}
			bf.delete(bf.length()-3, bf.length());
			bf.append("\"}");
			result = bf.toString();
			logger.debug("UTILresult::" + result);
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return result;

	}
}
