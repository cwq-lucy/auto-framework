package com.course.auto.framework.Template;


import com.course.auto.framework.Template.service.TemplateService;
import com.course.auto.framework.model.TemplateInfo;

import java.util.Map;

public final class TemplateFacade {


    public TemplateFacade() {
    }

    public static TemplateInfo getTemplateByKey(String templateKey) {
        return new TemplateService().getTemplateByKey(templateKey);
    }

    //替换模板方法
    public static String replaceTemplate(String templateKey, Map<String, Object> mapping) {
        return new TemplateService().replaceTemplate(templateKey, mapping);
    }
}
