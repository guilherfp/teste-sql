package teste;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author guilherme.pacheco
 */
public final class Table {

  private final String name;
  private List<String> columns;
  private Where where;
  private String alias;

  {
    columns = new LinkedList<>();
    where = Where.ofTable(this);
  }

  private Table(String name, String alias) {
    this.name = name;
    this.alias = alias;
  }

  public String getName() {
    return name;
  }

  public String getAlias() {
    return alias;
  }

  public Where where() {
    return where;
  }

  public Table addColumn(String columnName) {
    columns.add(columnName);
    return this;
  }

  public Table addColumns(String... columnNames) {
    columns.addAll(Arrays.asList(columnNames));
    return this;
  }

  public static Table valueOf(String name, String alias) {
    return new Table(name, alias);
  }

  public static Table valueOf(String name) {
    return new Table(name, name);
  }

  public String getColumns() {
    return columns.stream().collect(Collectors.joining(", "));
  }

  public String getColumnsAlias() {
    return columns.stream().map(c -> alias.concat(".").concat(c))
      .collect(Collectors.joining(", "));
  }

  public String getFromName() {
    return " from ".concat(name);
  }

  public String getFromNameAlias() {
    return " from ".concat(getNameAlias());
  }

  public String getNameAlias() {
    return name.concat(" ").concat(alias);
  }

  public String getWhereAlias() {
    return where.toStringAlias(alias);
  }

  public String getWhereAlias(boolean withClause) {
    return where.toStringAlias(withClause, alias);
  }

  public String getWhere() {
    return where.toString();
  }

  public String column(String column) {
    return String.format("%s.%s", alias, column);
  }

  public boolean hasWhere() {
    return where.hasWhere();
  }

}
