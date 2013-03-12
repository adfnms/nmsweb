//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.6 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2013.03.11 �ð� 11:47:53 AM KST 
//


package kr.co.adflow.nms.web.vo.discoveryConfiguration;

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
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/discovery}specific" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/discovery}include-range" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/discovery}exclude-range" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://xmlns.opennms.org/xsd/config/discovery}include-url" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="threads" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="packets-per-second" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *             &lt;minInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="initial-sleep-time" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="restart-sleep-time" use="required" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="retries" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="timeout" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "specific",
    "includeRange",
    "excludeRange",
    "includeUrl"
})
@XmlRootElement(name = "discovery-configuration")
public class DiscoveryConfiguration {

    protected List<Specific> specific;
    @XmlElement(name = "include-range")
    protected List<IncludeRange> includeRange;
    @XmlElement(name = "exclude-range")
    protected List<ExcludeRange> excludeRange;
    @XmlElement(name = "include-url")
    protected List<IncludeUrl> includeUrl;
    @XmlAttribute(name = "threads", required = true)
    protected int threads;
    @XmlAttribute(name = "packets-per-second", required = true)
    protected int packetsPerSecond;
    @XmlAttribute(name = "initial-sleep-time", required = true)
    protected long initialSleepTime;
    @XmlAttribute(name = "restart-sleep-time", required = true)
    protected long restartSleepTime;
    @XmlAttribute(name = "retries")
    protected Integer retries;
    @XmlAttribute(name = "timeout")
    protected Long timeout;

    /**
     * the specific addresses to be polled Gets the value of the specific property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specific property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecific().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Specific }
     * 
     * 
     */
    public List<Specific> getSpecific() {
        if (specific == null) {
            specific = new ArrayList<Specific>();
        }
        return this.specific;
    }

    /**
     * the range of addresses to be polled Gets the value of the includeRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includeRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludeRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IncludeRange }
     * 
     * 
     */
    public List<IncludeRange> getIncludeRange() {
        if (includeRange == null) {
            includeRange = new ArrayList<IncludeRange>();
        }
        return this.includeRange;
    }

    /**
     * the range of addresses to be excluded from the
     *             polling Gets the value of the excludeRange property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the excludeRange property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExcludeRange().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExcludeRange }
     * 
     * 
     */
    public List<ExcludeRange> getExcludeRange() {
        if (excludeRange == null) {
            excludeRange = new ArrayList<ExcludeRange>();
        }
        return this.excludeRange;
    }

    /**
     * a file URL holding specific addresses to be
     *             polled Gets the value of the includeUrl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includeUrl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludeUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IncludeUrl }
     * 
     * 
     */
    public List<IncludeUrl> getIncludeUrl() {
        if (includeUrl == null) {
            includeUrl = new ArrayList<IncludeUrl>();
        }
        return this.includeUrl;
    }

    /**
     * threads �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getThreads() {
        return threads;
    }

    /**
     * threads �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setThreads(int value) {
        this.threads = value;
    }

    /**
     * packetsPerSecond �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public int getPacketsPerSecond() {
        return packetsPerSecond;
    }

    /**
     * packetsPerSecond �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setPacketsPerSecond(int value) {
        this.packetsPerSecond = value;
    }

    /**
     * initialSleepTime �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public long getInitialSleepTime() {
        return initialSleepTime;
    }

    /**
     * initialSleepTime �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setInitialSleepTime(long value) {
        this.initialSleepTime = value;
    }

    /**
     * restartSleepTime �Ӽ��� ���� �����ɴϴ�.
     * 
     */
    public long getRestartSleepTime() {
        return restartSleepTime;
    }

    /**
     * restartSleepTime �Ӽ��� ���� �����մϴ�.
     * 
     */
    public void setRestartSleepTime(long value) {
        this.restartSleepTime = value;
    }

    /**
     * retries �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRetries() {
        return retries;
    }

    /**
     * retries �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRetries(Integer value) {
        this.retries = value;
    }

    /**
     * timeout �Ӽ��� ���� �����ɴϴ�.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * timeout �Ӽ��� ���� �����մϴ�.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTimeout(Long value) {
        this.timeout = value;
    }

}
