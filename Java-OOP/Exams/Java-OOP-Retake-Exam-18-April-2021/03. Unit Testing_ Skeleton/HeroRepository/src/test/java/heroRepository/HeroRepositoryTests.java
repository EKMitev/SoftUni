package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class HeroRepositoryTests {

    private HeroRepository heroRepository;
    private Hero hero;

    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("test", 999);
    }

    @Test
    public void testConstructor() {
        assertNotNull(heroRepository);
    }

    @Test
    public void testGetCount() {
        int length = heroRepository.getCount();
        assertEquals(0, length);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateNull() {
        heroRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSameName() {
        heroRepository.create(hero);
        heroRepository.create(hero);
    }

    @Test
    public void testCreate() {
        String expected = "Successfully added hero test with level 999";
        String actual = heroRepository.create(hero);
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveNull() {
        heroRepository.remove("         ");
    }

    @Test
    public void testRemove() {
        heroRepository.create(hero);
        boolean removed = heroRepository.remove(hero.getName());
        assertTrue(removed);
    }

    @Test
    public void testGetHeroWithHighestLevel() {
        heroRepository.create(hero);
        heroRepository.create(new Hero("pesho", 1));
        heroRepository.create(new Hero("gosho", 500));

        Hero heroWithHighestLevel = heroRepository.getHeroWithHighestLevel();

        assertEquals(hero, heroWithHighestLevel);
    }

    @Test
    public void testGetHero() {
        heroRepository.create(hero);
        Hero hero1 = heroRepository.getHero("test");
        assertEquals(hero, hero1);
    }

    @Test
    public void testGetHeroNull() {
        heroRepository.create(hero);
        Hero hero1 = heroRepository.getHero("testovich");
        assertNull(hero1);
    }

    @Test
    public void testGetHeroes(){
        Collection<Hero> heroes = heroRepository.getHeroes();
        assertNotNull(heroes);
    }

}
