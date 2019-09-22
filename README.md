# springbootdubboseata
分布式解决方案：springboot+dubbo+seata
1、修改seata/conf下的文件registry.conf如下
registry {  
  type = "file"  

  file {  
    name = "file.conf"  
  }  
}  

config {  
  type = "file"  

  file {   
    name = "file.conf"  
  }  
}  
2、修改seata/conf下的文件file.conf如下  
transport {  
  type = "TCP"  
  #NIO NATIVE  
  server = "NIO"  
  #enable heartbeat
  heartbeat = true  
  #thread factory for netty  
  thread-factory {  
    boss-thread-prefix = "NettyBoss"  
    worker-thread-prefix = "NettyServerNIOWorker"  
    server-executor-thread-prefix = "NettyServerBizHandler"  
    share-boss-worker = false  
    client-selector-thread-prefix = "NettyClientSelector"  
    client-selector-thread-size = 1  
    client-worker-thread-prefix = "NettyClientWorkerThread"  
    # netty boss thread size,will not be used for UDT  
    boss-thread-size = 1   
    #auto default pin or 8  
    worker-thread-size = 8  
  }  
  shutdown {  
    # when destroy server, wait seconds  
    wait = 3  
  }  
  serialization = "seata"  
  compressor = "none"  
}  
service {
  #vgroup->rgroup
  vgroup_mapping.my_test_tx_group = "default"
  #only support single node
  default.grouplist = "192.168.1.5:8091" #此处为实际的ip地址
  #degrade current not support
  enableDegrade = false
  #disable
  disable = false
  #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
  max.commit.retry.timeout = "-1"
  max.rollback.retry.timeout = "-1"
}

client {
  async.commit.buffer.limit = 10000
  lock {
    retry.internal = 10
    retry.times = 30
  }
  report.retry.count = 5
}

store {
  mode = "file"

  file {
    dir = "sessionStore"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
    # async, sync
    flush-disk-mode = async
  }  

  db {  
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.  
    datasource = "dbcp"  
    ## mysql/oracle/h2/oceanbase etc.    
    db-type = "mysql"    
    driver-class-name = "com.mysql.jdbc.Driver"  
    url = "jdbc:mysql://127.0.0.1:3306/seata"  
    user = "root"  
    password = ""  
    min-conn = 1  
    max-conn = 3  
    global.table = "global_table"  
    branch.table = "branch_table"  
    lock-table = "lock_table"  
    query-limit = 100  
  }  
}  
lock {  
  mode = "remote"  

  local {  
    ## store locks in user's database  
  }  

  remote {  
    ## store locks in the seata's server  
  }  
}  
recovery {  
  #schedule committing retry period in milliseconds  
  committing-retry-period = 1000  
  #schedule asyn committing retry period in milliseconds  
  asyn-committing-retry-period = 1000  
  #schedule rollbacking retry period in milliseconds  
  rollbacking-retry-period = 1000  
  #schedule timeout retry period in milliseconds  
  timeout-retry-period = 1000  
}  

transaction {  
  undo.data.validation = true  
  undo.log.serialization = "jackson"  
  undo.log.save.days = 7  
  #schedule delete expired undo_log in milliseconds  
  undo.log.delete.period = 86400000  
  undo.log.table = "undo_log"  
}  

metrics {  
  enabled = false  
  registry-type = "compact"  
  exporter-list = "prometheus"  
  exporter-prometheus-port = 9898  
}  

3、启动seata：./seata-server.bat -h 192.168.1.5 -p 8091 -m file  
4、拷贝file.conf和register.conf到项目resources目录下  
5、启动zookeeper  
6、启动项目  
7、测试  
事务提交：http://localhost:8104/business/dubbo/buy
事务回滚：http://localhost:8104/business/dubbo/roll
使用postman测试，body为  
{  
    "userId":"1",  
    "commodityCode":"C201901140001",  
    "name":"fan",  
    "count":2,  
    "amount":"100"  
}
