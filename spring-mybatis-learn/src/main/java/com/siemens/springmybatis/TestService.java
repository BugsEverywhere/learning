package com.siemens.springmybatis;

import com.siemens.springmybatis.mapper.SimpleMapper;
import com.siemens.springmybatis.model.SimpleModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Simons on 2017/5/22.
 */
public class TestService {

    @Autowired
    private SimpleMapper historicalMapper;

    public SimpleModel selectFirstModel(String id){

        SimpleModel historical =  historicalMapper.selectModelById(id);

        return historical;

    }



}
