package com.cnblogs.duma.streaming.starter;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @author duma http://www.duma.cnblogs.com
 */
public class WindowWordCount {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Tuple2<String, Integer>> dataStream = env
                //创建 socket 数据源
                .socketTextStream("192.168.29.132", 9000)
                //扁平化(flat)操作，输入"a b c"，按照空格分割后，输出三个二元组 (a, 1) (b, 1) (c, 1)
                .flatMap(new Splitter())
                //按照元组的第一个属性分组
                .keyBy(0)
                //指定时间窗口 5s
                .timeWindow(Time.seconds(5))
                //对元组第二个属性求和
                .sum(1);

        dataStream.print();

        env.execute("Window WordCount");
    }

    public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        private static final String DELIMITER = " ";
        @Override
        public void flatMap(String sentence, Collector<Tuple2<String, Integer>> out) throws Exception {
            for (String word: sentence.split(DELIMITER)) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
