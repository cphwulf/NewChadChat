package domain;

public interface PizzaRepository extends PizzaFactory {
    Iterable<Pizza> findAllPizzas();
    Pizza findPizzaById(int id) ;
    void savePizzaToDB(Pizza pizza);
}
