package com.course.auto.framework.util;

import com.course.auto.framework.model.DataEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class YmlUtils {

    private static final ObjectMapper MAPPER;

    private YmlUtils() {

    }

    static {
        //创建yamlfactoty工厂
        YAMLFactory factory = new YAMLFactory();
        YAMLMapper mapper = new YAMLMapper(factory);
        MAPPER = mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    public static Map<String, String> readForMap(String path) {
        RequiredUtils.requireNotNullOrEmpty(path, "yaml file path should not be null or empty.");
        Map<String, String> map = Maps.newHashMap();
        try {
            InputStream ins = Resources.getResource(path).openStream();
            JsonNode rootNode = MAPPER.readTree(ins);
            rootNode.fields().forEachRemaining(e -> map.put(e.getKey(), e.getValue().asText()));
            return map;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static List<DataEntity> read(String path) {
        //对path进行去空处理
        RequiredUtils.requireNotNullOrEmpty(path, "yaml file path should not be null or empty.");
        List<DataEntity> entities = Lists.newArrayList();
        try {
            InputStream ins = Resources.getResource(path).openStream();
            JsonNode rootNode = MAPPER.readTree(ins);
            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    entities.add(transToDataEntity(node));
                }
            }

            if (rootNode.isObject()) {
                entities.add(transToDataEntity(rootNode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entities;
    }

    private static DataEntity transToDataEntity(JsonNode node) {
        DataEntity dataEntity = new DataEntity();
        node.fields().forEachRemaining(e -> {
            DataEntity.Entity entity = transToEntity(e);
            dataEntity.addEntity(entity);
        });
        return dataEntity;
    }

    private static DataEntity.Entity transToEntity(Map.Entry<String, JsonNode> e) {
        JsonNode valueNode = e.getValue();
        return DataEntity.Entity.of(valueNode.isObject(), e.getKey(), valueNode.toString());
    }

    public static void main(String[] args) {
        List<DataEntity> entities = read("data/demo2.yml");
        for (DataEntity entity : entities) {
            System.out.println("entity = " + entity);
        }
    }
}
