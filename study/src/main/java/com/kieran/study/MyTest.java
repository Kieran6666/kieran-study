package com.kieran.study;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.core.HashCodeCustomizer;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import static com.kieran.study.juc.executor.ThreadPoolConfig.threadPoolExecutor;


public class MyTest {


    public static void main(String[] args) throws InterruptedException, ParseException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.err.println(now);

        String s = "20240205112900";

        LocalDateTime parse = LocalDateTime.parse(s, dtf);

        long seconds = Duration.between(parse, now).getSeconds();
        System.err.println(seconds);
    }






}
