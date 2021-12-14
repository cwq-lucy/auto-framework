package com.course.auto.framework.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class DataEntity {
    private Map<String, Entity> entityMap;
    private List<Entity> entities;

    public DataEntity() {
        this.entityMap = Maps.newHashMap();
        this.entities = Lists.newArrayList();
    }

    public void addEntity(Entity entity) {
        this.entityMap.put(entity.key, entity);
        this.entities.add(entity);
    }

    public Map<String, Entity> getEntityMap() {
        return entityMap;
    }

    public boolean isKeyExists(String key) {
        return this.entityMap.containsKey(key);
    }

    public Entity getEntity(String key) {
        return this.entityMap.get(key);
    }

    public static class Entity {
        boolean isJavaBean;
        String key;
        String val;

        public Entity(boolean isJavaBean, String key, String val) {
            this.isJavaBean = isJavaBean;
            this.key = key;
            this.val = val;
        }

        public static Entity of(boolean isJavaBean, String key, String val) {
            return new Entity(isJavaBean, key, val);
        }

        public boolean isJavaBean() {
            return isJavaBean;
        }

        public String getKey() {
            return key;
        }

        public String getVal() {
            return val;
        }

        @Override
        public String toString() {
            return "Entity{" +
                    "isJavaBean=" + isJavaBean +
                    ", key='" + key + '\'' +
                    ", val='" + val + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "entityMap=" + entityMap +
                ", entities=" + entities +
                '}';
    }
}
