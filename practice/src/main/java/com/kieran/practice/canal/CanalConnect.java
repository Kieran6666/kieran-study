package com.kieran.practice.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CanalConnect {

    @Bean(name = "practice_canal")
    public void connect() {
        CanalConnector example = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1",
                11111), "example", "", "");
        long batchId = -2L;
        try {
            example.connect();
            // 监控practice数据库的所有的表
            example.subscribe("practice.*");
            // 一次性取1000条SQL处理结果
            int batchSize = 1000;

            while (true) {
                System.err.println("canal运行");
                TimeUnit.SECONDS.sleep(1L);
                // 回到未同步的batchId   TODO:: 这一步没有理解
                example.rollback();
                // 非阻塞式，有多少拿多少
                Message messages = example.getWithoutAck(batchSize);
                // 需要确认的最大batchId
                batchId = messages.getId();
                System.err.println("batchId => " + batchId);

                List<CanalEntry.Entry> entries = messages.getEntries();
                for (CanalEntry.Entry entry : entries) {
                    // 判断当前数据对象是否是数据行
                    if (CanalEntry.EntryType.ROWDATA == entry.getEntryType()) {

                        // EventSink模块过滤后的序列化数据
                        ByteString storeValue = entry.getStoreValue();

                        // 反序列化
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);

                        // 获取改变类型
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        System.err.println("操作类型 => " + eventType);

                        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();

                        for (CanalEntry.RowData rowData : rowDatasList) {
                            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();

                            for (CanalEntry.Column before : beforeColumnsList) {
                                String name = before.getName();
                                String value = before.getValue();
                                System.err.println("改变之前，字段 => " + name + ", 值 => " + value);
                            }

                            for (CanalEntry.Column after : afterColumnsList) {
                                String name = after.getName();
                                String value = after.getValue();
                                System.err.println("改变之后，字段 => " + name + ", 值 => " + value);
                            }
                        }
                    }
                }

                // 必写，刷新ack cursor消费光标到最新位置
                example.ack(batchId);
            }
        } catch (InvalidProtocolBufferException | InterruptedException e) {
            if (batchId != -1L) {
                example.rollback(batchId); // TODO:: 这一步需要执行吗
            }
            e.printStackTrace();

            // TODO:: 通知canal宕了，或做一个failover，开集群
        } finally {
            example.disconnect();
        }
    }
}
