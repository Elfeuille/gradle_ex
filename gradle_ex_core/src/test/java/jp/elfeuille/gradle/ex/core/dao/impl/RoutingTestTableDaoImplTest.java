package jp.elfeuille.gradle.ex.core.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import jp.elfeuille.gradle.ex.core.SchemaHolder;
import jp.elfeuille.gradle.ex.core.SchemaHolder.Schema;
import jp.elfeuille.gradle.ex.core.config.DefaultConfiguration;
import jp.elfeuille.gradle.ex.core.entity.SchemaChangeTestTable;
import jp.elfeuille.gradle.ex.core.mapper.SchemaChangeTestTableMapper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DefaultConfiguration.class})
public class RoutingTestTableDaoImplTest {
	@Inject
	private SchemaChangeTestTableMapper mapper;

	@Before
	public void before() {
		SchemaHolder.setSchema(Schema.MASTER);
		mapper.insert(this.createEntity(1,  "master db value 1"));
		SchemaHolder.setSchema(Schema.SLAVE);
		mapper.insert(this.createEntity(1,  "slave db value 1"));
	}

	@After
	public void after() {
		SchemaHolder.setSchema(Schema.MASTER);
		mapper.deleteByPrimaryKey(1);
		SchemaHolder.setSchema(Schema.SLAVE);
		mapper.deleteByPrimaryKey(1);
	}

	private SchemaChangeTestTable createEntity(int id, String value) {
		SchemaChangeTestTable entity = new SchemaChangeTestTable();
		entity.setId(id);
		entity.setValue(value);
		return entity;
	}

	@Test
	public void master() {
		SchemaHolder.setSchema(Schema.MASTER);
		SchemaChangeTestTable actual = mapper.selectByPrimaryKey(1);
		assertThat(actual.getValue(), is("master db value 1"));

	}
	@Test
	public void slave() {
		SchemaHolder.setSchema(Schema.SLAVE);
		SchemaChangeTestTable actual = mapper.selectByPrimaryKey(1);
		assertThat(actual.getValue(), is("slave db value 1"));
	}
}
