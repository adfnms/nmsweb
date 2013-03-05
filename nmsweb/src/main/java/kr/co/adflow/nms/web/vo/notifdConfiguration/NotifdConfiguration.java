//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.02.26 시간 05:55:51 PM KST 
//


package kr.co.adflow.nms.web.vo.notifdConfiguration;

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
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/notifd}auto-acknowledge" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/notifd}queue" maxOccurs="unbounded"/>
 *         &lt;element name="outage-calendar" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="status" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pages-sent" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT * FROM notifications" />
 *       &lt;attribute name="next-notif-id" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT nextval('notifynxtid')" />
 *       &lt;attribute name="next-user-notif-id" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT nextval('userNotifNxtId')" />
 *       &lt;attribute name="next-group-id" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT nextval('notifygrpid')" />
 *       &lt;attribute name="service-id-sql" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT serviceID from service where serviceName = ?" />
 *       &lt;attribute name="outstanding-notices-sql" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT notifyid FROM notifications where notifyId = ? AND respondTime is not null" />
 *       &lt;attribute name="acknowledge-id-sql" type="{http://www.w3.org/2001/XMLSchema}string" default="SELECT notifyid FROM notifications WHERE eventuei=? AND nodeid=? AND interfaceid=? AND serviceid=?" />
 *       &lt;attribute name="acknowledge-update-sql" type="{http://www.w3.org/2001/XMLSchema}string" default="UPDATE notifications SET answeredby=?, respondtime=? WHERE notifyId=?" />
 *       &lt;attribute name="match-all" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="email-address-command" type="{http://www.w3.org/2001/XMLSchema}string" default="javaEmail" />
 *       &lt;attribute name="numeric-skip-resolution-prefix" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "autoAcknowledge",
    "queue",
    "outageCalendar"
})
@XmlRootElement(name = "notifd-configuration")
public class NotifdConfiguration {

    @XmlElement(name = "auto-acknowledge")
    protected List<AutoAcknowledge> autoAcknowledge;
    @XmlElement(required = true)
    protected List<Queue> queue;
    @XmlElement(name = "outage-calendar")
    protected List<String> outageCalendar;
    @XmlAttribute(name = "status", required = true)
    protected String status;
    @XmlAttribute(name = "pages-sent")
    protected String pagesSent;
    @XmlAttribute(name = "next-notif-id")
    protected String nextNotifId;
    @XmlAttribute(name = "next-user-notif-id")
    protected String nextUserNotifId;
    @XmlAttribute(name = "next-group-id")
    protected String nextGroupId;
    @XmlAttribute(name = "service-id-sql")
    protected String serviceIdSql;
    @XmlAttribute(name = "outstanding-notices-sql")
    protected String outstandingNoticesSql;
    @XmlAttribute(name = "acknowledge-id-sql")
    protected String acknowledgeIdSql;
    @XmlAttribute(name = "acknowledge-update-sql")
    protected String acknowledgeUpdateSql;
    @XmlAttribute(name = "match-all", required = true)
    protected boolean matchAll;
    @XmlAttribute(name = "email-address-command")
    protected String emailAddressCommand;
    @XmlAttribute(name = "numeric-skip-resolution-prefix")
    protected Boolean numericSkipResolutionPrefix;

    /**
     * Gets the value of the autoAcknowledge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the autoAcknowledge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutoAcknowledge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AutoAcknowledge }
     * 
     * 
     */
    public List<AutoAcknowledge> getAutoAcknowledge() {
        if (autoAcknowledge == null) {
            autoAcknowledge = new ArrayList<AutoAcknowledge>();
        }
        return this.autoAcknowledge;
    }

    /**
     * Gets the value of the queue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Queue }
     * 
     * 
     */
    public List<Queue> getQueue() {
        if (queue == null) {
            queue = new ArrayList<Queue>();
        }
        return this.queue;
    }

