package teste;

import java.util.LinkedList;
import java.util.List;

public class QueryBuilder {

  private Table table;
  private List<TableEquals> tableJoiners;

  {
    tableJoiners = new LinkedList<>();
  }

  public QueryBuilder(Table table) {
    this.table = table;
  }

  @Override
  public String toString() {
    if (tableJoiners.isEmpty()) {
      return select(table);
    } else {
      return selectJoins();
    }
  }

  public String toStringAlias() {
    if (tableJoiners.isEmpty()) {
      return selectAlias(table);
    } else {
      return selectJoins();
    }
  }

  private String selectJoins() {
    StringBuilder builder = new StringBuilder("select ");
    for (TableEquals tableJoiner : tableJoiners) {
      builder.append(tableJoiner.getTableLeft().getColumnsAlias());
      builder.append(", ");
      builder.append(tableJoiner.getTableRight().getColumnsAlias());
      builder.append(tableJoiner.getTableLeft().getFromNameAlias());
      builder.append(tableJoiner.sqlUnion());
      boolean hasWhere = tableJoiner.getTableLeft().hasWhere();
      builder.append(tableJoiner.getTableLeft().getWhereAlias());
      builder.append(tableJoiner.getTableRight().getWhereAlias(!hasWhere));
    }
    return builder.toString();
  }

  private String select(Table table) {
    StringBuilder builder = new StringBuilder("select ");
    builder.append(table.getColumns());
    builder.append(table.getFromName());
    builder.append(table.getWhere());
    return builder.toString();
  }

  private String selectAlias(Table table) {
    StringBuilder builder = new StringBuilder("select ");
    builder.append(table.getColumnsAlias());
    builder.append(table.getFromNameAlias());
    builder.append(table.getWhereAlias());
    return builder.toString();
  }

  public static QueryBuilder ofTable(Table table) {
    return new QueryBuilder(table);
  }

  public TableJoiner innerJoin(Table table) {
    return new TableJoiner(this);
  }

  public void add(TableEquals tableEquals) {
    tableJoiners.add(tableEquals);
  }

}
