package monster_game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    public void testMonsterHealthReduction() {
        Monster monster = new SimpleMonster("TestMonster", 15, 0, 0);
        try {
            monster.reduceHealth(5);
        } catch (HealthException e) {
            fail("HealthException sollte nicht ausgelöst werden. ");
        }
        assertEquals(10, monster.health);
    }

    @Test
    public void testStudentHealthReduction() {
        Student student = new Student("TestStudent", 15, 0, 0);
        try {
            student.reduceHealth(1);
        } catch (HealthException e) {
            fail("HealthException sollte nicht ausgelöst werden.");
        }
        assertEquals(14, student.health);
    }

    @Test
    public void testSimpleMonsterAttack() {
        Monster student = new Student("TestStudent", 15, 0, 0);
        Monster monster = new SimpleMonster("TestMonster", 15, 0, 0);
        monster.attack(student);
        assertEquals(13, student.health);
    }

    @Test
    public void testAdvancedMonsterAttack() {
        Monster student = new Student("TestStudent", 15, 0, 0);
        Monster monster = new AdvancedMonster("TestMonster", 15, 0, 0);
        monster.attack(student);
        assertEquals(11, student.health);
    }
}
