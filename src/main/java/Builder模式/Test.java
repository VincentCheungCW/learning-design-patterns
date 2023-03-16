package Builder模式;

/**
 * Created by Jiang on 2019-11-05.
 */
public class Test {
    public static void main(String[] args) {
        ParameterMapping parameterMapping = new ParameterMapping.Builder("test")
                .numericScale(2).jdbcTypeName("JDBC")
                .build();
    }
}
