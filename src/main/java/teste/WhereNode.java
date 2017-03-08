package teste;

final class WhereNode extends WhereOp {

  private Operator operator;

  public WhereNode(Operator operator) {
    super("");
    this.operator = operator;
  }

  public WhereNode(String union, Operator operator) {
    super(union);
    this.operator = operator;
  }

  @Override
  String format() {
    return String.format(" %s %s", getUnion(), operator);
  }

  @Override
  public String toString() {
    return format();
  }

  @Override
  String formatAlias(String alias) {
    return String.format(" %s %s", getUnion(), operator.toStringAlias(alias));
  }

}
