package domain;

public interface PizzaFactory {
    Pizza createPizza(int no, String name, double price, String... ingredients);
}

