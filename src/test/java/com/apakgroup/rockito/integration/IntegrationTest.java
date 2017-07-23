package com.apakgroup.rockito.integration;

import static com.apakgroup.rockito.choice.Selector.mock;
import static com.apakgroup.rockito.choice.Selector.when;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.apakgroup.rockito.choice.Mock;

public class IntegrationTest {

    @Test
    public void testAssertIntegerIteration() {
        Mock<TestObject> m = mock(TestObject.class);

        when(m.calls().getI()).thenIterate(1, 2, 3);
        when(m.calls().getK()).thenIterate(6, 7);

        StringBuilder sb = new StringBuilder();

        for (TestObject i : m) {
            sb.append("(").append(i.getI()).append(" , ").append(i.getK()).append(") ");
        }

        String casesCovered = sb.deleteCharAt(sb.length() - 1).toString();

        Assertions.assertThat(casesCovered).as("Some expected cases were missing.")
                .isEqualTo("(1 , 6) (2 , 6) (3 , 6) (1 , 7) (2 , 7) (3 , 7)");
    }

    @Test
    public void testAssertIteration() {
        Mock<TestObject> m = mock(TestObject.class);

        when(m.calls().getStr()).thenIterate("a", "b");
        when(m.calls().getK()).thenIterate(6, 7);

        StringBuilder sb = new StringBuilder();

        for (TestObject i : m) {
            sb.append("(").append(i.getStr()).append(" , ").append(i.getK()).append(") ");
        }

        String casesCovered = sb.deleteCharAt(sb.length() - 1).toString();

        Assertions.assertThat(casesCovered).as("Some expected cases were missing.")
                .isEqualTo("(a , 6) (b , 6) (a , 7) (b , 7)");
    }

    @Test
    public void testAssertNestedIterations() {
        Mock<TestObject> mTest = mock(TestObject.class);
        Mock<ParentTestObject> mParentTest = mock(ParentTestObject.class);

        when(mTest.calls().getStr()).thenIterate("a", "b");
        when(mTest.calls().getK()).thenIterate(6, 7);

        when(mParentTest.calls().getTo()).thenIterate(mTest);

        StringBuilder sb = new StringBuilder();

        for (ParentTestObject p : mParentTest) {
            sb.append("(").append(p.getTo().getStr()).append(" , ").append(p.getTo().getK()).append(") ");
        }

        String casesCovered = sb.deleteCharAt(sb.length() - 1).toString();

        Assertions.assertThat(casesCovered).as("Some expected cases were missing.")
                .isEqualTo("(a , 6) (b , 6) (a , 7) (b , 7)");
    }

    @Test
    public void testAssertRandomValues() {
        Mock<TestObject> m = mock(TestObject.class);

        when(m.calls().getI()).choose(10).from(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        when(m.calls().getK()).thenIterate(6);

        for (TestObject i : m) {
            Assertions.assertThat(i.getI()).isBetween(1, 11);
        }
    }

    @Test
    public void testAssertRandomValuesInRange() {
        Mock<TestObject> m = mock(TestObject.class);

        when(m.calls().getI()).choose(10).between(1, 1000);
        when(m.calls().getK()).thenIterate(6);

        for (TestObject i : m) {
            Assertions.assertThat(i.getI()).isBetween(1, 1000);
        }
    }

    @Test
    public void testAssertRandomCharsInRange() {
        Mock<TestObject> m = mock(TestObject.class);

        when(m.calls().getC()).choose(2).between('a', 'z');
        when(m.calls().getK()).thenIterate(6, 7);

        for (TestObject i : m) {
            Assertions.assertThat(i.getC()).isBetween('a', 'z');
        }
    }

    @SuppressWarnings("unused")
    @Test
    public void testAssertCorrectIterations() {
        Mock<TestObject> m = mock(TestObject.class);

        when(m.calls().getC()).thenIterate('a', 'b');
        when(m.calls().getK()).thenIterate(6, 7);

        int calls = 0;

        for (TestObject i : m) {
            calls++;
        }

        Assertions.assertThat(calls).isEqualTo(4);
    }
}

