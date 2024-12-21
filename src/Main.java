import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        Food[] breakfast = new Food[20];
        boolean calculating = false;
        boolean sorting = false;
        boolean counting = false;
        int step = 0;
        Food food = null;
        int counter = 0;
        int itemsSoFar = 0;
        for (String arg: args) {
            String[] parts = arg.split("/");
            try {
                switch (parts[0]) {
                    case "Cheese" -> {
                        breakfast[itemsSoFar] = new Cheese();
                        itemsSoFar++;
                    }
                    case "Apple" -> {
                        breakfast[itemsSoFar] = new Apple(parts[1]);
                        itemsSoFar++;
                    }
                    case "Dessert" -> {

                        Class<?> clazz = Class.forName(parts[0]);
                        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, String.class);
                        Object dessert = constructor.newInstance(parts[1], parts[2]);
                        breakfast[itemsSoFar] = (Food) dessert;
                        itemsSoFar++;

                    }
                    case "-calories" -> calculating = true;
                    case "-sort" -> sorting = true;
                    case "-count" -> {
                        counting = true;
                        step = itemsSoFar;
                    }
                    default -> System.out.println("Некорректный параметр");

                }
                if (counting && itemsSoFar - 1 == step) {
                    food = breakfast[step];
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Продукт класса " + parts[0] + " не найден");
            } catch (NoSuchMethodException e) {
                System.out.println("Метод для класса " + parts[0] + " не найден");
            }
        }

        if (food == null) {
            food = breakfast[0];
        }

        int calories = 0;

        if (sorting) {
            Arrays.sort(breakfast, new Comparator<Food>() {
                @Override
                public int compare(Food food1, Food food2) {
                    if (food1 == null && food2 == null) return 0;
                    if (food1 == null) return 1;
                    if (food2 == null) return -1;
                    return Integer.compare(food2.amountOfParameters(), food1.amountOfParameters());
                }
            });
        }

        for (Food item: breakfast)
            if (item!=null) {
                if (counting && item.equals(food))
                    counter++;
                if (calculating)
                    calories += item.calculateCalories();
                item.consume();
            }
            else
                break;

        System.out.println("Было съедено " + food + ": " + counter);
        if (calculating)
            System.out.println("Количество калорий в завтраке: " + calories);
        System.out.println("Всего хорошего!");
    }
}