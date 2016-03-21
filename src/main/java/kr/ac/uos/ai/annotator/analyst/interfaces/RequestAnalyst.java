package kr.ac.uos.ai.annotator.analyst.interfaces;

import kr.ac.uos.ai.annotator.bean.protocol.Job;
import kr.ac.uos.ai.annotator.bean.protocol.Protocol;

import javax.jms.Message;

/**
 * @author Chan Yeon, Cho
 * @version 0.0.1 - SnapShot
 *          on 2016-03-21 enemy
 * @link http://ai.uos.ac.kr:9000/lovebube/UIMA_Management_Client
 */

public interface RequestAnalyst {

    void init();
    void analysis(Message msg);
    void getNodeInfo(Message msg);
    void connect(Message msg);
    void getJobList(Message msg);
    void requestJob(Message msg);
    void upLoad(Message msg);
    void sendJob(Message msg);
    void jobListCheck(Message msg);
    Job makeJob(Message msg);
    Protocol makeProtocol(Message msg);

}
