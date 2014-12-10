package pl.edu.amu.dji.jms.lab4;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.service.InitPointOfSalesService;
import pl.edu.amu.dji.jms.lab4.service.SellProductService;
import pl.edu.amu.dji.jms.lab4.service.message.ChangePrice;
import pl.edu.amu.dji.jms.lab4.service.message.InitPointOfSales;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PointOfSales {

    private List<Item> productList;
    private static int counter = 0;
    private int id;

    @Autowired
    @Qualifier("sellProductService")
    private SellProductService sellProductService;

    @Autowired
    @Qualifier("initPointOfSalesService")
    private InitPointOfSalesService initPointOfSalesService;

    public PointOfSales() {
        this.id = counter++;
        productList = new ArrayList<Item>();
    }

    public void init(){
        initPointOfSalesService.sendAddPointOfSale();
    }

    public void sellItem(Item item){
        sellProductService.sellItem(item);
    }

    public void changePrice(ChangePrice changePrice){
        for(Item item : productList){
            if(changePrice.getItem().getName().equals(item.getName())){
                item.setPrice(changePrice.getNewPrice());
                break;
            }
        }
    }

    public void updateProductList(ProductList productList){
        this.productList = productList.getItemList();
    }

    public List<Item> getProductList(){
        return productList;
    }

    public Item[] getProductArray() {
        return productList.toArray(new Item[productList.size()]);
    }

    public int getId() {
        return id;
    }
}
