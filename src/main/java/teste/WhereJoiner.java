package teste;

/**
 * @author guilherme.pacheco
 */
public final class WhereJoiner {

  private final OperadorAppender operadorAppender;

  public WhereJoiner(OperadorAppender operadorAppender) {
    this.operadorAppender = operadorAppender;
  }

  public WhereColumnBuider and() {
    return new WhereColumnBuider(operadorAppender);
  }

  public WhereColumnBuider or() {
    return new WhereColumnBuider(operadorAppender);
  }

}
