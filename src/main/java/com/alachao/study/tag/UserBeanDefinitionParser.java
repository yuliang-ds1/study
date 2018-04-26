package com.alachao.study.tag;

import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @Author yuliang-ds1
 * @Date 14:24  2018/4/26.
 * @Desciption
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(Element element) {
        //return super.getBeanClass(element);
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String id=element.getAttribute("id");
        System.out.println("开始获取id:"+id);
        String userName=element.getAttribute("userName");
        System.out.println("开始获取userName:"+userName);
        String email=element.getAttribute("email");
        System.out.println("开始获取email:"+email);

        /*if(StringUtils.hasText(id)){
            bean.addPropertyReference("id",id);
        }*/

        if(StringUtils.hasText(userName)){
            //bean.addPropertyReference("userName",userName);
            bean.addPropertyValue("userName",userName);
        }

        if(StringUtils.hasText(email)){
            bean.addPropertyValue("email",email);
        }
       // super.doParse(element, builder);
    }
}
