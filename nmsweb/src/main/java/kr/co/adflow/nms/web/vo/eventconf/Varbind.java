//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.6 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2013.02.27 �ð� 01:50:32 PM KST 
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
 * <p>anonymous complex type�� ���� Java Ŭ�����Դϴ�.
 * 
 * <p>���� ��Ű�� ������ �� Ŭ������ ���ԵǴ� �ʿ��� �������� �����մϴ�.
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
     * vbnumber �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getVbnumber() {
        return vbnumber;
    }

    /**
     * vbnumber �Ӽ��� ���� �����մϴ�.
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
     * textualConvention �Ӽ��� ���� �����ɴϴ�.
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
     * textualConvention �Ӽ��� ���� �����մϴ�.
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
