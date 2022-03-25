package computers;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputerManagerTests {

    @Test
    public void testGetComputers() {
        ComputerManager computerManager = new ComputerManager();
        List<Computer> computers = computerManager.getComputers();
        assertEquals(0, computers.size());
    }

    @Test
    public void testGetCount() {
        ComputerManager computerManager = new ComputerManager();
        int count = computerManager.getCount();
        assertEquals(count, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(null);
    }

    @Test
    public void testAdd() {
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(new Computer("t", "t", 1.1));
        int size = computerManager.getCount();
        assertEquals(size, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalid() {
        ComputerManager computerManager = new ComputerManager();
        Computer computer = new Computer("t", "t", 1.1);
        computerManager.addComputer(computer);
        computerManager.removeComputer("a", "a");
    }

    @Test
    public void testRemoveValid() {
        ComputerManager computerManager = new ComputerManager();
        Computer computer = new Computer("t", "t", 1.1);
        computerManager.addComputer(computer);
        computerManager.removeComputer("t", "t");
        int size = computerManager.getCount();
        assertEquals(size, 0);
    }
 @Test(expected = IllegalArgumentException.class)
    public void testGetInvalid() {
        ComputerManager computerManager = new ComputerManager();
        Computer computer = new Computer("t", "t", 1.1);
        computerManager.addComputer(computer);
        computerManager.getComputer("a", "a");
    }

    @Test
    public void testGetValid() {
        ComputerManager computerManager = new ComputerManager();
        Computer computer = new Computer("t", "t", 1.1);
        computerManager.addComputer(computer);
        Computer computer1 = computerManager.getComputer("t", "t");
        assertEquals(computer1, computer);
    }
@Test(expected = IllegalArgumentException.class)
    public void testGetByManufacturerInvalid() {
        ComputerManager computerManager = new ComputerManager();
        Computer computer = new Computer("t", "t", 1.1);
        computerManager.addComputer(computer);
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void testGetByManufacturerValid() {
        ComputerManager computerManager = new ComputerManager();
        Computer computer = new Computer("t", "t", 1.1);
        computerManager.addComputer(computer);
        List<Computer> t = computerManager.getComputersByManufacturer("t");
        List<Computer> t1 = computerManager.getComputers();
        assertEquals(t1, t);
    }

}