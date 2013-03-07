package kr.co.adflow.nms.web.util;

import java.util.ArrayList;

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

	public String graphUrl(String data) {

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
			for (int i = 1; i < arr.size(); i++) {
				logger.debug("arr:::" + arr.get(i));
				bf.append("{\"url\":\"");
				bf.append(opennmUrl);
				bf.append(arr.get(i));
				bf.append("\"}");
				bf.append(",");
			}
			bf.delete(bf.length()-1, bf.length());
			result = bf.toString();
			logger.debug("UTILresult::" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
