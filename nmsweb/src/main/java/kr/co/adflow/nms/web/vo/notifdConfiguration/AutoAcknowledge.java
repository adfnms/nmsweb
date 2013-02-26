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
 *         &lt;element name="match" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="resolution-prefix" type="{http://www.w3.org/2001/XMLSchema}string" default="RESOLVED: " />
 *       &lt;attribute name="uei" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="acknowledge" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="notify" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "match"
})
@XmlRootElement(name = "auto-acknowledge")
public class AutoAcknowledge {

    @XmlElement(required = true)
    protected List<String> match;
    @XmlAttribute(name = "resolution-prefix")
    protected String resolutionPrefix;
    @XmlAttribute(name = "uei", required = true)
    protected String uei;
    @XmlAttribute(name = "acknowledge", required = true)
    protected String acknowledge;
    @XmlAttribute(name = "notify")
    protected Boolean notify;

    /**
     * Gets the value of the match property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the match property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMatch() {
        if (match == null) {
            match = new ArrayList<String>();
        }
        return this.match;
    }

    /**
     * resolutionPrefix �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolutionPrefix() {
        if (resolutionPrefix == null) {
            return "RESOLVED: ";
        } else {
            return resolutionPrefix;
        }
    }

    /**
     * resolutionPrefix �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolutionPrefix(String value) {
        this.resolutionPrefix = value;
    }

    /**
     * uei �Ӽ��� ���� �����ɴϴ�.
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
     * uei �Ӽ��� ���� �����մϴ�.
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
     * acknowledge �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcknowledge() {
        return acknowledge;
    }

    /**
     * acknowledge �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcknowledge(String value) {
        this.acknowledge = value;
    }

    /**
     * notify �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isNotify() {
        if (notify == null) {
            return true;
        } else {
            return notify;
        }
    }

    /**
     * notify �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNotify(Boolean value) {
        this.notify = value;
    }

}
