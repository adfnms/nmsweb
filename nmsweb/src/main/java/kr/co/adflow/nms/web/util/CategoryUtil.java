package kr.co.adflow.nms.web.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import kr.co.adflow.nms.web.exception.UtilException;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfo;
import kr.co.adflow.nms.web.vo.categoryDetail.CategoryInfoList;
import kr.co.adflow.nms.web.vo.servicesid.ServiceVo;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(DashBoardUtil.class);

	public String categoriesId(String data, ServiceVo serviceVo)
			throws UtilException {

		String resultId = null;
		String groupName=null;
		try {

			int serviceVosize = serviceVo.getServiceList().size();
			StringBuffer serviceidBuf = new StringBuffer();
			for (int i = 0; i < serviceVosize; i++) {
				if (data.contains(serviceVo.getServiceList().get(i)
						.getServiceName())) {
					serviceidBuf.append(serviceVo.getServiceList().get(i)
							.getServiceId()
							+ ",");
				}

			}

			// serviceidBuf.deleteCharAt(serviceidBuf.length() - 1);

			resultId = serviceidBuf.toString();
			if (resultId.length() > 1) {
				StringBuffer buf = new StringBuffer();
				buf.append(resultId);
				resultId = buf.deleteCharAt(buf.length() - 1).toString();
			}
		} catch (Exception e) {
			throw new UtilException(e);
		}
		return resultId;
	}

	public ArrayList categoriesIdArray(String data, ServiceVo serviceVo)
			throws UtilException {

		String resultId = null;
		ArrayList arr = new ArrayList();
		try {

			int serviceVosize = serviceVo.getServiceList().size();
			for (int i = 0; i < serviceVosize; i++) {
				if (data.contains(serviceVo.getServiceList().get(i)
						.getServiceName())) {
					arr.add(serviceVo.getServiceList().get(i).getServiceId());
				}

			}

		} catch (Exception e) {
			throw new UtilException(e);
		}
		return arr;
	}
	
	
	public String cateGoryJackSon(CategoryInfoList cateinfo) throws UtilException{
		String result=null;
		try{
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, cateinfo);
		result = writer.toString();
		logger.debug("CateInfoJsonresult:" + result);
		}catch(Exception e){
			throw new UtilException(e);
		}
		return result;
	}
	
	

}
