package jp.elfeuille.gradle.ex.core;

public class SchemaHolder {
	public static enum Schema {
		MASTER,
		SLAVE;
	}

	private static final ThreadLocal<Schema> schemaHolder = new ThreadLocal<>();

	public static void setSchema(Schema schema) {
		schemaHolder.set(schema);
	}

	public static Schema getSchema() {
		return schemaHolder.get();
	}

	public static void clear() {
		schemaHolder.remove();
	}
}
