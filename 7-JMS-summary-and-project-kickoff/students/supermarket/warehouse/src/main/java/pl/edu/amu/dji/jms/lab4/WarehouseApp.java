package pl.edu.amu.dji.jms.lab4;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import pl.edu.amu.dji.jms.lab4.service.ChangePriceService;
import pl.edu.amu.dji.jms.lab4.service.ProductListService;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WarehouseApp {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("/context.xml");
    }
}
