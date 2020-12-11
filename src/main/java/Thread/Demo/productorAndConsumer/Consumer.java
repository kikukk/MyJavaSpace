package Thread.Demo.productorAndConsumer;

public class Consumer extends Thread{
    private ProductPool pool;

    Consumer(ProductPool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        while(true){
            Product product = this.pool.pop();
            System.out.println("消费者消费了一件产品："+product.getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
