package teste;

import java.util.LinkedList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WhereGroup extends WhereOp {

  private LinkedList<WhereOp> list;

  {
    list = new LinkedList<>();
  }

  public WhereGroup(Operator operator) {
    super("");
    list.add(new WhereNode(operator));
  }

  private WhereGroup(String union, WhereGroup whereGroup) {
    super(union);
    list.add(whereGroup);
  }

  public WhereGroup() {
    super("");
  }

  @Override
  public String toString() {
    return list.stream().map(WhereOp::format).collect(Collectors.joining()).trim();
  }

  @Override
  public String formatAlias(String alias) {
    return list.stream().map(w -> w.formatAlias(alias))
      .collect(Collectors.joining()).trim();
  }

  public void and(Operator operator) {
    list.add(new WhereNode("and", operator));
  }

  public void and(WhereGroup whereGroup) {
    list.add(new WhereGroup("and", whereGroup));
  }

  public void or(WhereGroup whereGroup) {
    list.add(new WhereGroup("or", whereGroup));
  }

  @Override
  String format() {
    return String.format(" %s (%s)", getUnion(), operators());
  }

  private String operators() {
    return list.stream().map(map()).collect(Collectors.joining());
  }

  private Function<? super WhereOp, ? extends String> map() {
    return whereOp -> {
      if (whereOp instanceof WhereNode) {
        return whereOp.format();
      } else {
        return whereOp.toString();
      }
    };
  }

}
