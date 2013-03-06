//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.02.27 시간 01:50:32 PM KST 
//


package kr.co.adflow.nms.web.vo.eventconf;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.adflow.nms.web.vo.eventconf package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.adflow.nms.web.vo.eventconf
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Snmp }
     * 
     */
    public Snmp createSnmp() {
        return new Snmp();
    }

    /**
     * Create an instance of {@link Events }
     * 
     */
    public Events createEvents() {
        return new Events();
    }

    /**
     * Create an instance of {@link Global }
     * 
     */
    public Global createGlobal() {
        return new Global();
    }

    /**
     * Create an instance of {@link Security }
     * 
     */
    public Security createSecurity() {
        return new Security();
    }

    /**
     * Create an instance of {@link Event }
     * 
     */
    public Event createEvent() {
        return new Event();
    }

    /**
     * Create an instance of {@link Mask }
     * 
     */
    public Mask createMask() {
        return new Mask();
    }

    /**
     * Create an instance of {@link Maskelement }
     * 
     */
    public Maskelement createMaskelement() {
        return new Maskelement();
    }

    /**
     * Create an instance of {@link Varbind }
     * 
     */
    public Varbind createVarbind() {
        return new Varbind();
    }

    /**
     * Create an instance of {@link Logmsg }
     * 
     */
    public Logmsg createLogmsg() {
        return new Logmsg();
    }

    /**
     * Create an instance of {@link Correlation }
     * 
     */
    public Correlation createCorrelation() {
        return new Correlation();
    }

    /**
     * Create an instance of {@link Autoaction }
     * 
     */
    public Autoaction createAutoaction() {
        return new Autoaction();
    }

    /**
     * Create an instance of {@link Varbindsdecode }
     * 
     */
    public Varbindsdecode createVarbindsdecode() {
        return new Varbindsdecode();
    }

    /**
     * Create an instance of {@link Decode }
     * 
     */
    public Decode createDecode() {
        return new Decode();
    }

    /**
     * Create an instance of {@link Operaction }
     * 
     */
    public Operaction createOperaction() {
        return new Operaction();
    }

    /**
     * Create an instance of {@link Autoacknowledge }
     * 
     */
    public Autoacknowledge createAutoacknowledge() {
        return new Autoacknowledge();
    }

    /**
     * Create an instance of {@link Tticket }
     * 
     */
    public Tticket createTticket() {
        return new Tticket();
    }

    /**
     * Create an instance of {@link Forward }
     * 
     */
    public Forward createForward() {
        return new Forward();
    }

    /**
     * Create an instance of {@link Script }
     * 
     */
    public Script createScript() {
        return new Script();
    }

    /**
     * Create an instance of {@link AlarmData }
     * 
     */
    public AlarmData createAlarmData() {
        return new AlarmData();
    }

}
