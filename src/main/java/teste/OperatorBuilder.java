package teste;

public class OperatorBuilder {

  private final OperadorAppender operadorAppender;
  private final String column;

  public OperatorBuilder(String column, OperadorAppender operadorAppender) {
    this.operadorAppender = operadorAppender;
    this.column = column;
  }

  public WhereJoiner eq(String value) {
    operadorAppender.accept(new Operator(column, "=", value));
    return new WhereJoiner(operadorAppender);
  }

}
