package jp.elfeuille.gradle.ex.web.controller.api;

import jp.elfeuille.gradle.ex.core.service.RootService;
import jp.elfeuille.gradle.ex.web.json.RootJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
	@Autowired
	private RootService rootService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public RootJson test() {
		return RootJson.create(rootService.getValue());
	}

	@RequestMapping(value = "/null", method = RequestMethod.GET)
	public RootJson nulll() {
		return RootJson.create(null);
	}

	@RequestMapping(value = "/void", method = RequestMethod.GET)
	public void voidtest() {
	}
}
