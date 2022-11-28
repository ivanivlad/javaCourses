package Lesson6.Homework;

public class Human {
    private  int age;
    private String name;
    private int weight;
    private static Human human;
    private Human(){}

    public String info(){
        return  this.name + " - " + "возраст " + this.age + " вес " +  this.weight;
    }

    public static HumanBuilder builder(){
        return new HumanBuilder();
    }

    public static class HumanBuilder{

        public HumanBuilder(){
            human = new Human();
        }
        public HumanBuilder name(String name){
            human.name = name;
            return this;
        }

        public HumanBuilder age(int age){
            human.age = age;
            return this;
        }

        public HumanBuilder weight(int weight){
            human.weight = weight;
            return this;
        }

        public Human build() {
            return human;
        }
    }

}
