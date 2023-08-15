package cats;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private House house;
    private Cat cat1;
    private Cat cat2;
    
    @Before
    public void setUp() {
        house = new House("Test House", 2);
        cat1 = new Cat("Fluffy");
        cat2 = new Cat("Whiskers");
    }

    @Test
    public void testAddCat() {
        house.addCat(cat1);
        house.addCat(cat2);

        assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatFullHouse() {
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(new Cat("Mittens")); // This should throw an exception
    }

    @Test
    public void testRemoveCat() {
        house.addCat(cat1);
        house.addCat(cat2);

        house.removeCat("Fluffy");

        assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentCat() {
        house.removeCat("NonExistentCat"); // This should throw an exception
    }

    @Test
    public void testCatForSale() {
        house.addCat(cat1);

        Cat soldCat = house.catForSale("Fluffy");

        assertFalse(soldCat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleNonExistentCat() {
        house.catForSale("NonExistentCat"); // This should throw an exception
    }

    @Test
    public void testStatistics() {
        house.addCat(cat1);
        house.addCat(cat2);

        String expectedStatistics = "The cat Fluffy is in the house Test House!, The cat Whiskers is in the house Test House!";
        assertEquals(expectedStatistics, house.statistics());
    }
}
