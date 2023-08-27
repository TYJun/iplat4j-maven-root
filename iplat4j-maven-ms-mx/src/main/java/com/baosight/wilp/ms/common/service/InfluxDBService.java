package com.baosight.wilp.ms.common.service;

import com.baosight.wilp.ms.common.config.*;
import com.baosight.wilp.ms.common.domain.RtDTO;
import com.baosight.wilp.ms.common.domain.*;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * influxDB数据库服务层（时序数据库初始化操作、创建数据库、设置数据保存策略、执行命令、插入数据、批量插入数据、删除指定表的数据、查询所有设备的参数、分页查询指尺寸中的数据）
 *
 * @author: panlingfeng
 * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
 * @createDate: 2021/8/9 10:53 上午
 */
@Service
public class InfluxDBService {

    @Autowired
    private InfluxDBConfig influxDBConfig;

    private InfluxDB influxDB;

    /**
     * 时序数据库初始化操作
     *
     * @return 1.创建请求连接池
     * 2.连接数据库对象
     * 3.创建数据库
     * 4.创建保存策略
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/18 3:53 下午
     * @params
     */
    public void influxDBBuild() {
        if (influxDB == null) {
            OkHttpClient.Builder pool = new OkHttpClient.Builder()
                    .connectionPool(new ConnectionPool());
            influxDB = InfluxDBFactory.connect(influxDBConfig.getUrl(), influxDBConfig.getUsername(), influxDBConfig.getPassword(), pool);
            createDatabase(influxDBConfig.getDatabase());
            createRetentionPolicy(influxDBConfig.getDefaultPolicy());
        }
    }

    /**
     * 创建数据库
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 10:39 上午
     * @params dbName 数据库名
     * 1.查看数据库是否存在
     * 2.不存在则创建
     */
    public void createDatabase(String dbName) {
        boolean databaseExists = influxDB.databaseExists(dbName);
        if (!databaseExists)
            influxDB.createDatabase(influxDBConfig.getDatabase());
    }

