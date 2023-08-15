package catHouse.repositories;

import catHouse.entities.toys.Toy;

import java.util.Collection;
import java.util.HashSet;

public class ToyRepository implements Repository {
    private Collection<Toy> toys;

    public ToyRepository() {
        this.toys = new HashSet<>();
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public boolean removeToy(Toy toy) {
        return toys.remove(toy);
    }

    @Override
    public Toy findFirst(String type) {
        for (Toy toy : toys) {
            if (toy.getClass().getSimpleName().equalsIgnoreCase(type)) {
                return toy;
            }
        }
        return null;
    }
}
