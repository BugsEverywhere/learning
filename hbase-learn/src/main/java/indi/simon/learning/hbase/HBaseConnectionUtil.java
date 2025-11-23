package indi.simon.learning.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * HBase连接工具类
 * 用于创建和管理HBase连接
 */
public class HBaseConnectionUtil {

    private static final Logger logger = LoggerFactory.getLogger(HBaseConnectionUtil.class);

    private static Connection connection = null;
    private static Configuration configuration = null;

    // 默认配置 - 请根据实际环境修改
    private static final String ZOOKEEPER_QUORUM = "ld-uf6x065twvhl0wf9v-proxy-lindorm-vpc.lindorm.aliyuncs.com";
    private static final String ZOOKEEPER_PORT = "30020";

    /**
     * 初始化HBase配置
     */
    static {
        configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", ZOOKEEPER_QUORUM);
        configuration.set("hbase.zookeeper.property.clientPort", ZOOKEEPER_PORT);
        // 可选配置
        configuration.set("hbase.client.retries.number", "3");
        configuration.set("hbase.rpc.timeout", "30000");
    }

    /**
     * 获取HBase连接（单例模式）
     */
    public static synchronized Connection getConnection() throws IOException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = ConnectionFactory.createConnection(configuration);
                logger.info("HBase连接创建成功");
            } catch (IOException e) {
                logger.error("HBase连接创建失败", e);
                throw e;
            }
        }
        return connection;
    }

    /**
     * 使用自定义配置获取连接
     */
    public static Connection getConnection(String zkQuorum, String zkPort) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", zkQuorum);
        conf.set("hbase.zookeeper.property.clientPort", zkPort);
        return ConnectionFactory.createConnection(conf);
    }

    /**
     * 关闭连接
     */
    public static void closeConnection() {
        if (connection != null && !connection.isClosed()) {
            try {
                connection.close();
                logger.info("HBase连接已关闭");
            } catch (IOException e) {
                logger.error("关闭HBase连接失败", e);
            }
        }
    }
}
