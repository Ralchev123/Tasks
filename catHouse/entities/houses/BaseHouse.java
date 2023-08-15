package catHouse.entities.houses;

import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.Collection;
import java.util.HashSet;

public abstract class BaseHouse implements House {
    protected String name;
    protected int capacity;
    protected Collection<Cat> cats;
    protected Collection<Toy> toys;

    public BaseHouse(String name, int capacity) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException("House name cannot be null or empty.");
        }
        this.name = name;
        this.capacity = capacity;
        this.cats = new HashSet<>();
        this.toys = new HashSet<>();
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
    public Collection<Cat> getCats() {
        return cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return toys;
    }

    @Override
    public void addCat(Cat cat) {
        if (cats.size() < capacity) {
            cats.add(cat);
        } else {
            throw new IllegalStateException("Not enough capacity for this cat.");
        }
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public void feeding() {
        for (Cat cat : cats) {
            cat.eating();
        }
    }

    @Override
    public int sumSoftness() {
        int sum = 0;
        for (Toy toy : toys) {
            sum += toy.getSoftness();
        }
        return sum;
    }

    @Override
    public String getStatistics() {
        StringBuilder catsInfo = new StringBuilder();
        if (!cats.isEmpty()) {
            catsInfo.append("Cats: ");
            for (Cat cat : cats) {
                catsInfo.append(cat.getName()).append(" ");
            }
        } else {
            catsInfo.append("Cats: none");
        }

        return String.format("%s %s:%n%s%nToys: %d Softness: %d",
                name, this.getClass().getSimpleName(), catsInfo.toString(), toys.size(), sumSoftness());
    }
}
