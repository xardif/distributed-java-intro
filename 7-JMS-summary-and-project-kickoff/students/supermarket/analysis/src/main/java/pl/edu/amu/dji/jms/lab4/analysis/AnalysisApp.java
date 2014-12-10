package pl.edu.amu.dji.jms.lab4.analysis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnalysisApp {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("/context.xml");
    }
}
