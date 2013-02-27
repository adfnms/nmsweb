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
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}maskelement" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/eventconf}varbind" maxOccurs="unbounded" minOccurs="0"/>
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
    "maskelement",
    "varbind"
})
@XmlRootElement(name = "mask")
public class Mask {

    @XmlElement(required = true)
    protected List<Maskelement> maskelement;
    protected List<Varbind> varbind;

    /**
     * Gets the value of the maskelement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maskelement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaskelement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Maskelement }
     * 
     * 
     */
    public List<Maskelement> getMaskelement() {
        if (maskelement == null) {
            maskelement = new ArrayList<Maskelement>();
        }
        return this.maskelement;
    }

    /**
     * Gets the value of the varbind property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the varbind property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVarbind().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Varbind }
     * 
     * 
     */
    public List<Varbind> getVarbind() {
        if (varbind == null) {
            varbind = new ArrayList<Varbind>();
        }
        return this.varbind;
    }

}
