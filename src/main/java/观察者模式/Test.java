package 观察者模式;

/**
 * 观察者模式
 * 又称为订阅分发模式，被观察者状态发生变化时会通知所有的观察者
 * 将新发布的产品推送到多个电商平台
 * Created by Jiang on 2019-10-29.
 */
public class Test {
    public static void main(String[] args) {
        ProductList productList = ProductList.getInstance();
        TaobaoObserver taobaoObserver = new TaobaoObserver();
        JingdongObserver jingdongObserver = new JingdongObserver();

        productList.addProductListObserver(taobaoObserver);
        productList.addProductListObserver(jingdongObserver);

        productList.addProduct("Kanye West最新专辑<Jesus is king>");
    }
}
