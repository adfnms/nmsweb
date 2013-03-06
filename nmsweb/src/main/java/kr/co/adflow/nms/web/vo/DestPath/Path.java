//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.6 ������ ���� ��Ǿ���ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// �� ��¥: 2013.02.25 �ð� 06:29:50 PM KST 
//


package kr.co.adflow.nms.web.vo.DestPath;

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
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/destinationPaths}target" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/destinationPaths}escalate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="initial-delay" type="{http://www.w3.org/2001/XMLSchema}string" default="0s" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "target",
    "escalate"
})
@XmlRootElement(name = "path")
public class Path {

    @XmlElement(required = true)
    protected List<Target> target;
    protected List<Escalate> escalate;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "initial-delay")
    protected String initialDelay;

    /**
     * Gets the value of the target property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the target property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Target }
     * 
     * 
     */
    public List<Target> getTarget() {
        if (target == null) {
            target = new ArrayList<Target>();
        }
        return this.target;
    }

    /**
     * Gets the value of the escalate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the escalate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEscalate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Escalate }
     * 
     * 
     */
    public List<Escalate> getEscalate() {
        if (escalate == null) {
            escalate = new ArrayList<Escalate>();
        }
        return this.escalate;
    }

    /**
     * name �Ӽ��� ���� �����ɴϴ�.
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
     * name �Ӽ��� ���� �����մϴ�.
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
     * initialDelay �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialDelay() {
        if (initialDelay == null) {
            return "0s";
        } else {
            return initialDelay;
        }
    }

    /**
     * initialDelay �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialDelay(String value) {
        this.initialDelay = value;
    }

}
