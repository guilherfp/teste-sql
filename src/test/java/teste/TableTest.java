package teste;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * @author guilherme.pacheco
 */
public class TableTest {

  @Test
  public void testSelect() throws Exception {
    Table table = Table.valueOf("rotina", "r").addColumns("c1", "c2", "c3");

    QueryBuilder queryBuilder = QueryBuilder.ofTable(table);

    String sql = "select c1, c2, c3 from rotina";
    assertThat(queryBuilder).hasToString(sql);
  }

  @Test
  public void testSelect_ComAlias() throws Exception {
    Table table = Table.valueOf("rotina", "r").addColumns("c1", "c2", "c3");

    QueryBuilder queryBuilder = QueryBuilder.ofTable(table);

    String sql = "select r.c1, r.c2, r.c3 from rotina r";
    assertThat(queryBuilder.toStringAlias()).isEqualTo(sql);
  }

  @Test
  public void testWhere() throws Exception {
    Table table = Table.valueOf("rotina").addColumns("c1", "c2", "c3");
    table.where().column("c1").eq("3");

    QueryBuilder queryBuilder = QueryBuilder.ofTable(table);

    String sql = "select c1, c2, c3 from rotina where c1 = 3";
    assertThat(queryBuilder).hasToString(sql);
  }

  @Test
  public void testWhere_ComAlias() throws Exception {
    Table table = Table.valueOf("rotina", "r").addColumns("c1", "c2", "c3");
    table.where().column("c1").eq("3");

    QueryBuilder queryBuilder = QueryBuilder.ofTable(table);

    String sql = "select r.c1, r.c2, r.c3 from rotina r where r.c1 = 3";
    assertThat(queryBuilder.toStringAlias()).isEqualTo(sql);
  }

  @Test
  public void testWhere_Com2Where() throws Exception {
    Table table = Table.valueOf("rotina");
    table.addColumns("c1", "c2", "c3");

    table.where().column("c1").eq("3").and().column("c2").eq("30");

    QueryBuilder queryBuilder = QueryBuilder.ofTable(table);

    String sql = "select c1, c2, c3 from rotina where c1 = 3 and c2 = 30";
    assertThat(queryBuilder).hasToString(sql);
  }

  @Test
  public void testWhere_GroupWheres() throws Exception {
    Table tb1 = Table.valueOf("tb1", "t1").addColumns("c1");

    Table tb2 = Table.valueOf("tb2", "t2").addColumns("c2");
    tb2.where().column("c2").eq("4");

    QueryBuilder queryBuilder = QueryBuilder.ofTable(tb1);
    queryBuilder.innerJoin(tb2).on(tb1, "c1").eq(tb2, "c2");

    String sql = "select t1.c1, t2.c2 "
        + "from tb1 t1 inner join tb2 t2 on t1.c1 = t2.c2 where t2.c2 = 4";
    assertThat(queryBuilder).hasToString(sql);
  }

}
