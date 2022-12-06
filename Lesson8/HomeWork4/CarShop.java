package Lesson8.HomeWork4;

public class CarShop implements Market {

    private final Car car;

    public CarShop(Car car) {
        this.car = car;
    }

    @Override
    public String nameOfMarket() {
        return "Салон марки: " + car.nameOfMarket();
    }

    @Override
    public void saleCar(){

        System.out.println(nameOfMarket());
        System.out.println("Здравствуй клиент, цена этого авто:");

        try {
            car.saleCar();
            System.out.println("Хочешь купить авто?");
        } catch (NegativePriceException e){
            System.out.println("Неизвестна мне");
            System.out.println("Давайте посмотрим другое авто?");
        }

        System.out.print("\n");
    }
}
