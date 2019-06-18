package com.cnblogs.duma.streaming.starter;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

public class DataSource {
    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        String fileName = DataSource.class.getClassLoader().getResource("words.txt").getPath();
        System.out.println(fileName);

        DataSet<String> data =  env.readTextFile(fileName);
        data.print();
    }
}
