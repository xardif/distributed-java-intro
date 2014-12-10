package pl.edu.amu.dji.jms.lab4;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import pl.edu.amu.dji.jms.lab4.service.ChangePriceService;
import pl.edu.amu.dji.jms.lab4.service.ProductListService;
import pl.edu.amu.dji.jms.lab4.service.message.ProductList;

import java.util.ArrayList;
import java.util.List;


@Component("warehouse")
public class Warehouse {

    @Autowired
    @Qualifier("productList")
    private ProductList productList;

    @Autowired
    @Qualifier("productListService")
    private ProductListService productListService;

    @Autowired
    @Qualifier("changePriceService")
    private ChangePriceService changePriceService;

    public void addItem(Item item){
        productList.getItemList().add(item);
        productListService.sendProductList(productList);
    }

    public void changePrice(Item item, double newPrice){
        changePriceService.changePrice(item, newPrice);
        for(Item i : productList.getItemList()){
            if(i.getName().equals(item.getName())){
                i.setPrice(newPrice);
                break;
            }
        }
    }

    public List<Item> getItemList() {
        return productList.getItemList();
    }

    public Item[] getItemArray() {
        return productList.getItemArray();
    }

    public ProductList getProductList() {
        return productList;
    }
}
