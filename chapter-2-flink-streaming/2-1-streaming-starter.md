# DStream 编程入门代码解析
[博客](https://www.cnblogs.com/duma/p/11033182.html)
### Dstream 的 WordCount
需要启动 socket 客户端，Windows 可以安装 netcat 命令实现，但个人觉得不好用。建议使用Linux 环境（可以装一个虚拟机，搞大数据有个虚拟机会比较方便），下面以 Linux 为例
```bash
nc -l 9000
```
修改`chapter-2-flink-streaming/src/main/java/com/cnblogs/duma/streaming/starter/WindowWordCount.java` 或 `chapter-2-flink-streaming/src/main/scala/com/cnblogs/duma/streaming/scala/starter/WindowWordCount.scala` 代码中连接 socket 的主机为你自己的 ip，以 Java 代码为例：
```java
socketTextStream("192.168.29.132", 9000) // 修改 192.168.29.132 为你自己机器的ip
```
运行程序，回到 socket 客户端输入数据如下：
```bash
[root@hadoop0 ~]# nc -l 9000
a b c
```
可以在 Flink 程序的控制台看到统计结果：
```bash
1> (b,1)
3> (a,1)
2> (c,1)
```


