//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.02.26 시간 05:54:19 PM KST 
//


package kr.co.adflow.nms.web.vo.notifications;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element name="uei" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rule" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="notice-queue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="text-message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeric-message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="event-severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/notifications}parameter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/notifications}varbind" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="writeable" type="{http://www.w3.org/2001/XMLSchema}string" default="yes" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "uei",
    "description",
    "rule",
    "noticeQueue",
    "destinationPath",
    "textMessage",
    "subject",
    "numericMessage",
    "eventSeverity",
    "parameter",
    "varbind"
})
@XmlRootElement(name = "notification")
public class Notification {

    @XmlElement(required = true)
    protected String uei;
    protected String description;
    @XmlElement(required = true)
    protected String rule;
    @XmlElement(name = "notice-queue")
    protected String noticeQueue;
    @XmlElement(required = true)
    protected String destinationPath;
    @XmlElement(name = "text-message", required = true)
    protected String textMessage;
    protected String subject;
    @XmlElement(name = "numeric-message")
    protected String numericMessage;
    @XmlElement(name = "event-severity")
    protected String eventSeverity;
    protected List<Parameter> parameter;
    protected Varbind varbind;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "status", required = true)
    protected String status;
    @XmlAttribute(name = "writeable")
    protected String writeable;

    /**
     * uei 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUei() {
        return uei;
    }

    /**
     * uei 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUei(String value) {
        this.uei = value;
    }

    /**
     * description 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * description 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * rule 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRule() {
        return rule;
    }

    /**
     * rule 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRule(String value) {
        this.rule = value;
    }

    /**
     * noticeQueue 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoticeQueue() {
        return noticeQueue;
    }

    /**
     * noticeQueue 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoticeQueue(String value) {
        this.noticeQueue = value;
    }

    /**
     * destinationPath 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationPath() {
        return destinationPath;
    }

    /**
     * destinationPath 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationPath(String value) {
        this.destinationPath = value;
    }

    /**
     * textMessage 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextMessage() {
        return textMessage;
    }

    /**
     * textMessage 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextMessage(String value) {
        this.textMessage = value;
    }

    /**
     * subject 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * subject 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * numericMessage 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumericMessage() {
        return numericMessage;
    }

    /**
     * numericMessage 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumericMessage(String value) {
        this.numericMessage = value;
    }

    /**
     * eventSeverity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventSeverity() {
        return eventSeverity;
    }

    /**
     * eventSeverity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventSeverity(String value) {
        this.eventSeverity = value;
    }

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parameter }
     * 
     * 
     */
    public List<Parameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<Parameter>();
        }
        return this.parameter;
    }

    /**
     * varbind 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Varbind }
     *     
     */
    public Varbind getVarbind() {
        return varbind;
    }

    /**
     * varbind 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Varbind }
     *     
     */
    public void setVarbind(Varbind value) {
        this.varbind = value;
    }

    /**
     * name 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * name 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * status 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * status 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * writeable 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWriteable() {
        if (writeable == null) {
            return "yes";
        } else {
            return writeable;
        }
    }

    /**
     * writeable 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWriteable(String value) {
        this.writeable = value;
    }

}
