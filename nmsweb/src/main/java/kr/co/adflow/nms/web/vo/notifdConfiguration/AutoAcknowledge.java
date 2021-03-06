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
     * resolutionPrefix 속성의 값을 가져옵니다.
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
     * resolutionPrefix 속성의 값을 설정합니다.
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
     * acknowledge 속성의 값을 가져옵니다.
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
     * acknowledge 속성의 값을 설정합니다.
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
     * notify 속성의 값을 가져옵니다.
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
     * notify 속성의 값을 설정합니다.
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
