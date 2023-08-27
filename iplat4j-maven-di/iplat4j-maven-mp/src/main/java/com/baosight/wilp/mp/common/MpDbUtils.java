package com.baosight.wilp.mp.common;

import com.baosight.iplat4j.core.ioc.spring.PlatApplicationContext;
import com.baosight.iplat4j.core.util.NumberUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fangjian
 * @version V5.0.0
 * @Description: 数据库操作工具类
 * @ClassName: MpDbUtils
 * @Package com.baosight.wilp.mp.common
 * @date: 2022年11月04日 14:26
 *
 * 1.创建数据库连接
 * 2.关闭连接
 * 3.数据查询
 */
public class MpDbUtils {

    /**数据库连接地址**/
    public static final String url = PlatApplicationContext.getProperty("db_url");

    /**数据库用户名**/
    public static final String username = PlatApplicationContext.getProperty("db_username");

    /**数据库用户密码**/
    public static final String password = PlatApplicationContext.getProperty("db_password");

    /**数据库类型**/
    public static final String dbType = PlatApplicationContext.getProperty("db_type");

    /**数据库连接驱动**/
    private static String driver = null;
    static {
        switch (dbType) {
            case "sqlserver" :
                driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                break;
            case "oracle" :
                driver = "oracle.jdbc.driver.OracleDriver";
                break;
            default:
                driver = "com.mysql.jdbc.Driver";
        }
    }

    /**
     * 获取JDBC连接
     * @Title: getConnection
     * @return java.sql.Connection
     * @throws
     **/
    public static Connection getConnection(){
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            //把编写异常改为运行异常
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     * @Title: close
     * @param conn conn : 数据库连接对象
     * @param ps ps : 数据库操作对象
     * @param rs rs : 结果出炉对象
     * @return void
     * @throws
     **/
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps !=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs !=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 数据查询（利用反射+内省实现）
     * @Title: executeQuery
     * @param clazz clazz : 返回对象类型
     * @param sql sql : 查询sql
     * @param params params : 参数数组
     * @return java.util.List<T>
     * @throws
     **/
    public static <T> List<T> executeQuery(Class<T> clazz , String sql, Object...params){
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        List<T> list=new ArrayList<>();
        try {
            conn=getConnection();
            ps=conn.prepareStatement(sql);
            //设置参数
            if(params!=null){
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i+1, params[i]);
                }
            }
            //查询
            rs=ps.executeQuery();
            //获取结果集的标题
            ResultSetMetaData metaData = rs.getMetaData();
            //遍历结果集的标题字段
            boolean isMap = Map.class.equals(clazz) || Map.class.isAssignableFrom(clazz);
            if(isMap) {
                while (rs.next()){ buildMapRow(rs, list, metaData); }
            } else {
                while (rs.next()){ buildBeanRow(clazz, rs, list, metaData); }
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException("查询失败", e);
        }finally {
            close(conn, ps, rs);
        }
    }

    /**
     * 统计查询
     * @Title: count
     * @param sql sql : sql语句
     * @param params params : 参数
     * @return int
     * @throws
     **/
    public static int count(String sql, Object...params) {
        Connection conn=null; PreparedStatement ps=null; ResultSet rs=null;
        try {
            conn=getConnection();
            ps=conn.prepareStatement(sql);
            //设置参数
            if(params!=null){
                for (int i = 0; i < params.length-2; i++) {
                    ps.setObject(i+1, params[i]);
                }
            }
            //查询
            rs=ps.executeQuery();
            rs.next();
            return NumberUtils.toint(rs.getInt(1));
        }catch (Exception e){
            throw new RuntimeException("查询失败", e);
        }finally {
            close(conn, ps, rs);
        }
    }

    /**
     * 处理每行结果
     * @Title: buildRow
     * @param clazz clazz
     * @param rs rs
     * @param list list
     * @param metaData metaData
     * @return void
     * @throws
     **/
    private static <T> void buildBeanRow(Class<T> clazz, ResultSet rs, List<T> list, ResultSetMetaData metaData) throws Exception {
        //根据反射创建对象
        T obj= clazz.newInstance();
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            //获得每列的字段名称
            String columnLabel = metaData.getColumnLabel(i + 1);
            try {
                //利用内省完成属性赋值
                PropertyDescriptor pd = new PropertyDescriptor(columnLabel, clazz);
                if (pd != null) {
                    Method method = pd.getWriteMethod();
                    method.invoke(obj, rs.getObject(columnLabel));
                }
            } catch (Exception e) {
                //字段名出错就继续为下一个字段进行映射
                continue;
            }
        }
        list.add(obj);
    }

    /**
     * 构建Map数据行
     * @Title: buildMapRow
     * @param rs rs
     * @param list list
     * @param metaData metaData
     * @return void
     * @throws
     **/
    private static <T> void buildMapRow(ResultSet rs, List<T> list, ResultSetMetaData metaData) throws Exception {
        //根据反射创建对象
        Map<String, Object> map = new HashMap(metaData.getColumnCount());
        for (int i = 0; i < metaData.getColumnCount(); i++) {
            //获得每列的字段名称
            String columnLabel = metaData.getColumnLabel(i + 1);
            map.put(columnLabel, rs.getObject(columnLabel));
        }
        list.add((T) map);
    }
}
