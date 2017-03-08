package teste;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * @author guilherme.pacheco
 */
public final class Where {

  private final Table table;
  private WhereGroup whereGroup;
  private List<WhereGroup> whereGroups;

  {
    whereGroups = new LinkedList<>();
  }

  private Where(Table table) {
    this.table = table;
  }

  public static Where ofTable(Table table) {
    return new Where(table);
  }

  public OperatorBuilder column(String column) {
    return new OperatorBuilder(column, operadorAppender());
  }

  private OperadorAppender operadorAppender() {
    return operator -> {
      if (whereGroup == null) {
        whereGroup = new WhereGroup(operator);
        whereGroups.add(whereGroup);
      } else {
        whereGroup.and(operator);
      }
    };
  }

  public void add(WhereGroup whereGroup) {
    whereGroups.add(whereGroup);
  }

  @Override
  public String toString() {
    return toString(WhereGroup::toString);
  }

  public String toStringAlias(String alias) {
    return toString(w -> w.formatAlias(alias));
  }

  public String toStringAlias(boolean withClause, String alias) {
    String where = toStringAlias(alias);
    if (withClause) {
      return where;
    }
    return where.replaceFirst("where", "").trim();
  }

  private String toString(Function<WhereGroup, String> map) {
    if (!hasWhere()) {
      return StringUtils.EMPTY;
    } else {
      return " where ".concat(join(map));
    }
  }

  private String join(Function<WhereGroup, String> map) {
    return whereGroups.stream().map(map).collect(Collectors.joining());
  }

  public boolean hasWhere() {
    return !whereGroups.isEmpty();
  }

}
