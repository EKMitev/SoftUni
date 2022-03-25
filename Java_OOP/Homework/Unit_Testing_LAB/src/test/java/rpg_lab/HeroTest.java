package rpg_lab;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class HeroTest {

    private static final String HERO_NAME = "Test Testovich";
    private static final int TARGET_XP = 50;

    private Hero hero;
    private Target target;

    // Anonymous Class
    /*
      this.target = new Target() {

      @Override public int getHealth() { return 0; }
      @Override public void takeAttack(int attackPoints) { }
      @Override public int giveExperience() { return TARGET_XP; }
      @Override public boolean isDead() { return true; }
      };
      <p>
      this.weapon = new Weapon() {
      @Override public int getAttackPoints() { return 10; }
      @Override public int getDurabilityPoints() { return 0; }
      @Override public void attack(Target target) { }
      };
     */

    @Before
    public void setUP() {
        target = Mockito.mock(Target.class);
        Weapon weapon = Mockito.mock(Weapon.class);
        this.hero = new Hero(HERO_NAME, weapon);
    }


    @Test
    public void testHeroGainingXPAfterKillingTarget() {
        when(target.isDead()).thenReturn(true);
        when(target.giveExperience()).thenReturn(TARGET_XP);

        hero.attack(target);

        Assert.assertEquals(TARGET_XP, hero.getExperience());
    }
}