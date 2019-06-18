package com.cnblogs.duma.streaming.scala.starter

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object WindowWordCount {
  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val text = env.socketTextStream("192.168.29.132", 9000)

    val counts = text.flatMap { _.toLowerCase.split(" ") filter { _.nonEmpty } } //将输入数据按空格分割，过滤空元素
      .map { (_, 1) } //将word转成(word, 1)二元组
      .keyBy(0) //按照word分组
      .timeWindow(Time.seconds(5)) //5s的时间窗口
      .sum(1) //求和

    counts.print()

    env.execute("Window Stream WordCount")
  }
}