    /**
     * 设置数据保存策略
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/9 3:55 下午
     * @params timeStrategy 持久化保留时间
     * 1.创建数据库sql
     * 2.执行sql
     */
    public void createRetentionPolicy(String timeStrategy) {
        String command = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s DEFAULT",
                "defalut", influxDBConfig.getDatabase(), StringUtils.isEmpty(timeStrategy) ? "0s" : timeStrategy, 1);
        query(command);
    }

    /**
     * 执行命令
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 10:13 上午
     * @params
     */
    public QueryResult query(String command) {
        influxDBBuild();
        return influxDB.query(new Query(command, influxDBConfig.getDatabase()));
    }

    /**
     * 插入数据
     *
     * @return
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 4:03 下午
     * @params tags 标签容器
     * @params fields 属性容器
     * @params measurement 测量
     * 1.初始化数据库
     * 2.构建数据
     * 3.写入到数据库中
     */
    public void insert(Map<String, String> tags, Map<String, Object> fields, String measurement) {
        influxDBBuild();
        Point.Builder builder = Point.measurement(measurement);
        builder.tag(tags);
        builder.fields(fields);
        influxDB.write(influxDBConfig.getDatabase(), influxDBConfig.getDefaultPolicy(), builder.build());
    }

    /**
     * 批量插入数据
     *
     * @return 遍历list获取数据
     * tag属性——只能存储String类型
     * 将单条数据存储到集合中
     * @author panlingfeng
     * @date 2021/8/10 4:22 下午
     * @params sensors 传感器采集到到数据容器
     * @params measurement 测量
     * 1.调用初始化数据库方法
     * 2.构建数据库批处理执行器
     * 3.构建批处理需要的数据集合
     * 4.写入批量数据到数据库
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     */
    public void insertBatchParams(List<SensorDTO> sensors, String measurement) {
        influxDBBuild();
        BatchPoints batchPoints = BatchPoints
                .database(influxDBConfig.getDatabase())
                .build();
        sensors.stream().map(sensorDTO -> Point.measurement(measurement)
                //.time(Long.parseLong(map.get("timeStamp")), TimeUnit.MILLISECONDS)
                .tag("tag_name", sensorDTO.getName())
                .addField("handle", sensorDTO.getDesc())
                .addField("iid", sensorDTO.getIid())
                .addField("desc", sensorDTO.getDesc())
                .addField("dom", sensorDTO.getDom())
                .addField("hsf", sensorDTO.getHsf())
                .build()).forEach(batchPoints::point);
        influxDB.write(batchPoints);
    }

    /**
     * @return 1.调用初始化数据库方法
     * 2.构建数据库批处理执行器
     * 3.构建批处理需要的数据集合
     * 4.写入批量数据到数据库
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 4:04 下午
     * @params rts 采集数据集合
     * @params measurement 测量
     */
    public void insertBatchDatas(List<RtDTO> rts, String measurement) {
        influxDBBuild();
        BatchPoints batchPoints = BatchPoints
                .database(influxDBConfig.getDatabase())
                .build();
        rts.stream().map(rtDTO -> Point.measurement(measurement)
                //.time(Long.parseLong(map.get("timeStamp")), TimeUnit.MILLISECONDS)
                .tag("tag_name", rtDTO.getTnm())
                .addField("bsn", rtDTO.getBsn())
                .addField("bnm", rtDTO.getBnm())
                .addField("tds", rtDTO.getTds())
                .addField("hid", rtDTO.getHid())
                .addField("iid", rtDTO.getIid())
                .addField("tss", rtDTO.getTss())
                .addField("tsm", rtDTO.getTsm())
                .addField("vt", rtDTO.getVt())
                .addField("val", rtDTO.getVal())
                .addField("vq", rtDTO.getVq())
                .build()).forEach(batchPoints::point);
        influxDB.write(batchPoints);
    }

    /**
     * @return 1.调用初始化数据库方法
     * 2.构建数据库批处理执行器
     * 3.构建批处理需要的数据集合
     * 4.写入批量数据到数据库
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 4:04 下午
     * @params rts 采集数据集合
     * @params measurement 测量
     */
    public void insertBatchDatasSimple(List<RtDTO> rts, String measurement) {
        influxDBBuild();
        BatchPoints batchPoints = BatchPoints
                .database(influxDBConfig.getDatabase())
                .build();
        rts.stream().map(rtDTO -> Point.measurement(measurement)
                .time((Long.parseLong(rtDTO.getTss()) * 1000L + Long.parseLong(rtDTO.getTsm())) * 1000000L, TimeUnit.NANOSECONDS)
                .tag("tag_name", rtDTO.getTnm())
                .addField("bsn", rtDTO.getBsn())
                .addField("bnm", rtDTO.getBnm())
                .addField("tss", rtDTO.getTss())
                .addField("tsm", rtDTO.getTsm())
                .addField("vt", rtDTO.getVt())
                .addField("val", rtDTO.getVal())
                .build()).forEach(batchPoints::point);
        influxDB.write(batchPoints);
    }

    /**
     * 删除指定表的数据
     *
     * @return QueryResult
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 5:15 下午
     * @params measurement
     */
    public void deleteMeasurement(String measurement) {
        String command = String.format("DROP MEASUREMENT %s", measurement);
        query(command);
    }

    /**
     * 查询所有设备的参数
     *
     * @return QueryResult
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/8/10 6:20 下午
     * @params measurement 测量
     */
    public QueryResult queryMeasurement(String measurement) {
        String command = String.format("SELECT * FROM %s", measurement);
        return query(command);
    }

    /**
     * 分页查询指尺寸中的数据
     *
     * @return QueryResult
     * @author wzy
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/9/3 10:43 上午
     * @params measurement 测量
     * @params arg 参数
     * @params limit 起点
     * @params offset 结束点
     */
    public QueryResult queryMeasurementX(String measurement, String arg, Integer limit, Integer offset) {
        String command = String.format("SELECT * FROM %s where tag_name =~/" + "%s" + "/ limit " + "%s" + " OFFSET" + " %s", measurement, arg, limit, offset);
        return query(command);
    }

    /**
     * 查询记录数
     *
     * @return QueryResult
     * @author wzy
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/9/10 10:47 上午
     * @params measurement 测量
     * @params arg 参数
     */
    public QueryResult queryCount(String measurement, String arg) {
        String command = String.format("SELECT count(*) FROM %s where tag_name =~/" + "%s" + "/", measurement, arg);
        return query(command);
    }

    /**
     * 查询24小时内的数据，并以10分钟平均值划分
     *
     * @return QueryResult
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/9/10 10:42 上午
     * @params measurement 测量
     */
    public QueryResult query24h(String tagName, String measurement) {
        String current = null, before = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        current = sdf.format(date); //当前时间字符串
        current = current.replace(" ", "T") + "Z";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        before = sdf.format(calendar.getTime()); //前一天时间字符串
        before = before.replace(" ", "T") + "Z";
        String command = String.format("SELECT mean(\"val\") FROM %s WHERE tag_name = '%s' AND time >= '%s' AND time <= '%s' GROUP BY time(60m) TZ('Asia/Shanghai')", measurement, tagName, before, current);
        return query(command);
    }

    /**
     * 动态查询实时数据
     * @author panlingfeng
     * @Copyright: 2021 www.bonawise.com Inc. All rights reserved.
     * @date 2021/9/14 5:13 下午
     * @params params 参数容器
     * @return
     */
    public QueryResult queryDynamic(HashMap<String, String> params) {
        String command = String.format("SELECT mean(\"val\") FROM %s WHERE tag_name = '%s' AND time >= '%s' AND time <= '%s' GROUP BY time(%s)", params.get("measurement"), params.get("tagName"), params.get("start"), params.get("end"), params.get("particle"));
        return query(command);
    }
}
