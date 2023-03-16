package 观察者模式;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * 被观察者-产品列表
 * 扩展Observable类
 * Created by Jiang on 2019-10-29.
 */
public class ProductList extends Observable {
    private List<String> productList = new ArrayList<>();
    private static ProductList instance = new ProductList();

    private ProductList() {
    }

    //单例
    public static ProductList getInstance() {
        return instance;
    }

    public void addProductListObserver(Observer observer) {
        this.addObserver(observer);
    }

    public void addProduct(String newProduct) {
        productList.add(newProduct);
        System.out.println("产品列表中新增了产品：" + newProduct);
        this.setChanged();
        this.notifyObservers(newProduct);
    }
}
