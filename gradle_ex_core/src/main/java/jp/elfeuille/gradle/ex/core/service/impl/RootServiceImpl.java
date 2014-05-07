package jp.elfeuille.gradle.ex.core.service.impl;

import org.springframework.stereotype.Service;

import com.sun.tracing.dtrace.NameAttributes;

import jp.elfeuille.gradle.ex.core.service.RootService;

@Service
public class RootServiceImpl implements RootService {

	@Override
	public String getValue() {
		return "service value";
	}
}
