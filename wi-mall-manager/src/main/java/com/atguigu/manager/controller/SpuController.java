package com.atguigu.manager.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.atguigu.manager.bean.T_MALL_PRODUCT;
import com.atguigu.manager.service.SpuServiceImp;


@Controller
public class SpuController {

	@Autowired
	SpuServiceImp spuServiceImp;

	@RequestMapping(value = "spu_publish", method = RequestMethod.POST)
	public String spu_publish(T_MALL_PRODUCT spu, 
							  @RequestParam("files") MultipartFile[] files,
							  HttpServletRequest request) {

		List<String> image_list = new ArrayList<String>();

		// 图片组上传
		for (int i = 0; i < files.length; i++) {
			try {

				// UUID randomUUID = UUID.randomUUID();

				String originalFilename = System.currentTimeMillis() + files[i].getOriginalFilename();// 包含图片格式
//				files[i].transferTo(new File(
//						"D:\\Applications\\workSpaces\\workspace05\\maven_mall_1201_manager_teacher\\src\\main\\webapp\\upload\\"
//								+ originalFilename));// 图片的上传
				
				ServletContext servletContext = RequestContextUtils.getWebApplicationContext(request).getServletContext();
				String appPath = servletContext.getRealPath("/");
				
				files[i].transferTo(new File(appPath + "\\src\\main\\webapp\\upload\\" + originalFilename)); // 图片的上传
				
				image_list.add(originalFilename);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 调用商品spu信息发布业务
		spuServiceImp.spu_publish(spu, image_list);

		return "redirect:/manager_spu_publish.jsp";
	}

}
