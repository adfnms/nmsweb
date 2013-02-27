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
 *         &lt;element name="vbnumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vbvalue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="textual-convention" type="{http://xmlns.opennms.org/xsd/eventconf}rfc1903-snmp-tc" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "vbnumber",
    "vbvalue"
})
@XmlRootElement(name = "varbind")
public class Varbind {

    protected int vbnumber;
    @XmlElement(required = true)
    protected List<String> vbvalue;
    @XmlAttribute(name = "textual-convention")
    protected String textualConvention;

    /**
     * vbnumber 속성의 값을 가져옵니다.
     * 
     */
    public int getVbnumber() {
        return vbnumber;
    }

    /**
     * vbnumber 속성의 값을 설정합니다.
     * 
     */
    public void setVbnumber(int value) {
        this.vbnumber = value;
    }

    /**
     * Gets the value of the vbvalue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vbvalue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVbvalue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getVbvalue() {
        if (vbvalue == null) {
            vbvalue = new ArrayList<String>();
        }
        return this.vbvalue;
    }

    /**
     * textualConvention 속성의 값을 가져옵니다.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextualConvention() {
        return textualConvention;
    }

    /**
     * textualConvention 속성의 값을 설정합니다.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextualConvention(String value) {
        this.textualConvention = value;
    }

}
