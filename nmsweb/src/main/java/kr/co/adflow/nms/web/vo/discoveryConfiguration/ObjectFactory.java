//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.03.11 시간 11:47:53 AM KST 
//


package kr.co.adflow.nms.web.vo.discoveryConfiguration;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.adflow.nms.web.vo.discoveryConfiguration package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.adflow.nms.web.vo.discoveryConfiguration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExcludeRange }
     * 
     */
    public ExcludeRange createExcludeRange() {
        return new ExcludeRange();
    }

    /**
     * Create an instance of {@link IncludeUrl }
     * 
     */
    public IncludeUrl createIncludeUrl() {
        return new IncludeUrl();
    }

    /**
     * Create an instance of {@link Specific }
     * 
     */
    public Specific createSpecific() {
        return new Specific();
    }

    /**
     * Create an instance of {@link IncludeRange }
     * 
     */
    public IncludeRange createIncludeRange() {
        return new IncludeRange();
    }

    /**
     * Create an instance of {@link DiscoveryConfiguration }
     * 
     */
    public DiscoveryConfiguration createDiscoveryConfiguration() {
        return new DiscoveryConfiguration();
    }

}
