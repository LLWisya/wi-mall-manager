package com.atguigu.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("goto_spu_publish")
	public String goto_spu_publish() {

		return "manager_spu_publish";

	}

	@RequestMapping("index")
	public String index() {
		return "manager_index";
	}

}
