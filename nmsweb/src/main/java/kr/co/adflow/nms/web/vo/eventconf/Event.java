//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.02.27 시간 01:50:32 PM KST 
//


package kr.co.adflow.nms.web.vo.eventconf;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}mask" minOccurs="0"/>
 *         &lt;element name="uei" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="event-label" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}snmp" minOccurs="0"/>
 *         &lt;element name="descr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}logmsg"/>
 *         &lt;element name="severity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}correlation" minOccurs="0"/>
 *         &lt;element name="operinstruct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}autoaction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}varbindsdecode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}operaction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}autoacknowledge" minOccurs="0"/>
 *         &lt;element name="loggroup" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}tticket" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}forward" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}script" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mouseovertext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}alarm-data" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mask",
    "uei",
    "eventLabel",
    "snmp",
    "descr",
    "logmsg",
    "severity",
    "correlation",
    "operinstruct",
    "autoaction",
    "varbindsdecode",
    "operaction",
    "autoacknowledge",
    "loggroup",
    "tticket",
    "forward",
    "script",
    "mouseovertext",
    "alarmData"
})
@XmlRootElement(name = "event")
public class Event {

    protected Mask mask;
    @XmlElement(required = true)
    protected String uei;
    @XmlElement(name = "event-label", required = true)
    protected String eventLabel;
    protected Snmp snmp;
    @XmlElement(required = true)
    protected String descr;
    @XmlElement(required = true)
    protected Logmsg logmsg;
    @XmlElement(required = true)
    protected String severity;
    protected Correlation correlation;
    protected String operinstruct;
    protected List<Autoaction> autoaction;
    protected List<Varbindsdecode> varbindsdecode;
    protected List<Operaction> operaction;
    protected Autoacknowledge autoacknowledge;
    protected List<String> loggroup;
    protected Tticket tticket;
    protected List<Forward> forward;
    protected List<Script> script;
    protected String mouseovertext;
    @XmlElement(name = "alarm-data")
    protected AlarmData alarmData;

    /**
     * The event mask which helps to uniquely identify an
     *             event
     * 
     * @return
     *     possible object is
     *     {@link Mask }
     *     
     */
    public Mask getMask() {
        return mask;
    }

    /**
     * mask 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Mask }
     *     
     */
    public void setMask(Mask value) {
        this.mask = value;
    }

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
     * eventLabel 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventLabel() {
        return eventLabel;
    }

    /**
     * eventLabel 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventLabel(String value) {
        this.eventLabel = value;
    }

    /**
     * The snmp information from the trap
     * 
     * @return
     *     possible object is
     *     {@link Snmp }
     *     
     */
    public Snmp getSnmp() {
        return snmp;
    }

    /**
     * snmp 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Snmp }
     *     
     */
    public void setSnmp(Snmp value) {
        this.snmp = value;
    }

    /**
     * descr 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescr() {
        return descr;
    }

    /**
     * descr 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescr(String value) {
        this.descr = value;
    }

    /**
     * The event logmsg
     * 
     * @return
     *     possible object is
     *     {@link Logmsg }
     *     
     */
    public Logmsg getLogmsg() {
        return logmsg;
    }

    /**
     * logmsg 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Logmsg }
     *     
     */
    public void setLogmsg(Logmsg value) {
        this.logmsg = value;
    }

    /**
     * severity 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * severity 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverity(String value) {
        this.severity = value;
    }

    /**
     * The event correlation information
     * 
     * @return
     *     possible object is
     *     {@link Correlation }
     *     
     */
    public Correlation getCorrelation() {
        return correlation;
    }

    /**
     * correlation 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Correlation }
     *     
     */
    public void setCorrelation(Correlation value) {
        this.correlation = value;
    }

    /**
     * operinstruct 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperinstruct() {
        return operinstruct;
    }

    /**
     * operinstruct 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperinstruct(String value) {
        this.operinstruct = value;
    }

    /**
     * The automatic action to occur when this event
     *             occurs Gets the value of the autoaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autoaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutoaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Autoaction }
     * 
     * 
     */
    public List<Autoaction> getAutoaction() {
        if (autoaction == null) {
            autoaction = new ArrayList<Autoaction>();
        }
        return this.autoaction;
    }

    /**
     * The varbind decoding tag used to decode value 
     *             into a string Gets the value of the varbindsdecode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the varbindsdecode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVarbindsdecode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Varbindsdecode }
     * 
     * 
     */
    public List<Varbindsdecode> getVarbindsdecode() {
        if (varbindsdecode == null) {
            varbindsdecode = new ArrayList<Varbindsdecode>();
        }
        return this.varbindsdecode;
    }

    /**
     * The operator action to be taken when this event
     *             occurs Gets the value of the operaction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operaction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperaction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Operaction }
     * 
     * 
     */
    public List<Operaction> getOperaction() {
        if (operaction == null) {
            operaction = new ArrayList<Operaction>();
        }
        return this.operaction;
    }

    /**
     * The autoacknowledge information for the
     *             user
     * 
     * @return
     *     possible object is
     *     {@link Autoacknowledge }
     *     
     */
    public Autoacknowledge getAutoacknowledge() {
        return autoacknowledge;
    }

    /**
     * autoacknowledge 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Autoacknowledge }
     *     
     */
    public void setAutoacknowledge(Autoacknowledge value) {
        this.autoacknowledge = value;
    }

    /**
     * Gets the value of the loggroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loggroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoggroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLoggroup() {
        if (loggroup == null) {
            loggroup = new ArrayList<String>();
        }
        return this.loggroup;
    }

    /**
     * The trouble ticket info
     * 
     * @return
     *     possible object is
     *     {@link Tticket }
     *     
     */
    public Tticket getTticket() {
        return tticket;
    }

    /**
     * tticket 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Tticket }
     *     
     */
    public void setTticket(Tticket value) {
        this.tticket = value;
    }

    /**
     * The forwarding information for this
     *             event Gets the value of the forward property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forward property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForward().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Forward }
     * 
     * 
     */
    public List<Forward> getForward() {
        if (forward == null) {
            forward = new ArrayList<Forward>();
        }
        return this.forward;
    }

    /**
     * The script information for this
     *             event Gets the value of the script property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the script property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getScript().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Script }
     * 
     * 
     */
    public List<Script> getScript() {
        if (script == null) {
            script = new ArrayList<Script>();
        }
        return this.script;
    }

    /**
     * mouseovertext 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMouseovertext() {
        return mouseovertext;
    }

    /**
     * mouseovertext 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMouseovertext(String value) {
        this.mouseovertext = value;
    }

    /**
     * Data used to create an event.
     * 
     * @return
     *     possible object is
     *     {@link AlarmData }
     *     
     */
    public AlarmData getAlarmData() {
        return alarmData;
    }

    /**
     * alarmData 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link AlarmData }
     *     
     */
    public void setAlarmData(AlarmData value) {
        this.alarmData = value;
    }

}
