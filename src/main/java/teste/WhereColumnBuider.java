package teste;

public class WhereColumnBuider {

  private final OperadorAppender operadorAppender;

  public WhereColumnBuider(OperadorAppender operadorAppender) {
    this.operadorAppender = operadorAppender;
  }

  public OperatorBuilder column(String column) {
    return new OperatorBuilder(column, operadorAppender);
  }

}
