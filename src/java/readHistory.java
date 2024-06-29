import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class readHistory {
    private String time; // 时间
    private ArrayList<Total> products; // 购买的商品清单
    public readHistory(ArrayList<Book> products) {
        this.products = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = dateFormat.format(new Date());

        for (Book p : products) {
            Total purchase = new Total(p.getName(), p.getAmount());
            this.products.add(purchase); // 存储购买信息
        }
    }
    public String getTime() {
        return time;
    }
    public ArrayList<Total> getProducts() {
        return products;
    }
}
class Total {
    private String name;
    private int quantity;

    public Total(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}