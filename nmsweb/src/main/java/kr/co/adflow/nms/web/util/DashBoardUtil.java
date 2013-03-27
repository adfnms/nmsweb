package kr.co.adflow.nms.web.util;

import java.util.ArrayList;

import kr.co.adflow.nms.web.exception.UtilException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DashBoardUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(DashBoardUtil.class);

	public String dashBoardUrl(String data) throws UtilException {

		String result = "";
		try {
			Document doc = Jsoup.parse(data);
			// print all available links on page
			Elements table = doc.select("table");
			Element el=null;
			for (int i = 0; i < table.size(); i++) {
				 el=table.get(i);
				 String data2=el.attr("td");
				 logger.debug("data2:"+data2);
			}
			
		
			
			logger.debug("links.text()::" + table.text());
			String temp = table.text();
			String firstName = temp.substring(0, 32);
			StringBuffer buf = new StringBuffer();
			if (firstName.equals("Categories Outages Availability ")) {
				logger.debug("succed");
				temp = temp.substring(32);
				logger.debug("temp::"+temp);
				String netWork = temp.substring(0, 19);
				netWork = netWork.trim();
				logger.debug("netWork::" + netWork);
			} else {
				throw new UtilException("Categories Outages failed....");
			}

		} catch (Exception e) {
			throw new UtilException(e);
		}
		return result;

	}

}
