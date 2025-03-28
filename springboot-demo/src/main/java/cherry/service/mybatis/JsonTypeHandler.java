package cherry.service.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lee
 * @since 2022/3/20
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonTypeHandler<T> extends BaseTypeHandler<T>{
    //非空参数转换为数据库的存储值
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T o, JdbcType jdbcType) throws SQLException {

    }

    //
    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return null;
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
