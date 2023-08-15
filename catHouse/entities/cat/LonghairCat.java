package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    private static final int INITIAL_KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(getKilograms() + 3);
    }

    @Override
    public void eating() {
        kilograms += 3;
    }
}
