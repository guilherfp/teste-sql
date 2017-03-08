package teste;

public class TableJoiner {

  private String sql;
  private QueryBuilder queryBuilder;

  public TableJoiner(QueryBuilder queryBuilder) {
    this.queryBuilder = queryBuilder;
  }

  public TableEquals on(Table tableLeft, String column) {
    return new TableEquals(this, tableLeft, column);
  }

  public String getSql() {
    return sql;
  }

  public void addEquals(String sql) {
    this.sql = sql;
  }

  public void addEquals(TableEquals tableEquals) {
    queryBuilder.add(tableEquals);
  }

}
