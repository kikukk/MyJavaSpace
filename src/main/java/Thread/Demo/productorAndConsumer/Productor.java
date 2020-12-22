package Thread.Demo.productorAndConsumer;

public class Productor extends Thread {
    private ProductPool pool;

    Productor(ProductPool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        while(true){
            String name = (int)(Math.random()*100)+"号产品";
            System.out.println("生产了一件产品："+name);
            Product product = new Product(name);
            this.pool.push(product);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
