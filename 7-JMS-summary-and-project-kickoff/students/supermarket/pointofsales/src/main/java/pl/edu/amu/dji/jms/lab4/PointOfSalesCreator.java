package pl.edu.amu.dji.jms.lab4;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;


public class PointOfSalesCreator {


    private PointOfSales pointOfSales;
    private PointOfSalesPane pointOfSalesPane;

    public PointOfSalesCreator() {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        pointOfSalesPane = (PointOfSalesPane) context.getBean("pointOfSalesPane");
        pointOfSales = (PointOfSales) context.getBean("pointOfSales");
        pointOfSales.init();
    }

    public PointOfSales getPointOfSales() {
        return pointOfSales;
    }

    public PointOfSalesPane getPointOfSalesPane() {
        return pointOfSalesPane;
    }

}
