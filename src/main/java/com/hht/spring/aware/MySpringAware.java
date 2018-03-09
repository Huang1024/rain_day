package com.hht.spring.aware;

import com.hht.base.io.MyInputStreamReader;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by hht on 2017/11/29.
 */
@Service
public class MySpringAware implements BeanNameAware, ResourceLoaderAware {

    private String beanName;
    private ResourceLoader resourceLoader;

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void outputResult() {
        System.out.println("bean的名称是：" + beanName);
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        try {
            String content = MyInputStreamReader.byteArrayOutputStream(resource.getInputStream());
            System.out.println("加载的文件内容是：" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
