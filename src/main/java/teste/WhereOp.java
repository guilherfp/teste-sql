package teste;

public abstract class WhereOp {

  private final String union;

  public WhereOp(String union) {
    this.union = union;
  }

  public String getUnion() {
    return union;
  }

  abstract String format();

  abstract String formatAlias(String alias);

}
