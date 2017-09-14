package com.atguigu.manager.service;

import java.util.List;

import com.atguigu.manager.bean.OBJECT_T_MALL_ATTR;

/**
 * description 
 * @author WisyaZZ
 * @buildTime 2017年9月13日下午10:03:23
 */
public interface AttrValueServiceInf {
	
	List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(Integer class_2_id);

	int attr_add(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id);

	
}
