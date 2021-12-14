package com.course.auto.framework.Template.factory;

import cn.hutool.core.io.IoUtil;
import com.course.auto.framework.model.TemplateInfo;
import com.google.common.io.Resources;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplateFactory {

    private static final String TEMPLATES_ROOT_PATH = "templates";
    private final Map<String, TemplateInfo> templateMapping;

    public TemplateFactory() {
        this.templateMapping = initTemplateMapping();
    }

    private Map<String, TemplateInfo> initTemplateMapping() {
        //模版文件全部加载出来并封装进map
        String rootPathString = Resources.getResource(TEMPLATES_ROOT_PATH).getPath().substring(1);
        Path rootPath = Paths.get(rootPathString);

        try {
            return Files.walk(rootPath)
                    .filter(path -> Files.isRegularFile(path))
                    .map(path -> path.getFileName().toString())
                    .map(fileName -> {
                        String templateData = getTemplateData(fileName);
                        return new TemplateInfo(fileName, templateData);
                    }).collect(Collectors.toMap(temp -> temp.getTemplateKey(), temp -> temp));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private String getTemplateData(String fileName) {
        try {
            InputStream inputStream = Resources.getResource(TEMPLATES_ROOT_PATH + "/" + fileName).openStream();
            return IoUtil.read(inputStream, Charset.defaultCharset());

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //创建模板工厂实例
    private static class ClassHolder {
        private static final TemplateFactory INSTANCE = new TemplateFactory();

    }

    //模版类型实例
    public static TemplateFactory of() {
        return ClassHolder.INSTANCE;
    }

    public TemplateInfo getTemplateByKey(String templateKey) {
        if (this.templateMapping.containsKey(templateKey)) {
            return this.templateMapping.get(templateKey);
        }
        throw new IllegalArgumentException("template key not exists");
    }

}

