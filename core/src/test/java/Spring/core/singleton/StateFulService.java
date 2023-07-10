package Spring.core.singleton;

public class StateFulService {
        
    private int price; //상태를 유지하는 필드 10000->20000으로 바꿔치기 됨
    
    public void order(String name, int price) {
        System.out.println("name = " + name + " " +"price = " +  price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
