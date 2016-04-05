package kr.ac.uos.ai.annotator.activemq;

import kr.ac.uos.ai.annotator.activemq.interfaces.Sender;
import kr.ac.uos.ai.annotator.bean.protocol.Job;
import kr.ac.uos.ai.annotator.classloader.JobTracker;
import kr.ac.uos.ai.annotator.monitor.JobList;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.File;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-03-21 enemy
 * @link http://ai.uos.ac.kr:9000/lovebube/UIMA_Management_Client
 */

public class Sender_Impl implements Sender {

    private ActiveMQConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private Queue queue;
    private SenderSwitcher_Impl senderSwitcher;

    @Override
    public void createQueue(String queueName) {
        try {
            queue = session.createQueue(queueName);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        set();
    }

    @Override
    public void set() {
        try {
            producer = session.createProducer(queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
        factory = new ActiveMQConnectionFactory("tcp://" + "localhost" + ":61616");
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            senderSwitcher = new SenderSwitcher_Impl();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String msgType, String msgTxt, Job job, byte[] byteFromFile) {
        TextMessage txtMsg = null;
        BytesMessage byteMsg = null;

        switch (msgType) {
            case "getJobs" :

                break;

            case "callBack" :
                break;

            case "" :
                break;
            default:

                /*doNothing*/

                break;
        }
    }

    @Override
    public void logMessage(String type, String message) {

    }

    @Override
    public String switchMessage(String msgType, String msgTxt, Job job, byte[] byteFromFile) {
        return null;
    }

}