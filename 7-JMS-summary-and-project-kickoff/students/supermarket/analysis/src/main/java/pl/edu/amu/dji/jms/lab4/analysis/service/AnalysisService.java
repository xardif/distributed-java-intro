package pl.edu.amu.dji.jms.lab4.analysis.service;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.Item;
import pl.edu.amu.dji.jms.lab4.analysis.AnalysisUI;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@Service("analysisService")
public class AnalysisService{

    @Autowired
    @Qualifier("analysisUI")
    private AnalysisUI analysisUI;

    @Transactional
    public void analyse(Item soldItem){
        analysisUI.report(soldItem);
    }
}
