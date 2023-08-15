package catHouse.entities.cat;

public abstract class BaseCat implements Cat {
    private String name;
    private String breed;
    protected int kilograms;
    private double price;
    

    public BaseCat(String name, String breed, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("Cat name cannot be null or empty.");
        }
        if (breed == null || breed.trim().isEmpty()) {
            throw new NullPointerException("Cat breed cannot be null or empty.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Cat price cannot be below or equal to 0.");
        }
        this.name = name;
        this.breed = breed;
        this.price = price;
    }

     protected void setKilograms(int kilograms) {
        this.kilograms = kilograms;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getKilograms() {
        return kilograms;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
