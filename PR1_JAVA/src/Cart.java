import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    @Getter
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void clear() {
        products.clear();
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній.";
        }
        StringBuilder sb = new StringBuilder("Кошик містить:\n");
        for (Product product : products) {
            sb.append(" - Товар{id=").append(product.getId())
                    .append(", назва='").append(product.getName())
                    .append("', ціна=").append(product.getPrice())
                    .append(", категорія='").append(product.getCategory() != null ? product.getCategory().getName() : "Без категорії")
                    .append("'}\n");
        }
        sb.append("Загальна вартість: ").append(getTotalPrice());
        return sb.toString();
    }
}