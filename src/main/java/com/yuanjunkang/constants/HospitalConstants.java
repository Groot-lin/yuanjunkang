package com.yuanjunkang.constants;

public class HospitalConstants {
    public static final String MAPPING_TEMPLATE = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"hospitalname\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\":\"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"score\":{\n" +
            "        \"type\":\"integer\"\n" +
            "      },\n" +
            "      \"city\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"grade\":{\n" +
            "        \"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"image\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"ispublic\":{\n" +
            "        \"type\": \"boolean\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
