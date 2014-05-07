package jp.elfeuille.gradle.ex.web.json;

public class RootJson {
	private String key;

	public static RootJson create(String key) {
		RootJson json = new RootJson();
		json.setKey(key);
		return json;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
