public class Dessert extends Food{
    private String component1;
    private String component2;

    public Dessert(String component1, String component2) {
        super("Дессерт");
        this.component1 = component1;
        this.component2 = component2;
    }

    public String toString() {
        return super.toString() + " c компонентами: '" + component1.toUpperCase() + "' и '" + component2.toUpperCase() +"'";
    }

    public boolean equals(Object arg0) {
        if (super.equals(arg0)) { // Шаг 1
            if (!(arg0 instanceof Dessert)) return false; // Шаг 2
            return component1.equals(((Dessert)arg0).component1) && component2.equals(((Dessert)arg0).component2);// Шаг 3
        } else
            return false;
    }

    public void consume() {
        System.out.println(this + " съеден");
    }

    public int calculateCalories() {
        int calories = 500;
        switch (component1) {
            case "Морожение" -> calories = 207;
            case "Шоколад" -> calories = 546;
            case "Бисквит" -> calories = 297;
        }
        switch (component2) {
            case "Мороженое" -> calories += 207;
            case "Шоколад" -> calories += 546;
            case "Бисквит" -> calories += 297;
        }
        return calories;
    };

    public int amountOfParameters() {
        return 2;
    };

    public String getComponent1() {
        return component1;
    }
    public void setComponent1(String component1) {
        this.component1 = component1;
    }
    public String getComponent2() {
        return component2;
    }
    public void setComponent2(String component2) {
        this.component2 = component2;
    }
}