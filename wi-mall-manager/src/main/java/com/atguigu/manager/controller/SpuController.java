package com.atguigu.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.manager.bean.T_MALL_PRODUCT;
import com.atguigu.manager.service.SpuServiceImp;
import com.atguigu.manager.util.MyFileUploadUtil;


@Controller
public class SpuController {

	@Autowired
	SpuServiceImp spuServiceImp;

	@RequestMapping(value = "spu_publish", method = RequestMethod.POST)
	public ModelAndView spu_publish(T_MALL_PRODUCT spu, @RequestParam("files") MultipartFile[] files) {

		// 图片组上传
		List<String> image_list = MyFileUploadUtil.imgs_upload(files);

		// 调用商品spu信息发布业务
		spuServiceImp.spu_publish(spu, image_list);

		// URLDecoder
		// URLEncoder

		String success = "恭喜操作成功";

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_spu_publish/{success}.do");

		modelAndView.addObject("success", success);

		return modelAndView;
	}

	@RequestMapping("goto_spu_publish/{success}")
	public String goto_spu_publish(@PathVariable String success) {

		return "manager_spu_publish";

	}

}
