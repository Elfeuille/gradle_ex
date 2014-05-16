package jp.elfeuille.gradle.ex.core;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
//		private static Logger log = LoggerFactory.getLogger(RoutingDataSource.class);
	
	@Override
	protected Object determineCurrentLookupKey() {
//		log.debug("database schema : " + (SchemaHolder.getSchema() != null ? SchemaHolder.getSchema().name() : "default"));
		return SchemaHolder.getSchema();
	}
}
