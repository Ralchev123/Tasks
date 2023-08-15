package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {
    private static final int INITIAL_KILOGRAMS = 7;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(getKilograms() + 1);
    }

    @Override
    public void eating() {
        kilograms += 1;
    }
}
