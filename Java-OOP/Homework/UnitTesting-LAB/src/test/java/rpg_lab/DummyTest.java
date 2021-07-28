package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    private static final int DUMMY_HEALTH = 50;
    private static final int DUMMY_XP = 100;
    private static final int ATTACK = 10;
    private static final int EXPECTED_DUMMY_HP_AFTER_ATTACK = DUMMY_HEALTH - ATTACK;

    private Dummy dummy;
    private Dummy deadDummy;

    @Before
    public void setUp() {
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
        this.deadDummy = new Dummy(0, DUMMY_XP);
    }

    @Test
    public void dummyLosesHealthWhenAttacked() {
        dummy.takeAttack(ATTACK);
        Assert.assertEquals(EXPECTED_DUMMY_HP_AFTER_ATTACK, dummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void deadDummyShouldThrowWhenAttacked() {
        deadDummy.takeAttack(ATTACK);
    }

    @Test
    public void deadDummyShouldGiveXP() {
        Assert.assertEquals(DUMMY_XP, deadDummy.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyShouldNotGiveXP() {
        dummy.giveExperience();
    }
}