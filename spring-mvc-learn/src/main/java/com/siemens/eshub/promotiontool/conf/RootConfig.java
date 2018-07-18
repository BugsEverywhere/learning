package com.siemens.eshub.promotiontool.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 此类为Spring中除Controller和Mapper之外所有Bean扫描的配置类
 * Created by Chen Zhuo on 2017/6/19.
 */
@Configuration
@ComponentScan( basePackages={"com.siemens.eshub.promotiontool"},
        excludeFilters = { @ComponentScan.Filter(type= FilterType.ANNOTATION,value=EnableWebMvc.class)}
)
public class RootConfig {


}
