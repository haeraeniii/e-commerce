package hhplus.e_commerce.support.strategy;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class UpperCaseNamingStrategy implements PhysicalNamingStrategy {
    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment context) {
        return convertToUpperUnderscore(name);
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment context) {
        return convertToUpperUnderscore(name);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return convertToUpperUnderscore(name);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment context) {
        return convertToUpperUnderscore(name);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return convertToUpperUnderscore(name);
    }

    private Identifier convertToUpperUnderscore(final Identifier identifier) {
        if (identifier == null) {
            return null;
        }
        final String newName = identifier.getText()
                .replaceAll("([a-z])([A-Z]+)", "$1_$2")
                .toUpperCase();
        return Identifier.toIdentifier(newName);
    }
}
