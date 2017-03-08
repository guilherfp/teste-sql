package teste;

public class Operator {

  private String column;
  private String operator;
  private String value;

  public Operator(String column, String operator, String value) {
    this.column = column;
    this.operator = operator;
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("%s %s %s", column, operator, value);
  }

  public String toStringAlias(String alias) {
    return String.format("%s.%s %s %s", alias, column, operator, value);
  }

}
