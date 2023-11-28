package com.yuanjunkang.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanjunkang.mapper.HospitalMapper;
import com.yuanjunkang.pojo.HosRequestParams;
import com.yuanjunkang.pojo.Hospital;
import com.yuanjunkang.pojo.HospitalDoc;
import com.yuanjunkang.pojo.Paging;
import com.yuanjunkang.service.HospitalService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Override
    public Paging<HospitalDoc> search(HosRequestParams params) {
        try {
            // 1.准备Request
            SearchRequest request = new SearchRequest("hospital");
            // 2.准备请求参数
            // 2.1.query
            buildBasicQuery(params, request);
            // 2.2.分页
            int page = params.getPage();
            int size = params.getSize();
            request.source().from((page - 1) * size).size(size);
            // 2.3.距离排序
            String location = params.getLocation();
            if (StringUtils.isNotBlank(location)) {
                request.source().sort(SortBuilders
                        .geoDistanceSort("location", new GeoPoint(location))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS)
                );
            }
            // 3.发送请求
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            // 4.解析响应
            return handleResponse(response);
        } catch (IOException e) {
            throw new RuntimeException("搜索数据失败", e);
        }
    }
    private void buildBasicQuery(HosRequestParams params, SearchRequest request) {
        // 1.准备Boolean查询
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 1.1.关键字搜索，match查询，放到must中
        String key = params.getKey();
        if (StringUtils.isNotBlank(key)) {
            // 不为空，根据关键字查询
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        } else {
            // 为空，查询所有
            boolQuery.must(QueryBuilders.matchAllQuery());
        }

        //1.2公立
        Boolean ispublic = params.getIspublic();
        String isp = ispublic?"1":"0";
        if (StringUtils.isNotBlank(isp)){
            boolQuery.filter(QueryBuilders.termQuery("ispublic",ispublic));
        }
        // 1.3.城市
        String city = params.getCity();
        if (StringUtils.isNotBlank(city)) {
            boolQuery.filter(QueryBuilders.termQuery("city", city));
        }
        // 1.4.等级
        String grade = params.getGrade();
        if (StringUtils.isNotBlank(grade)) {
            boolQuery.filter(QueryBuilders.termQuery("grade", grade));
        }

        // 2.算分函数查询
        FunctionScoreQueryBuilder functionScoreQuery = QueryBuilders.functionScoreQuery(
                boolQuery, // 原始查询，boolQuery
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{ // function数组
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                QueryBuilders.termQuery("isAD", true), // 过滤条件
                                ScoreFunctionBuilders.weightFactorFunction(10) // 算分函数
                        )
                }
        );
        // 3.设置查询条件
        request.source().query(functionScoreQuery);
    }
    private Paging<HospitalDoc> handleResponse(SearchResponse response) {
        SearchHits searchHits = response.getHits();
        // 4.1.总条数
        int total = (int)searchHits.getTotalHits().value;
        // 4.2.获取文档数组
        SearchHit[] hits = searchHits.getHits();
        // 4.3.遍历
        List<HospitalDoc> hospitals = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            // 4.4.获取source
            String json = hit.getSourceAsString();
            // 4.5.反序列化，非高亮的
            HospitalDoc hospitalDoc = JSON.parseObject(json, HospitalDoc.class);
            // 4.6.处理高亮结果
            // 1)获取高亮map
            Map<String, HighlightField> map = hit.getHighlightFields();
            if (map != null && !map.isEmpty()) {
                // 2）根据字段名，获取高亮结果
                HighlightField highlightField = map.get("hostipalname");
                if (highlightField != null) {
                    // 3）获取高亮结果字符串数组中的第1个元素
                    String hName = highlightField.getFragments()[0].toString();
                    // 4）把高亮结果放到HotelDoc中
                    hospitalDoc.setHospitalname(hName);
                }
            }
            // 4.8.排序信息
            Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                hospitalDoc.setDistance(sortValues[0]);
            }
            System.out.println(hospitalDoc);
            // 4.9.放入集合
            hospitals.add(hospitalDoc);
        }
        return new Paging(total, hospitals);
    }
}
