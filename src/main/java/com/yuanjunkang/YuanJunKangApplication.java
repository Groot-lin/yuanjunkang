package com.yuanjunkang;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Slf4j
@SpringBootApplication
public class YuanJunKangApplication {
    public static void main(String[] args) {
        SpringApplication.run(YuanJunKangApplication.class, args);
        log.info("愿君康");
    }
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://49.235.84.94:9200")
        ));
    }


}
