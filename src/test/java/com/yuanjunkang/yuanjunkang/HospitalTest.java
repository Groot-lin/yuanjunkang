package com.yuanjunkang.yuanjunkang;

import com.alibaba.fastjson.JSON;
import com.yuanjunkang.pojo.Hospital;
import com.yuanjunkang.pojo.HospitalDoc;
import com.yuanjunkang.service.HospitalService;
import com.yuanjunkang.service.impl.HospitalServiceImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static com.yuanjunkang.constants.HospitalConstants.MAPPING_TEMPLATE;

@SpringBootTest
public class HospitalTest {
    private RestHighLevelClient client;
    @Autowired
    HospitalServiceImpl hospitalService;
    //创建索引库
    @Test
    void createHospitalIndex() throws IOException {
        //1.创建Request对象
        CreateIndexRequest request = new CreateIndexRequest("hospital");
        //2.准备请求参数
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        //3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }
    //删除索引库
    @Test
    void testDeleteIndex() throws IOException {
        // 1.准备Request
        DeleteIndexRequest request = new DeleteIndexRequest("hospital");
        // 3.发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }
    //批量导入数据
    @Test
    void testBulkRequest() throws IOException {
        // 查询所有的酒店数据
        List<Hospital> list = hospitalService.list();

        // 1.准备Request
        BulkRequest request = new BulkRequest();
        // 2.准备参数
        for (Hospital hospital : list) {
            // 2.1.转为HotelDoc
            HospitalDoc hospitalDoc = new HospitalDoc(hospital);
            // 2.2.转json
            String json = JSON.toJSONString(hospitalDoc);
            // 2.3.添加请求
            request.add(new IndexRequest("hospital").id(hospital.getId().toString()).source(json, XContentType.JSON));
        }
        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }
    @BeforeEach
    void setUp() {
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://49.235.84.94:9200")
        ));
    }
    @AfterEach
    void tearDown() throws IOException {
        client.close();
    }
}
