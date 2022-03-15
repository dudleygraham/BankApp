package com.learning.utils;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		
		String newNameString = name.getText().concat("_TBL");
		return Identifier.toIdentifier(newNameString);
	}
}
