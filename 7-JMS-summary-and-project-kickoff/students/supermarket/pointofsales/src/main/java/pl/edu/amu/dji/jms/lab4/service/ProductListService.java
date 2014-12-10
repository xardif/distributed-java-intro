package pl.edu.amu.dji.jms.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.dji.jms.lab4.PointOfSales;
import pl.edu.amu.dji.jms.lab4.PointOfSalesPane;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;


@Service("productListService")
public class ProductListService {

    @Autowired
    @Qualifier("pointOfSales")
    private PointOfSales pointOfSales;

    @Autowired
    @Qualifier("pointOfSalesPane")
    private PointOfSalesPane pointOfSalesPane;

    @Transactional
    public void update(ProductList productList) {
        pointOfSales.updateProductList(productList);
        pointOfSalesPane.log("Received new product list!");
        pointOfSalesPane.updateProductList();
    }
}