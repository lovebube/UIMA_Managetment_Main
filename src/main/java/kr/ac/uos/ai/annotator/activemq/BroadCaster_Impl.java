package kr.ac.uos.ai.annotator.activemq;

import kr.ac.uos.ai.annotator.activemq.interfaces.BroadCaster;
import kr.ac.uos.ai.annotator.bean.protocol.Protocol;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-03-17 enemy
 * @link http://ai.uos.ac.kr:9000/lovebube/UIMA_Management_Client
 */

public class BroadCaster_Impl implements BroadCaster {

    private String consumerID;
    private String topicName;
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Topic topic;
    private MessageProducer producer;

    public BroadCaster_Impl(String topicName) {
        this.topicName = topicName;
    }

    public void init() {
        try {
            connectionFactory = new ActiveMQConnectionFactory("tcp://" + "211.109.9.71" + ":61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic(topicName);
            producer = session.createProducer(topic);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(byte[] msg, String fileName, String type, String specificReceiver) {
        try {
            BytesMessage message = session.createBytesMessage();
            message.writeBytes(msg);
            message.setObjectProperty("connectCallBack", specificReceiver);
            message.setObjectProperty("msgType", "upload");
            message.setObjectProperty("type", "jar");
            message.setObjectProperty("updateType", type);
            message.setObjectProperty("fileName", fileName);
            producer.send(message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        TextMessage txtMsg;
        try {
            txtMsg = session.createTextMessage();
            txtMsg.setText(message);
            txtMsg.setObjectProperty("msgType", "TEST");
            producer.send(txtMsg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void sendGetNodeMessage(){
        TextMessage txtMsg;
        try {
            txtMsg = session.createTextMessage();
            txtMsg.setObjectProperty("msgType", "getNodeInfo");
            producer.send(txtMsg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(String consumerID) {
        this.consumerID = consumerID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void sendMessage(String runAnnotator, String annoFileName) {
        TextMessage txtMsg;
        try {
            txtMsg = session.createTextMessage();
            txtMsg.setObjectProperty("msgType", runAnnotator);
            txtMsg.setObjectProperty("fileName", annoFileName);
            producer.send(txtMsg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
