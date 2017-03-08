package teste;

public class TableEquals {

  private TableJoiner tableJoiner;
  private Table tableLeft;
  private String columnLeft;
  private Table tableRight;
  private String columnRight;

  public TableEquals(TableJoiner tableJoiner, Table tableLeft, String columnLeft) {
    this.tableJoiner = tableJoiner;
    this.tableLeft = tableLeft;
    this.columnLeft = columnLeft;
  }

  public void eq(Table tableRight, String columnRight) {
    this.tableRight = tableRight;
    this.columnRight = columnRight;
    tableJoiner.addEquals(this);
  }

  public Table getTableLeft() {
    return tableLeft;
  }

  public Table getTableRight() {
    return tableRight;
  }

  public String sqlUnion() {
    return String.format(" inner join %s on %s = %s", tableRight.getNameAlias(),
      tableLeft.column(columnLeft), tableRight.column(columnRight));
  }

}
