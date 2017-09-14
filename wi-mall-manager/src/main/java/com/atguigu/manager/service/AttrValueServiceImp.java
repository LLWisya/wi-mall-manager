package com.atguigu.manager.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.manager.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.manager.bean.T_MALL_VALUE;
import com.atguigu.manager.mapper.AttrValueMapper;

/**
 * description 
 * @author WisyaZZ
 * @buildTime 2017年9月13日下午10:06:34
 */
@Service
public class AttrValueServiceImp implements AttrValueServiceInf{

	@Autowired
	AttrValueMapper attrValueMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(Integer class_2_id) {
		return attrValueMapper.select_attr_by_class_2_id(class_2_id);
	}

	@Override
	public int attr_add(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

		int num = 0;

		for (int i = 0; i < list_attr.size(); i++) {
			HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

			// 插入一条分类属性
			//注意，内联的模型中也有id时，建议先封装到外部模型中，再在sql中使用外部模型单参数，否则可能封装不上。
			list_attr.get(i).setFlbh2(class_2_id);
			attrValueMapper.insert_into_t_mall_attr(list_attr.get(i));

			List<T_MALL_VALUE> list_value = list_attr.get(i).getList_value();
			hashMap.put("list_value", list_value);
			hashMap.put("attr_id", list_attr.get(i).getId());
			// 批量插入分类属性值集合
			attrValueMapper.insert_into_t_mall_value(hashMap);

			num++;
		}
		return num;
	}
	
}
