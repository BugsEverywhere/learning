# HBase Learn - HBase Java 操作示例

## 项目简介

这是一个 HBase Java API 学习项目，包含了 HBase 的基本操作示例，包括：
- 连接 HBase 数据库
- 创建/删除表
- 数据的增删改查
- 各种扫描和过滤器操作

## 项目结构

```
hbase-learn/
├── src/main/java/indi/simon/learning/hbase/
│   ├── HBaseConnectionUtil.java    # HBase连接工具类
│   ├── HBaseTableOperator.java     # 表操作类（创建、删除表等）
│   ├── HBaseDataOperator.java      # 数据操作类（增删改查）
│   └── HBaseExample.java           # 示例程序（演示所有操作）
└── src/main/resources/
    └── log4j.properties            # 日志配置
```

## 使用前准备

### 1. 启动 HBase 环境

确保以下服务已启动：
- Hadoop（HDFS、YARN）
- ZooKeeper
- HBase（Master、RegionServer）

```bash
# 启动 Hadoop
start-dfs.sh
start-yarn.sh

# 启动 ZooKeeper
zkServer.sh start

# 启动 HBase
start-hbase.sh

# 验证服务状态
jps  # 应该看到 HMaster, HRegionServer, QuorumPeerMain 等进程
```

### 2. 修改连接配置

编辑 `HBaseConnectionUtil.java`，修改 ZooKeeper 连接配置：

```java
private static final String ZOOKEEPER_QUORUM = "localhost";  // 修改为你的ZK地址
private static final String ZOOKEEPER_PORT = "2181";         // 修改为你的ZK端口
```

如果是集群环境，可以设置多个 ZK 节点：
```java
private static final String ZOOKEEPER_QUORUM = "node1,node2,node3";
```

## 核心功能

### 1. 连接管理（HBaseConnectionUtil）

```java
// 获取连接（单例模式）
Connection connection = HBaseConnectionUtil.getConnection();

// 使用自定义配置
Connection conn = HBaseConnectionUtil.getConnection("zk-host", "2181");

// 关闭连接
HBaseConnectionUtil.closeConnection();
```

### 2. 表操作（HBaseTableOperator）

```java
// 创建表
String[] columnFamilies = {"info", "contact"};
HBaseTableOperator.createTable("test_user", columnFamilies);

// 列出所有表
List<String> tables = HBaseTableOperator.listTables();

// 检查表是否存在
boolean exists = HBaseTableOperator.tableExists("test_user");

// 删除表
HBaseTableOperator.deleteTable("test_user");
```

### 3. 数据写入（HBaseDataOperator）

```java
// 插入单条数据
HBaseDataOperator.putData("test_user", "user001", "info", "name", "张三");

// 插入一行多列
Map<String, String> columns = new HashMap<>();
columns.put("name", "李四");
columns.put("age", "30");
HBaseDataOperator.putRow("test_user", "user002", "info", columns);
```

### 4. 数据查询（HBaseDataOperator）

```java
// 根据 RowKey 查询
Map<String, String> data = HBaseDataOperator.getByRowKey("test_user", "user001");

// 查询指定列
String name = HBaseDataOperator.getColumnValue("test_user", "user001", "info", "name");

// 全表扫描
List<Map<String, String>> allData = HBaseDataOperator.scanTable("test_user");

// 范围扫描
List<Map<String, String>> rangeData = HBaseDataOperator.scanTable(
    "test_user", "user001", "user003"
);

// 前缀过滤
List<Map<String, String>> prefixData = HBaseDataOperator.scanWithPrefixFilter(
    "test_user", "user"
);

// 值过滤
List<Map<String, String>> filtered = HBaseDataOperator.scanWithValueFilter(
    "test_user", "info", "age", "30"
);
```

### 5. 数据删除（HBaseDataOperator）

```java
// 删除整行
HBaseDataOperator.deleteRow("test_user", "user001");

// 删除指定列
HBaseDataOperator.deleteColumn("test_user", "user001", "info", "age");

// 批量删除
List<String> rowKeys = Arrays.asList("user001", "user002");
HBaseDataOperator.batchDelete("test_user", rowKeys);
```

## 运行示例

### 1. 编译项目

```bash
cd /path/to/learning
mvn clean compile -pl hbase-learn
```

### 2. 运行示例程序

```bash
mvn exec:java -pl hbase-learn -Dexec.mainClass="indi.simon.learning.hbase.HBaseExample"
```

或者在 IDE 中直接运行 `HBaseExample` 类。

### 3. 使用 HBase Shell 验证

```bash
hbase shell

# 查看所有表
list

# 查看表数据
scan 'test_user'

# 查询单行
get 'test_user', 'user001'
```

## HBase 版本说明

本项目使用：
- HBase 2.4.17
- Hadoop 3.3.4
- Java 1.8+

如需使用其他版本，请修改 `pom.xml` 中的版本号。

## 常见问题

### 1. 连接超时

检查防火墙设置，确保以下端口可访问：
- ZooKeeper: 2181
- HBase Master: 16000
- HBase RegionServer: 16020

### 2. 找不到表

确保表名大小写正确，HBase 表名区分大小写。

### 3. 依赖冲突

如果遇到 Hadoop 依赖冲突，可以添加 exclusions：

```xml
<dependency>
    <groupId>org.apache.hbase</groupId>
    <artifactId>hbase-client</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-common</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

## 学习资源

- [HBase 官方文档](https://hbase.apache.org/book.html)
- [HBase Java API](https://hbase.apache.org/apidocs/index.html)
- [HBase 最佳实践](https://hbase.apache.org/book.html#schema)
