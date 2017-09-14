package com.atguigu.manager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.manager.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.manager.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.manager.bean.T_MALL_VALUE;
import com.atguigu.manager.service.AttrValueServiceInf;

/**
 * description
 * 
 * @author WisyaZZ
 * @buildTime 2017年9月13日下午10:02:04
 */
@Controller
public class AttrValueController {

	@Autowired
	AttrValueServiceInf attrValueService;

	// RESTFUL风格，没有参数也需要'/'
	@RequestMapping("goto_attr_publish")
	public String goto_attr_publish() {

		return "manager_attr_publish";
	}

	// Ajax+内嵌页 的方式，与Ajax+Json方式作为两大主要手法常用。
	// 如果在请求参数中有的数据，不需要封装到map中，如本例中的class_2_id,return后页面的请求仍为同一个所以可以访问，若请求参数中没有，
	// 则需要封装到model中，才可以被页面访问到。
	// @RequestMapping("get_attr_by_class_2_id/{class_2_id}")
	// public String get_attr_by_class_2_id(@PathVariable int class_2_id,
	// ModelMap map ) {
	// List<OBJECT_T_MALL_ATTR> list_object_attr_value =
	// attrValueService.get_attr_by_class_2_id(class_2_id);
	//
	// map.put("list_object_attr_value", list_object_attr_value);
	// return "manager_attr_publish_inner";
	// }

	// 改为使用post请求，在get请求中页面没有自动加载内嵌页
	@RequestMapping(value = "get_attr_by_class_2_id", method = RequestMethod.POST)
	public String get_attr_by_class_2_id(int class_2_id, ModelMap map) {
		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueService.get_attr_by_class_2_id(class_2_id);

		map.put("list_object_attr_value", list_object_attr_value);
		return "manager_attr_publish_inner";
	}

	@RequestMapping("goto_attr_add/{class_2_id}/{class_2_name}")
	public String goto_attr_add(@PathVariable int class_2_id, @PathVariable String class_2_name) {

		return "manager_attr_add";
	}

	@RequestMapping(value = "attr_add.do", method = RequestMethod.POST)
	public ModelAndView attr_add(MODEL_OBJECT_T_MALL_ATTR list_attr, int class_2_id, String class_2_name) {

		//收集到的集合中可能有null值，因为页面删除索引index时没有重新整理index，所以遍历整理出不为空的集合再给service层。
		
		// 获得页面提交的属性集合
		List<OBJECT_T_MALL_ATTR> list_attr2 = list_attr.getList_attr();

		// 循环页面提交的属性集合
		for (int i = 0; i < list_attr2.size(); i++) {

			// 每次循环，得到属性集合中的一个属性，创建一个新的属性值集合
			List<T_MALL_VALUE> list_value2 = new ArrayList<T_MALL_VALUE>();

			// 循环原有的属性值集合，判断该属性值集合中的每个属性
			List<T_MALL_VALUE> list_value = list_attr2.get(i).getList_value();

			// 如果被循环判断的属性不为空，则将其放入新的属性值集合中
			for (int j = 0; j < list_value.size(); j++) {
				if (list_value.get(j).getShxzh() != null) {
					list_value2.add(list_value.get(j));
				}
			}

			// 将新的属性值集合放入当前属性中，替换掉原来的属性值集合
			list_attr2.get(i).setList_value(list_value2);

		}

		// 调用业务层的插入方法
		attrValueService.attr_add(list_attr2, class_2_id);

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_attr_add/{class_2_id}/{class_2_name}.do");

		modelAndView.addObject("class_2_id", class_2_id);
		modelAndView.addObject("class_2_name", class_2_name);

		return modelAndView;
	}

}
