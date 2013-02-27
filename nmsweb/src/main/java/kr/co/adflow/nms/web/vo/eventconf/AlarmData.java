//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.02.27 시간 01:50:32 PM KST 
//


package kr.co.adflow.nms.web.vo.eventconf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="reduction-key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="alarm-type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="clear-key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="auto-clean" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="x733-alarm-type" type="{http://xmlns.opennms.org/xsd/eventconf}x733-alarm-type" />
 *       &lt;attribute name="x733-probable-cause" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "alarm-data")
public class AlarmData {

    @XmlAttribute(name = "reduction-key", required = true)
    protected String reductionKey;
    @XmlAttribute(name = "alarm-type", required = true)
    protected int alarmType;
    @XmlAttribute(name = "clear-key")
    protected String clearKey;
    @XmlAttribute(name = "auto-clean")
    protected Boolean autoClean;
    @XmlAttribute(name = "x733-alarm-type")
    protected String x733AlarmType;
    @XmlAttribute(name = "x733-probable-cause")
    protected Integer x733ProbableCause;

    /**
     * reductionKey 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReductionKey() {
        return reductionKey;
    }

    /**
     * reductionKey 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReductionKey(String value) {
        this.reductionKey = value;
    }

    /**
     * alarmType 속성의 값을 가져옵니다.
     * 
     */
    public int getAlarmType() {
        return alarmType;
    }

    /**
     * alarmType 속성의 값을 설정합니다.
     * 
     */
    public void setAlarmType(int value) {
        this.alarmType = value;
    }

    /**
     * clearKey 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearKey() {
        return clearKey;
    }

    /**
     * clearKey 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearKey(String value) {
        this.clearKey = value;
    }

    /**
     * autoClean 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAutoClean() {
        if (autoClean == null) {
            return false;
        } else {
            return autoClean;
        }
    }

    /**
     * autoClean 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoClean(Boolean value) {
        this.autoClean = value;
    }

    /**
     * x733AlarmType 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX733AlarmType() {
        return x733AlarmType;
    }

    /**
     * x733AlarmType 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX733AlarmType(String value) {
        this.x733AlarmType = value;
    }

    /**
     * x733ProbableCause 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getX733ProbableCause() {
        return x733ProbableCause;
    }

    /**
     * x733ProbableCause 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setX733ProbableCause(Integer value) {
        this.x733ProbableCause = value;
    }

}
