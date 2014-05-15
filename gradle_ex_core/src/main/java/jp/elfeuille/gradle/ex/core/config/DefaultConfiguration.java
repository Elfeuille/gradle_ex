package jp.elfeuille.gradle.ex.core.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = "jp.elfeuille.gradle.ex")
@MapperScan("jp.elfeuille.gradle.ex.core.mapper")
public class DefaultConfiguration {

	@Bean
	public DataSource basicDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/gradle_ex");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(basicDataSource());
		return ssfb.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws ClassNotFoundException {
		return new DataSourceTransactionManager(basicDataSource());
	}
}
