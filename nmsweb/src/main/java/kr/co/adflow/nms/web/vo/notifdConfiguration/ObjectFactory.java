//
// �� ������ JAXB(JavaTM Architecture for XML Binding) ���� ���� 2.2.6 ������ ���� �����Ǿ����ϴ�. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>�� �����Ͻʽÿ�. 
// �� ������ �����ϸ� �ҽ� ��Ű���� ���������� �� ���� ������ �սǵ˴ϴ�. 
// ���� ��¥: 2013.02.26 �ð� 05:55:51 PM KST 
//


package kr.co.adflow.nms.web.vo.notifdConfiguration;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the kr.co.adflow.nms.web.vo.notifdConfiguration package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: kr.co.adflow.nms.web.vo.notifdConfiguration
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Queue }
     * 
     */
    public Queue createQueue() {
        return new Queue();
    }

    /**
     * Create an instance of {@link HandlerClass }
     * 
     */
    public HandlerClass createHandlerClass() {
        return new HandlerClass();
    }

    /**
     * Create an instance of {@link InitParams }
     * 
     */
    public InitParams createInitParams() {
        return new InitParams();
    }

    /**
     * Create an instance of {@link AutoAcknowledge }
     * 
     */
    public AutoAcknowledge createAutoAcknowledge() {
        return new AutoAcknowledge();
    }

    /**
     * Create an instance of {@link NotifdConfiguration }
     * 
     */
    public NotifdConfiguration createNotifdConfiguration() {
        return new NotifdConfiguration();
    }

}
