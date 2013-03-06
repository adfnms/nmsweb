package kr.co.adflow.nms.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import kr.co.adflow.nms.web.service.NotificationsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class XmlUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(NotificationsService.class);
	
	public Object xmlRead(String filePath, Class classname, Object ob) throws Exception {
		
        try {
        	
        	logger.debug("xml Read ::"+filePath+"Class::"+classname.toString() +"obj::"+ ob.toString());

            JAXBContext jc = JAXBContext.newInstance(classname);
            Unmarshaller u = jc.createUnmarshaller();
 
//            File f = new File("d:\\OpenNMS\\etc\\destinationPaths.xml");
            File f = new File(filePath);
            ob = u.unmarshal(f);
            
            logger.debug("xml Read : success");
            
        } catch (JAXBException e) {
        	throw e;
        }
        return ob;
		
	}
	
	public String xmlWrite(String filePath, Class classname, Object ob)
			throws Exception {
		
		String result = "Faile";

		try {
			
			logger.debug("xml Write ::"+filePath+"Class::"+classname.toString() +"obj::"+ ob.toString());

			JAXBContext jc = JAXBContext.newInstance(classname);

			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			FileOutputStream fout = new FileOutputStream(filePath);

			m.marshal(ob, fout);
			
			logger.debug("xml Write : success");

			fout.close();
			
			result = "success";

		} catch (JAXBException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}

		return result;

	}

}
