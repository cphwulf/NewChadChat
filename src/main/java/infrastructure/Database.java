package infrastructure;

import domain.Pizza;
import domain.PizzaRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Database implements PizzaRepository {

    private final List<Pizza> pizzaMenu;
    private final File file;

    public Database() {
        file = new File("resources/menu");
        pizzaMenu = new ArrayList<>();
        this.loadPizzasFromDB();
    }

    @Override
    public Pizza createPizza(int no, String name, double price, String... ingredients) {
        return new Pizza(no,name,price,ingredients);
    }

    @Override
    public Iterable<Pizza> findAllPizzas() {
        return this.pizzaMenu;
    }

    @Override
    public Pizza findPizzaById(int id) {
        Pizza retPizza = null;
        for (Pizza p : pizzaMenu ) {
            System.out.println(p.getNo() + " and " + id);
            if (p.getNo()==id) {
                retPizza = p;
                return  retPizza;
            }
        }
        return retPizza;
    }

    @Override
    public void savePizzaToDB(Pizza pizza) {

    }
    private void loadPizzasFromDB() {
        //11,Hawai,Tomatsauce|ost|skinke|ananas|oregano,61
        String line = "";
        Pizza tmpPizza = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("menu");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);

        try {
            //BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedReader br = new BufferedReader(streamReader);

            while((line=br.readLine())!=null) {
                String[] lineArr = line.split(",");
                String[] ingredients = lineArr[2].split("|");
                tmpPizza = new Pizza(Integer.valueOf(lineArr[0]),lineArr[1],Double.valueOf(lineArr[3]),ingredients);
                pizzaMenu.add(tmpPizza);
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
