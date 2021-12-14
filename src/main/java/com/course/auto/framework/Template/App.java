package com.course.auto.framework.Template;

import com.course.auto.framework.Template.factory.TemplateFactory;
import com.course.auto.framework.model.TemplateInfo;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class App {
    @Test
    public void testTemp() {
        TemplateInfo templateInfo1 = TemplateFactory.of().getTemplateByKey("default_alarm_template");
        System.out.println("templateInfo1 = " + templateInfo1);

        TemplateInfo templateInfo2 = TemplateFactory.of().getTemplateByKey("default_report_template");
        System.out.println("templateInfo2 = " + templateInfo2);


        TemplateInfo templateInfo3 = TemplateFactory.of().getTemplateByKey("xxxxxxxx");
        System.out.println("templateInfo3 = " + templateInfo3);
    }

    @Test
    public void testReplace() {
        Map<String, Object> mapping = Maps.newHashMap();
        mapping.put("case_title", "测试标题");
        mapping.put("case_id", "1234");
        mapping.put("case_owner", "qa");


        String text = TemplateFacade.replaceTemplate("default_alarm_template", mapping);
        //System.out.println(text);
    }

}
