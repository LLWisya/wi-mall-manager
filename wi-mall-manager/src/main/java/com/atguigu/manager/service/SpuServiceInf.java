package com.atguigu.manager.service;

import java.util.List;

import com.atguigu.manager.bean.T_MALL_PRODUCT;

public interface SpuServiceInf {

	public int spu_publish(T_MALL_PRODUCT spu, List<String> image_list);

}
