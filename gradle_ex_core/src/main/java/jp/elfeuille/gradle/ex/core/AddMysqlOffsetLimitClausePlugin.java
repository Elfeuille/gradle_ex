package jp.elfeuille.gradle.ex.core;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.PrimitiveTypeWrapper;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class AddMysqlOffsetLimitClausePlugin extends PluginAdapter {
	private static final String LIMIT_FIELD_NAME = "limit";
	private static final String LIMIT_GETTER_NAME = "getLimit";
	private static final String LIMIT_SETTER_NAME = "setLimit";

	private static final String OFFSET_FIELD_NAME = "offset";
	private static final String OFFSET_GETTER_NAME = "getOffset";
	private static final String OFFSET_SETTER_NAME = "setOffset";

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

		Field limit = new Field();
		limit.setName(LIMIT_FIELD_NAME);
		limit.setVisibility(JavaVisibility.PRIVATE);
		limit.setType(integerWrapper);
		limit.addJavaDocLine("/** @mbggenerated */");
		topLevelClass.addField(limit);

		Method limitSet = new Method();
		limitSet.setVisibility(JavaVisibility.PUBLIC);
		limitSet.setName(LIMIT_SETTER_NAME);
		limitSet.addParameter(new Parameter(integerWrapper, LIMIT_FIELD_NAME));
		limitSet.addBodyLine("this." + LIMIT_FIELD_NAME + " = " + LIMIT_FIELD_NAME + ";");
		limitSet.addJavaDocLine("/** @mbggenerated */");
		topLevelClass.addMethod(limitSet);

		Method limitGet = new Method();
		limitGet.setVisibility(JavaVisibility.PUBLIC);
		limitGet.setReturnType(integerWrapper);
		limitGet.setName(LIMIT_GETTER_NAME);
		limitGet.addBodyLine("return " + LIMIT_FIELD_NAME + ";");
		limitGet.addJavaDocLine("/** @mbggenerated */");
		topLevelClass.addMethod(limitGet);

		Field offset = new Field();
		offset.setName(OFFSET_FIELD_NAME);
		offset.setVisibility(JavaVisibility.PRIVATE);
		offset.setType(integerWrapper);
		offset.addJavaDocLine("/** @mbggenerated */");
		topLevelClass.addField(offset);

		Method offsetSet = new Method();
		offsetSet.setVisibility(JavaVisibility.PUBLIC);
		offsetSet.setName(OFFSET_SETTER_NAME);
		offsetSet.addParameter(new Parameter(integerWrapper, OFFSET_FIELD_NAME));
		offsetSet.addBodyLine("this." + OFFSET_FIELD_NAME + " = " + OFFSET_FIELD_NAME + ";");
		offsetSet.addJavaDocLine("/** @mbggenerated */");
		topLevelClass.addMethod(offsetSet);

		Method offsetGet = new Method();
		offsetGet.setVisibility(JavaVisibility.PUBLIC);
		offsetGet.setReturnType(integerWrapper);
		offsetGet.setName(OFFSET_GETTER_NAME);
		offsetGet.addBodyLine("return " + OFFSET_FIELD_NAME + ";");
		offsetGet.addJavaDocLine("/** @mbggenerated */");
		topLevelClass.addMethod(offsetGet);

		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		XmlElement xe = new XmlElement("if");
		xe.addAttribute(new Attribute("test", LIMIT_FIELD_NAME + " != null"));
		xe.addElement(new TextElement("limit ${" + LIMIT_FIELD_NAME + "}"));
		element.getElements().add(xe);

		xe = new XmlElement("if");
		xe.addAttribute(new Attribute("test", LIMIT_FIELD_NAME + " != null and " + OFFSET_FIELD_NAME + " != null"));
		xe.addElement(new TextElement("offset ${" + OFFSET_FIELD_NAME + "}"));
		element.getElements().add(xe);
		return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean providerSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		List<String> clauses = new ArrayList<>();
		clauses.add("String limit = \"\";");
		clauses.add("String offset = \"\";");
		clauses.add("if (example != null && example." + LIMIT_GETTER_NAME + "() != null) {");
		clauses.add("limit = \" limit #{" + LIMIT_FIELD_NAME + ",jdbcType=INTEGER}\";");
		clauses.add("if (example." + OFFSET_GETTER_NAME + "() != null) {");
		clauses.add("offset = \" offset #{" + OFFSET_FIELD_NAME + ",jdbcType=INTEGER}\";");
		clauses.add("}");
		clauses.add("}");
		method.addBodyLines(method.getBodyLines().size() - 2, clauses);
		method.getBodyLines().set(method.getBodyLines().size() - 1, "return SQL() + limit + offset;");

		return super.providerSelectByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}
}
