package kr.co.adflow.nms.web;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TestController
 * 
 * @author typark@adflow.co.kr
 * @version 1.0
 */
@Controller
public class TestController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	/**
	 * sendNewSuspectEvent
	 * 
	 * @return The value input as a String.
	 * @exception Exception
	 */
	@RequestMapping(value = "/sendNewSuspectEvent", method = RequestMethod.GET)
	public @ResponseBody
	String sendNewSuspectEvent() throws Exception {

		logger.info("sendNewSuspectEvent entry");

		try {

			ClassLoader parent = getClass().getClassLoader();
			GroovyClassLoader loader = new GroovyClassLoader(parent);
			Class groovyClass = loader.parseClass(new File(
					"src/test/groovy/script/sendNewSuspectEvent.groovy"));

			// let's call some method on an instance
			GroovyObject groovyObject = (GroovyObject) groovyClass
					.newInstance();
			String[] args = { "127.0.0.2", "192.168.112.128" };
			Object ret = groovyObject.invokeMethod("sendNewSuspectEvent", args);
			logger.debug("ret :" + ret);
			return "{\"result\":\"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"error\":\"{\"code\":\"100\",\"message\":\""
					+ e.getMessage() + "\"}\"}";
		}
	}
}
