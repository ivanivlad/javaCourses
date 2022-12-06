package Lesson8.HomeWork4;

public class Car implements Market{

    private final String model;
    private final int price;

    Car(String model, int price) {
        this.price = price;
        this.model = model;
    }

    @Override
    public String nameOfMarket() {
        return model;
    }

    @Override
    public void saleCar() throws negativePriceException {

        if (price < 0){
            throw new negativePriceException();
        }else {
            System.out.println(price);
        }
    }
}
