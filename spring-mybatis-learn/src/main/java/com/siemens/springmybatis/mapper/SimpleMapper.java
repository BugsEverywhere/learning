package com.siemens.springmybatis.mapper;


import com.siemens.springmybatis.model.SimpleModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Simons on 2017/5/22.
 */
public interface SimpleMapper{

    @Select("SELECT id,tag,value,time FROM historical WHERE id = #{Id}")
    SimpleModel selectModelById(@Param("Id") String id);

}
