package Builder模式;


/**
 * 建造器模式
 * 摘自Mybatis源码
 */
public class ParameterMapping {

  private String property;
  private Integer numericScale;
  private String jdbcTypeName;

  /**
   * 构造器私有化
   */
  private ParameterMapping() {
  }

  /**
   * 静态内部类Builder
   * 使用时需要 new Builder().().().
   */
  public static class Builder {
    private ParameterMapping parameterMapping = new ParameterMapping();

    public Builder(String property) {
      parameterMapping.property = property;
    }

    public Builder(String property, Integer numericScale) {
      parameterMapping.property = property;
      parameterMapping.numericScale = numericScale;
    }

    public Builder numericScale(Integer numericScale) {
      parameterMapping.numericScale = numericScale;
      return this;
    }

    public Builder jdbcTypeName(String jdbcTypeName) {
      parameterMapping.jdbcTypeName = jdbcTypeName;
      return this;
    }

    public ParameterMapping build() {
      return parameterMapping;
    }
  }


}
