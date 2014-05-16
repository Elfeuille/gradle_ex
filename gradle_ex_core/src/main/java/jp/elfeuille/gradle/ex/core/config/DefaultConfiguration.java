package jp.elfeuille.gradle.ex.core.config;

import java.util.HashMap;
import java.util.Map;

import jp.elfeuille.gradle.ex.core.RoutingDataSource;
import jp.elfeuille.gradle.ex.core.SchemaHolder.Schema;

import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@ComponentScan(basePackages = "jp.elfeuille.gradle.ex")
@MapperScan(basePackages = "jp.elfeuille.gradle.ex.core.mapper")
public class DefaultConfiguration {

	@Bean
	public RoutingDataSource routingDataSource() throws ClassNotFoundException {
		RoutingDataSource rds = new RoutingDataSource();
		Map<Object, Object> targetDataSourceMap = new HashMap<>();
		rds.setDefaultTargetDataSource(masterDataSource());

		targetDataSourceMap.put(Schema.MASTER, masterDataSource());
		targetDataSourceMap.put(Schema.SLAVE, slaveDataSource());
		rds.setTargetDataSources(targetDataSourceMap);
		return rds;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(routingDataSource());
		return ssfb.getObject();
	}

	private SharedPoolDataSource getSharedPoolDataSource() {
		SharedPoolDataSource spds = new SharedPoolDataSource();
		spds.setMaxActive(10);
		spds.setMaxIdle(1);
		spds.setMaxWait(10000);
		spds.setTimeBetweenEvictionRunsMillis(60000);
		spds.setValidationQuery("select 1");
		spds.setNumTestsPerEvictionRun(3);
		spds.setTestOnBorrow(true);
		spds.setTestWhileIdle(true);
		return spds;
	}

	public SharedPoolDataSource masterDataSource() throws ClassNotFoundException {
		SharedPoolDataSource ads = this.getSharedPoolDataSource();
		DriverAdapterCPDS dacpds = new DriverAdapterCPDS();
		dacpds.setDriver("com.mysql.jdbc.Driver");
		dacpds.setUser("root");
		dacpds.setPassword("");
		dacpds.setUrl("jdbc:mysql://localhost:3306/gradle_ex");
		ads.setConnectionPoolDataSource(dacpds);
		return ads;
	}

	public SharedPoolDataSource slaveDataSource() throws ClassNotFoundException {
		SharedPoolDataSource ads = this.getSharedPoolDataSource();
		DriverAdapterCPDS dacpds = new DriverAdapterCPDS();
		dacpds.setDriver("com.mysql.jdbc.Driver");
		dacpds.setUser("root");
		dacpds.setPassword("");
		dacpds.setUrl("jdbc:mysql://localhost:3306/gradle_ex_slave");
		ads.setConnectionPoolDataSource(dacpds);
		return ads;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() throws ClassNotFoundException {
		return new DataSourceTransactionManager(routingDataSource());
	}
}
