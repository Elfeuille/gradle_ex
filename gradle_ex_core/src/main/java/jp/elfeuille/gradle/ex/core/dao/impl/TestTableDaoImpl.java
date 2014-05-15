package jp.elfeuille.gradle.ex.core.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import jp.elfeuille.gradle.ex.core.dao.TestTableDao;
import jp.elfeuille.gradle.ex.core.entity.TestTable;
import jp.elfeuille.gradle.ex.core.entity.TestTableExample;
import jp.elfeuille.gradle.ex.core.mapper.TestTableMapper;

@Named
public class TestTableDaoImpl implements TestTableDao {
	@Inject
	private TestTableMapper mapper;

	@Override
	public List<TestTable> list() {
		TestTableExample query = new TestTableExample();

		return mapper.selectByExample(query);
	}

	@Override
	public List<TestTable> list(int offset, int limit) {
		TestTableExample query = new TestTableExample();
		query.setLimit(limit);
		query.setOffset(offset);

		return mapper.selectByExample(query);
	}

	@Override
	public List<TestTable> limit(int limit) {
		TestTableExample query = new TestTableExample();
		query.setLimit(limit);

		return mapper.selectByExample(query);
	}

	@Override
	public List<TestTable> offset(int offset) {
		TestTableExample query = new TestTableExample();
		query.setOffset(offset);

		return mapper.selectByExample(query);
	}
}
