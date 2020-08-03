## This project will help to setup and write Kafka Producer and Consumer in Java
### Step 1: Steup the Kafka Infrastructure.
    Infrastructure Ubuntu 18.4 LTS 
    prerequisite - Ensure Java is installed
    ```
    abdus@abdus-HP-EliteBook-8460p:~$ java -version
    openjdk version "11.0.8" 2020-07-14
    OpenJDK Runtime Environment (build 11.0.8+10-post-Ubuntu-0ubuntu118.04.1)
    OpenJDK 64-Bit Server VM (build 11.0.8+10-post-Ubuntu-0ubuntu118.04.1, mixed mode, sharing)
    abdus@abdus-HP-EliteBook-8460p:~$ 
  ```
  ```
  Download Kafka binary from https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz and unzip it uning
  tar -xvf kafka_2.13-2.5.0.tgz
  
    abdus@abdus-HP-EliteBook-8460p:~/kafka$ ls -lrt
    total 60028
    -rw-rw-r-- 1 abdus abdus 61459093 Jul 19 00:42 kafka_2.13-2.5.0.tgz
    drwxr-xr-x 8 abdus abdus     4096 Jul 19 00:59 kafka_2.13-2.5.0
    abdus@abdus-HP-EliteBook-8460p:~/kafka$ 

  
  ```
  UPDATE PATH in .bashrc , this is located in home hirectory and it is hidden. So to find it there in your home directory , execute the following command.You will 
  find the .bashrc along with other files.
  
  abdus@abdus-HP-EliteBook-8460p:~$ ls -la ~/ | more
-rw-r--r--  1 abdus abdus     3771 Feb  7  2019 .bashrc

 Update Kafka /home/abdus/kafka/kafka_2.13-2.5.0/bin in PATH .
 abdus@abdus-HP-EliteBook-8460p:~$  nano .bashrc 
  Append the below at the bottom and presess Ctrl + X and Enter

export PATH="/home/abdus/kafka/kafka_2.13-2.5.0/bin:$PATH"
```````
Now go to /home/abdus/kafka/kafka_2.13-2.5.0/ and create a data directory.
and inside data create two more directories 'kafka' and  'zookeeper'
``````````
now update conf/zookeeper.properties and update below 
#dataDir=/tmp/zookeeper
dataDir=/home/abdus/kafka/kafka_2.13-2.5.0/data/zookeeper

start zookeeper now 

abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ bin/zookeeper-server-start.sh config/zookeeper.properties 

If you find below error , then kill the process

**[2020-08-03 19:56:31,560] INFO binding to port 0.0.0.0/0.0.0.0:2181 (org.apache.zookeeper.server.NIOServerCnxnFactory)**
[2020-08-03 19:56:31,596] ERROR Unexpected exception, exiting abnormally (org.apache.zookeeper.server.ZooKeeperServerMain)
java.net.BindException: Address already in use
	at java.base/sun.nio.ch.Net.bind0(Native Method)
	at java.base/sun.nio.ch.Net.bind(Net.java:455)
	at java.base/sun.nio.ch.Net.bind(Net.java:447)
	at java.base/sun.nio.ch.ServerSocketChannelImpl.bind(ServerSocketChannelImpl.java:227)
	at java.base/sun.nio.ch.ServerSocketAdaptor.bind(ServerSocketAdaptor.java:80)
	at java.base/sun.nio.ch.ServerSocketAdaptor.bind(ServerSocketAdaptor.java:73)
	at org.apache.zookeeper.server.NIOServerCnxnFactory.configure(NIOServerCnxnFactory.java:687)
	at org.apache.zookeeper.server.ZooKeeperServerMain.runFromConfig(ZooKeeperServerMain.java:143)
	at org.apache.zookeeper.server.ZooKeeperServerMain.initializeAndRun(ZooKeeperServerMain.java:106)
	at org.apache.zookeeper.server.ZooKeeperServerMain.main(ZooKeeperServerMain.java:64)
	at org.apache.zookeeper.server.quorum.QuorumPeerMain.initializeAndRun(QuorumPeerMain.java:128)
	at org.apache.zookeeper.server.quorum.QuorumPeerMain.main(QuorumPeerMain.java:82)
`````````
Identify which process has occupied the port

abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ sudo netstat -plten |grep 2181
[sudo] password for abdus: 
tcp6       0      0 :::2181                 :::*                    LISTEN      122        34700      1310/java           
abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ 

Now kill the process.
abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ sudo kill -9 1310
abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ sudo netstat -plten |grep 2181
abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ 

Now start the zookeeper again.
abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ bin/zookeeper-server-start.sh config/zookeeper.properties
This time you will not find any error.

Now update conf/server.properties by below 
log.dirs=/home/abdus/kafka/kafka_2.13-2.5.0/data/kafka

Now start kafka using below command.
abdus@abdus-HP-EliteBook-8460p:~/kafka/kafka_2.13-2.5.0$ bin/kafka-server-start.sh config/server.properties

If will looks good you will see the 5 files got created in /kafka/kafka_2.13-2.5.0/data/kafka
cleaner-offset-checkpoint
meta.properties
recovery-point-offset-checkpoint
log-start-offset-checkpoint
replication-offset-checkpoint

***************You are done with the Setup ********************
```






  
