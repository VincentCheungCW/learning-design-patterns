package BloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.BitSet;

public class MyBloomFilter {

  /**
   * 位数组的大小
   */
  private static final int DEFAULT_SIZE = 2 << 24;
  /**
   * 通过这个数组可以创建 6 个不同的哈希函数
   */
  private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

  /**
   * 位数组。数组中的元素只能是 0 或者 1
   */
  private BitSet bits = new BitSet(DEFAULT_SIZE);

  /**
   * 存放包含 hash 函数的类的数组
   */
  private SimpleHash[] func = new SimpleHash[SEEDS.length];

  /**
   * 初始化多个包含 hash 函数的类的数组，每个类中的 hash 函数都不一样
   */
  public MyBloomFilter() {
    // 初始化多个不同的 Hash 函数
    for (int i = 0; i < SEEDS.length; i++) {
      func[i] = new SimpleHash(DEFAULT_SIZE, SEEDS[i]);
    }
  }

  /**
   * 添加元素到位数组
   */
  public void add(Object value) {
    for (SimpleHash f : func) {
      bits.set(f.hash(value), true);
    }
  }

  /**
   * 判断指定元素是否存在于位数组
   */
  public boolean contains(Object value) {
    boolean ret = true;
    for (SimpleHash f : func) {
      ret = ret && bits.get(f.hash(value));
    }
    return ret;
  }

  /**
   * 静态内部类。用于 hash 操作！
   */
  public static class SimpleHash {

    private int cap;
    private int seed;

    public SimpleHash(int cap, int seed) {
      this.cap = cap;
      this.seed = seed;
    }

    /**
     * 计算 hash 值
     */
    public int hash(Object value) {
      int h;
      return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
    }

  }

  public static void main(String[] args) {
    String value1 = "https://javaguide.cn/";
    String value2 = "https://github.com/Snailclimb";
    MyBloomFilter filter = new MyBloomFilter();
    System.out.println(filter.contains(value1));
    System.out.println(filter.contains(value2));
    filter.add(value1);
    filter.add(value2);
    System.out.println(filter.contains(value1));
    System.out.println(filter.contains(value2));

    Integer value3 = 13423;
    Integer value4 = 22131;
    System.out.println(filter.contains(value3));
    System.out.println(filter.contains(value4));
    filter.add(value3);
    filter.add(value4);
    System.out.println(filter.contains(value3));
    System.out.println(filter.contains(value4));


    //直接使用Guava提供的 bloom filter
    BloomFilter<Integer> fltr = BloomFilter.create(
        Funnels.integerFunnel(),
        1500,
        0.01);
    System.out.println(fltr.mightContain(1));
    System.out.println(fltr.mightContain(2));
    fltr.put(1);
    fltr.put(2);
    System.out.println(fltr.mightContain(1));
    System.out.println(fltr.mightContain(2));

    //Guava 提供的布隆过滤器只能单机使用，对于分布式的场景可以使用 Redis 中的布隆过滤器
    //Redis v4.0 之后有了 Module（模块/插件） 功能，Redis Modules 让 Redis 可以使用外部模块扩展其功能。
    //官网推荐了一个 RedisBloom 作为 Redis 布隆过滤器的 Module。

  }
}
