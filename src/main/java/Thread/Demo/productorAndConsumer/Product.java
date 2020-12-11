package Thread.Demo.productorAndConsumer;

public class Product {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Product(String name){
        this.name = name;
    }

}