    /**
     * Gets the value of the outageCalendar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outageCalendar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutageCalendar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOutageCalendar() {
        if (outageCalendar == null) {
            outageCalendar = new ArrayList<String>();
        }
        return this.outageCalendar;
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
     * pagesSent 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagesSent() {
        if (pagesSent == null) {
            return "SELECT * FROM notifications";
        } else {
            return pagesSent;
        }
    }

    /**
     * pagesSent 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagesSent(String value) {
        this.pagesSent = value;
    }

    /**
     * nextNotifId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextNotifId() {
        if (nextNotifId == null) {
            return "SELECT nextval('notifynxtid')";
        } else {
            return nextNotifId;
        }
    }

    /**
     * nextNotifId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextNotifId(String value) {
        this.nextNotifId = value;
    }

    /**
     * nextUserNotifId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextUserNotifId() {
        if (nextUserNotifId == null) {
            return "SELECT nextval('userNotifNxtId')";
        } else {
            return nextUserNotifId;
        }
    }

    /**
     * nextUserNotifId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextUserNotifId(String value) {
        this.nextUserNotifId = value;
    }

    /**
     * nextGroupId 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextGroupId() {
        if (nextGroupId == null) {
            return "SELECT nextval('notifygrpid')";
        } else {
            return nextGroupId;
        }
    }

    /**
     * nextGroupId 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextGroupId(String value) {
        this.nextGroupId = value;
    }

    /**
     * serviceIdSql 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceIdSql() {
        if (serviceIdSql == null) {
            return "SELECT serviceID from service where serviceName = ?";
        } else {
            return serviceIdSql;
        }
    }

    /**
     * serviceIdSql 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceIdSql(String value) {
        this.serviceIdSql = value;
    }

    /**
     * outstandingNoticesSql 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutstandingNoticesSql() {
        if (outstandingNoticesSql == null) {
            return "SELECT notifyid FROM notifications where notifyId = ? AND respondTime is not null";
        } else {
            return outstandingNoticesSql;
        }
    }

    /**
     * outstandingNoticesSql 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutstandingNoticesSql(String value) {
        this.outstandingNoticesSql = value;
    }

    /**
     * acknowledgeIdSql 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcknowledgeIdSql() {
        if (acknowledgeIdSql == null) {
            return "SELECT notifyid FROM notifications WHERE eventuei=? AND nodeid=? AND interfaceid=? AND serviceid=?";
        } else {
            return acknowledgeIdSql;
        }
    }

    /**
     * acknowledgeIdSql 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcknowledgeIdSql(String value) {
        this.acknowledgeIdSql = value;
    }

    /**
     * acknowledgeUpdateSql 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcknowledgeUpdateSql() {
        if (acknowledgeUpdateSql == null) {
            return "UPDATE notifications SET answeredby=?, respondtime=? WHERE notifyId=?";
        } else {
            return acknowledgeUpdateSql;
        }
    }

    /**
     * acknowledgeUpdateSql 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcknowledgeUpdateSql(String value) {
        this.acknowledgeUpdateSql = value;
    }

    /**
     * matchAll 속성의 값을 가져옵니다.
     * 
     */
    public boolean isMatchAll() {
        return matchAll;
    }

    /**
     * matchAll 속성의 값을 설정합니다.
     * 
     */
    public void setMatchAll(boolean value) {
        this.matchAll = value;
    }

    /**
     * emailAddressCommand 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddressCommand() {
        if (emailAddressCommand == null) {
            return "javaEmail";
        } else {
            return emailAddressCommand;
        }
    }

    /**
     * emailAddressCommand 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddressCommand(String value) {
        this.emailAddressCommand = value;
    }

    /**
     * numericSkipResolutionPrefix 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isNumericSkipResolutionPrefix() {
        if (numericSkipResolutionPrefix == null) {
            return false;
        } else {
            return numericSkipResolutionPrefix;
        }
    }

    /**
     * numericSkipResolutionPrefix 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNumericSkipResolutionPrefix(Boolean value) {
        this.numericSkipResolutionPrefix = value;
    }

}
