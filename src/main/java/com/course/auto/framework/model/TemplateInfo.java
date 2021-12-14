package com.course.auto.framework.model;

public class TemplateInfo {
    private String templateKey;
    private String templateValue;

    public TemplateInfo(String templateKey, String templateValue) {
        this.templateKey = templateKey;
        this.templateValue = templateValue;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public String getTemplateValue() {
        return templateValue;
    }

    @Override
    public String toString() {
        return "TemplateInfo{" +
                "templateKey='" + templateKey + '\'' +
                ", templateValue='" + templateValue + '\'' +
                '}';
    }
}
