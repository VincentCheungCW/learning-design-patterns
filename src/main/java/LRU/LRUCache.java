package LRU;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * LRU(Least Recently Used)实现：链表+哈希表
 * 链表用于维护一个按最近访问时间排序的列表，表头是最近访问的项，表尾是最久未访问的项
 * HashMap主要是为了判断是否命中缓存以及存储访问项
 * 另一种方法：LinkedHashMap是链表和HashMap的结合，可以直接使用,其构造函数：
 * LinkedHashMap(int initialCapacity,float loadFactor, boolean accessOrder)
 * 的accessOrder设为true即为按访问顺序排序，其还有removeEldestEntry方法。
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {
    // 关键是维护两个数据结构：list维护操作项的顺序（最近的存到表头最老的存到表尾）
    // map维护实际的存储项
    private LinkedList<K> cacheList = new LinkedList<>();
    private HashMap<K, V> map = new HashMap<>();

    private final int cacheSize;

    public LRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public synchronized void put(K k, V v) {
        //map中已存在则只需要更新list中记录的顺序
        if (map.containsKey(k)) {
            cacheList.remove(k);
            cacheList.addFirst(k);
        } else {
            //不存在则增加，先考虑cache size
            if (map.size() >= cacheSize) {
                K k1 = cacheList.removeLast();
                map.remove(k1);
            }
            cacheList.addFirst(k);
            map.put(k, v);
        }
    }

    public synchronized V get(K k) {
        if (!map.containsKey(k)) {
            return null;
        }
        //最近操作项移到cacheList表头
        cacheList.remove(k);
        cacheList.addFirst(k);
        return map.get(k);
    }

    @Override
    public String toString() {
        return cacheList.toString();
    }
}
