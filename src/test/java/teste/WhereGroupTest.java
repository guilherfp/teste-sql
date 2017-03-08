package teste;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WhereGroupTest {

  @Test
  public void testToString_Simple() throws Exception {
    WhereGroup group = new WhereGroup(new Operator("c1", "=", "3"));

    assertThat(group).hasToString("c1 = 3");
  }

  @Test
  public void testToString_WithTwoAnd() throws Exception {
    WhereGroup group = new WhereGroup(new Operator("c1", "=", "30"));
    group.and(new Operator("c3", ">", "10"));

    assertThat(group).hasToString("c1 = 30 and c3 > 10");
  }

  @Test
  public void testToString_OperatorWithGroup() throws Exception {
    WhereGroup group = new WhereGroup(new Operator("c1", "=", "30"));
    group.and(new WhereGroup(new Operator("c3", ">", "10")));

    assertThat(group).hasToString("c1 = 30 and (c3 > 10)");
  }

  @Test
  public void testToString_WithTwoGroup() throws Exception {
    WhereGroup group1 = new WhereGroup(new Operator("c1", "=", "30"));

    WhereGroup group2 = new WhereGroup(new Operator("c3", ">", "10"));
    group2.and(new Operator("c5", "like", "10"));

    group1.and(group2);

    assertThat(group1).hasToString("c1 = 30 and (c3 > 10 and c5 like 10)");
  }

}
