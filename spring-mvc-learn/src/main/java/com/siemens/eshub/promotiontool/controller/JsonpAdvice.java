package com.siemens.eshub.promotiontool.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * wang-tao.wt@siemens.com
 * Created by wangtao on 2016/6/12.
 */
@ControllerAdvice(basePackages = "com.siemens.eshub.promotiontool")
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("callback", "jsonp");
    }
}
