package com.atguigu.manager.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.manager.bean.OBJECT_T_MALL_ATTR;

/**
 * description
 * 
 * @author WisyaZZ
 * @buildTime 2017年9月13日下午10:07:48
 */
public interface AttrValueMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(Integer class_2_id);

	int insert_into_t_mall_attr(OBJECT_T_MALL_ATTR objest_TMALL_ATTR);

	int insert_into_t_mall_value(HashMap<Object, Object> hashMap);

}
