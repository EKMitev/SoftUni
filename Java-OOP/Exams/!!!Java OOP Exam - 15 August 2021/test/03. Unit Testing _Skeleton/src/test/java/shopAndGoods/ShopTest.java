package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private Shop shop;
    private Goods goods;

    @Before
    public void setUp() {
        shop = new Shop();
        goods = new Goods("test", "11");
    }

    @Test
    public void testCtor() {
        Assert.assertNotNull(shop);
    }

    @Test
    public void testCtor2() {
        Assert.assertEquals(12, shop.getShelves().size());
    }

    @Test
    public void testGet() {
        int actual = shop.getShelves().size();
        Assert.assertEquals(12, actual);
    }

    @Test(expected = IllegalArgumentException.class) // !!!
    public void testAddNotPresent() throws OperationNotSupportedException {
        shop.addGoods("a", goods);
    }

    @Test(expected = IllegalArgumentException.class) // !!!
    public void testAddTaken() throws OperationNotSupportedException {
        shop.addGoods("Shelves12", goods);
        shop.addGoods("Shelves12", goods);
    }

    @Test(expected = OperationNotSupportedException.class) // !!!
    public void testAddTaken2() throws OperationNotSupportedException {
        shop.addGoods("Shelves12", goods);
        shop.addGoods("Shelves11", goods);
    }

    @Test
    public void testAdd() throws OperationNotSupportedException {
        String s = shop.addGoods("Shelves12", goods);
        Assert.assertEquals("Goods: 11 is placed successfully!", s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotPresent() {
        shop.removeGoods("s", goods);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNotPresent1() {
        shop.removeGoods("Shelves12", goods);
    }

    @Test
    public void testRemove() throws OperationNotSupportedException {
        shop.addGoods("Shelves12", goods);
        shop.removeGoods("Shelves12", goods);
        Assert.assertNull(shop.getShelves().get("Shelves12"));
    }

    @Test
    public void testRemove1() throws OperationNotSupportedException {
        shop.addGoods("Shelves12", goods);
        String actual = shop.removeGoods("Shelves12", goods);
        Assert.assertEquals("Goods: 11 is removed successfully!", actual);
    }

}