package jp.elfeuille.gradle.ex.core.dao;

import java.util.List;

import jp.elfeuille.gradle.ex.core.entity.TestTable;

public interface TestTableDao {

	List<TestTable> list();
	List<TestTable> list(int offset, int limit);
	List<TestTable> limit(int limit);
	List<TestTable> offset(int offset);
}
