package jp.elfeuille.gradle.ex.core.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import jp.elfeuille.gradle.ex.core.config.DefaultConfiguration;
import jp.elfeuille.gradle.ex.core.dao.TestTableDao;
import jp.elfeuille.gradle.ex.core.entity.TestTable;
import jp.elfeuille.gradle.ex.core.mapper.TestTableMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration
@Transactional
@ContextConfiguration(classes = {DefaultConfiguration.class})
public class TestTableDaoImplTest {
	@Inject
	private TestTableDao target;
	@Inject
	private TestTableMapper mapper;

	@Before
	public void before() {
		mapper.insert(this.createEntity(1,  "master db value 1"));
		mapper.insert(this.createEntity(2,  "master db value 2"));
		mapper.insert(this.createEntity(3,  "master db value 3"));
		mapper.insert(this.createEntity(4,  "master db value 4"));
		mapper.insert(this.createEntity(5,  "master db value 5"));
		mapper.insert(this.createEntity(6,  "master db value 6"));
		mapper.insert(this.createEntity(7,  "master db value 7"));
		mapper.insert(this.createEntity(8,  "master db value 8"));
		mapper.insert(this.createEntity(9,  "master db value 9"));
		mapper.insert(this.createEntity(10, "master db value 10"));
	}

	private TestTable createEntity(int id, String value) {
		TestTable entity = new TestTable();
		entity.setId(id);
		entity.setValue(value);
		return entity;
	}

	@Test
	public void list() {
		List<TestTable> actuals = target.list();
		assertThat(actuals.size(), is(10));
		assertThat(actuals.get(0).getId(), is(1));
		assertThat(actuals.get(1).getId(), is(2));
		assertThat(actuals.get(2).getId(), is(3));
		assertThat(actuals.get(3).getId(), is(4));
		assertThat(actuals.get(4).getId(), is(5));
		assertThat(actuals.get(5).getId(), is(6));
		assertThat(actuals.get(6).getId(), is(7));
		assertThat(actuals.get(7).getId(), is(8));
		assertThat(actuals.get(8).getId(), is(9));
		assertThat(actuals.get(9).getId(), is(10));
	}
	@Test
	public void listOL() {
		List<TestTable> actuals = target.list(2,5);
		assertThat(actuals.size(), is(5));
		assertThat(actuals.get(0).getId(), is(3));
		assertThat(actuals.get(1).getId(), is(4));
		assertThat(actuals.get(2).getId(), is(5));
		assertThat(actuals.get(3).getId(), is(6));
		assertThat(actuals.get(4).getId(), is(7));
	}
	@Test
	public void testL() {
		List<TestTable> actuals = target.limit(5);
		assertThat(actuals.size(), is(5));
		assertThat(actuals.get(0).getId(), is(1));
		assertThat(actuals.get(1).getId(), is(2));
		assertThat(actuals.get(2).getId(), is(3));
		assertThat(actuals.get(3).getId(), is(4));
		assertThat(actuals.get(4).getId(), is(5));
	}
	@Test
	public void testO() {
		List<TestTable> actuals = target.offset(3);
		assertThat(actuals.size(), is(10));
		assertThat(actuals.get(0).getId(), is(1));
		assertThat(actuals.get(1).getId(), is(2));
		assertThat(actuals.get(2).getId(), is(3));
		assertThat(actuals.get(3).getId(), is(4));
		assertThat(actuals.get(4).getId(), is(5));
		assertThat(actuals.get(5).getId(), is(6));
		assertThat(actuals.get(6).getId(), is(7));
		assertThat(actuals.get(7).getId(), is(8));
		assertThat(actuals.get(8).getId(), is(9));
		assertThat(actuals.get(9).getId(), is(10));
	}
}
