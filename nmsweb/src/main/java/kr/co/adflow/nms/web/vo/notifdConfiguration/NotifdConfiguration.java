//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.6 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2013.02.26 �ð� 05:55:51 PM KST 
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
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * status �Ӽ��� ���� �����ɴϴ�.
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
     * status �Ӽ��� ���� �����մϴ�.
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
     * pagesSent �Ӽ��� ���� �����ɴϴ�.
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
     * pagesSent �Ӽ��� ���� �����մϴ�.
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
     * nextNotifId �Ӽ��� ���� �����ɴϴ�.
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
     * nextNotifId �Ӽ��� ���� �����մϴ�.
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
     * nextUserNotifId �Ӽ��� ���� �����ɴϴ�.
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
     * nextUserNotifId �Ӽ��� ���� �����մϴ�.
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
     * nextGroupId �Ӽ��� ���� �����ɴϴ�.
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
     * nextGroupId �Ӽ��� ���� �����մϴ�.
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
     * serviceIdSql �Ӽ��� ���� �����ɴϴ�.
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
     * serviceIdSql �Ӽ��� ���� �����մϴ�.
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
     * outstandingNoticesSql �Ӽ��� ���� �����ɴϴ�.
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
     * outstandingNoticesSql �Ӽ��� ���� �����մϴ�.
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
     * acknowledgeIdSql �Ӽ��� ���� �����ɴϴ�.
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
     * acknowledgeIdSql �Ӽ��� ���� �����մϴ�.
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
     * acknowledgeUpdateSql �Ӽ��� ���� �����ɴϴ�.
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
     * acknowledgeUpdateSql �Ӽ��� ���� �����մϴ�.
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
     * matchAll �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public boolean isMatchAll() {
        return matchAll;
    }

    /**
     * matchAll �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setMatchAll(boolean value) {
        this.matchAll = value;
    }

    /**
     * emailAddressCommand �Ӽ��� ���� �����ɴϴ�.
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
     * emailAddressCommand �Ӽ��� ���� �����մϴ�.
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
     * numericSkipResolutionPrefix �Ӽ��� ���� �����ɴϴ�.
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
     * numericSkipResolutionPrefix �Ӽ��� ���� �����մϴ�.
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
