package jp.elfeuille.gradle.ex.core.service.impl;

import jp.elfeuille.gradle.ex.core.service.RootService;

import org.springframework.stereotype.Service;

@Service
public class RootServiceImpl implements RootService {

	@Override
	public String getValue() {
		return "service value";
	}
}
