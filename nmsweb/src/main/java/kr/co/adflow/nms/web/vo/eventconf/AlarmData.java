//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.6 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2013.02.27 �ð� 01:50:32 PM KST 
//


package kr.co.adflow.nms.web.vo.eventconf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="reduction-key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="alarm-type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="clear-key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="auto-clean" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="x733-alarm-type" type="{http://xmlns.opennms.org/xsd/eventconf}x733-alarm-type" />
 *       &lt;attribute name="x733-probable-cause" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "alarm-data")
public class AlarmData {

    @XmlAttribute(name = "reduction-key", required = true)
    protected String reductionKey;
    @XmlAttribute(name = "alarm-type", required = true)
    protected int alarmType;
    @XmlAttribute(name = "clear-key")
    protected String clearKey;
    @XmlAttribute(name = "auto-clean")
    protected Boolean autoClean;
    @XmlAttribute(name = "x733-alarm-type")
    protected String x733AlarmType;
    @XmlAttribute(name = "x733-probable-cause")
    protected Integer x733ProbableCause;

    /**
     * reductionKey �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReductionKey() {
        return reductionKey;
    }

    /**
     * reductionKey �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReductionKey(String value) {
        this.reductionKey = value;
    }

    /**
     * alarmType �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getAlarmType() {
        return alarmType;
    }

    /**
     * alarmType �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setAlarmType(int value) {
        this.alarmType = value;
    }

    /**
     * clearKey �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClearKey() {
        return clearKey;
    }

    /**
     * clearKey �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClearKey(String value) {
        this.clearKey = value;
    }

    /**
     * autoClean �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAutoClean() {
        if (autoClean == null) {
            return false;
        } else {
            return autoClean;
        }
    }

    /**
     * autoClean �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoClean(Boolean value) {
        this.autoClean = value;
    }

    /**
     * x733AlarmType �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX733AlarmType() {
        return x733AlarmType;
    }

    /**
     * x733AlarmType �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX733AlarmType(String value) {
        this.x733AlarmType = value;
    }

    /**
     * x733ProbableCause �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getX733ProbableCause() {
        return x733ProbableCause;
    }

    /**
     * x733ProbableCause �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setX733ProbableCause(Integer value) {
        this.x733ProbableCause = value;
    }

}
