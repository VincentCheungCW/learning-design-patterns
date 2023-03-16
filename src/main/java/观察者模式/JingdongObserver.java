package 观察者模式;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者-电商平台
 * 实现Observer接口
 * Created by Jiang on 2019-10-29.
 */
public class JingdongObserver implements Observer {
    @Override
    public void update(Observable o, Object product) {
        //product由被观察者的notifyObservers()方法传入
        String newProduct = (String) product;
        System.out.println("新产品【" + newProduct + "】同步到京东商城");
    }
}
