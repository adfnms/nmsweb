//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.6 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2013.03.11 시간 11:47:53 AM KST 
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
 * <p>anonymous complex type에 대한 Java 클래스입니다.
 * 
 * <p>다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
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
     * threads 속성의 값을 가져옵니다.
     * 
     */
    public int getThreads() {
        return threads;
    }

    /**
     * threads 속성의 값을 설정합니다.
     * 
     */
    public void setThreads(int value) {
        this.threads = value;
    }

    /**
     * packetsPerSecond 속성의 값을 가져옵니다.
     * 
     */
    public int getPacketsPerSecond() {
        return packetsPerSecond;
    }

    /**
     * packetsPerSecond 속성의 값을 설정합니다.
     * 
     */
    public void setPacketsPerSecond(int value) {
        this.packetsPerSecond = value;
    }

    /**
     * initialSleepTime 속성의 값을 가져옵니다.
     * 
     */
    public long getInitialSleepTime() {
        return initialSleepTime;
    }

    /**
     * initialSleepTime 속성의 값을 설정합니다.
     * 
     */
    public void setInitialSleepTime(long value) {
        this.initialSleepTime = value;
    }

    /**
     * restartSleepTime 속성의 값을 가져옵니다.
     * 
     */
    public long getRestartSleepTime() {
        return restartSleepTime;
    }

    /**
     * restartSleepTime 속성의 값을 설정합니다.
     * 
     */
    public void setRestartSleepTime(long value) {
        this.restartSleepTime = value;
    }

    /**
     * retries 속성의 값을 가져옵니다.
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
     * retries 속성의 값을 설정합니다.
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
     * timeout 속성의 값을 가져옵니다.
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
     * timeout 속성의 값을 설정합니다.
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
