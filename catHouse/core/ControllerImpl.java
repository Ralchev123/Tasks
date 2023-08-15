package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.houses.House;
import catHouse.entities.toys.Toy;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.repositories.Repository;
import java.util.Collection;
import java.util.ArrayList;


public class ControllerImpl implements Controller {
    private Repository toyRepository;
    private Collection<House> houseCollection;

    public ControllerImpl(Repository toyRepository) {
        this.toyRepository = toyRepository;
        this.houseCollection = new ArrayList<>();

    }

    @Override
public String addHouse(String type, String name) {
    House house;
    switch (type) {
        case "ShortHouse":
            house = new ShortHouse(name);
            break;
        case "LongHouse":
            house = new LongHouse(name);
            break;
        default:
            throw new IllegalArgumentException("Invalid house type.");
    }

    // Add the created house to your collection or repository
    houseCollection.add(house); // Assuming houseCollection is a collection of houses

    return String.format("Successfully added %s named %s.", type, name);
}


    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException("Invalid toy type.");
        }
        toyRepository.buyToy(toy);
        return String.format("Successfully bought %s.", type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        House house = findHouseByName(houseName);
        if (house != null) {
            Toy toy;
            switch (toyType) {
                case "Ball":
                    toy = new Ball();
                    break;
                case "Mouse":
                    toy = new Mouse();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid toy type.");
            }
            house.buyToy(toy);
            return String.format("Successfully added %s to %s.", toyType, houseName);
        }
        return String.format("House %s not found.", houseName);
    }

  @Override
public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
    if (price < 0) {
        throw new IllegalArgumentException("Invalid price. Price cannot be negative.");
    }

    House house = findHouseByName(houseName);
    if (house != null) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException("Invalid cat type.");
        }
        house.addCat(cat);
        return String.format("Successfully added %s - %s to %s.", catType, catName, houseName);
    }
    return String.format("House %s not found.", houseName);
}


    @Override
    public String feedingCat(String houseName) {
        House house = findHouseByName(houseName);
        if (house != null) {
            house.feeding();
            return String.format("Cats are fed in %s.", houseName);
        }
        return String.format("House %s not found.", houseName);
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = findHouseByName(houseName);
        if (house != null) {
            int sumSoftness = house.sumSoftness();
            return String.format("Softness: %d", sumSoftness);
        }
        return String.format("House %s not found.", houseName);
    }

   @Override
public String getStatistics() {
    StringBuilder statistics = new StringBuilder();

    // Iterate through each house and gather statistics
    for (House house : houseCollection) {
        String houseType = house instanceof ShortHouse ? "ShortHouse" : "LongHouse";
        statistics.append(String.format("%s %s:\n", house.getName(), houseType));

        Collection<Cat> cats = house.getCats();
        if (!cats.isEmpty()) {
            statistics.append("Cats: ");
            for (Cat cat : cats) {
                statistics.append(cat.getName()).append(" ");
            }
        } else {
            statistics.append("Cats: none");
        }

        Collection<Toy> toys = house.getToys();
        int toysCount = toys.size();
        int sumSoftness = house.sumSoftness();
        statistics.append(String.format("\nToys: %d Softness: %d\n", toysCount, sumSoftness));
    }

    return statistics.toString();
}



private House findHouseByName(String houseName) {
    // Assuming you have a collection of houses, you can iterate through them to find the desired house
    for (House house : houseCollection) {
        if (house.getName().equals(houseName)) {
            return house;
        }
    }
    return null; // House not found
}

}
