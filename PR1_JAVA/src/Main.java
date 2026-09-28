import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Створення товарів
        List<Product> catalog = new ArrayList<>();
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        catalog.add(product1);
        catalog.add(product2);
        catalog.add(product3);

        Cart cart = new Cart();
        List<Order> orderHistory = new ArrayList<>();

        while (true) {
            System.out.println("\n ВИБЕРІТЬ ОПЦІЮ");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Видалити товар з кошика");
            System.out.println("4 - Переглянути кошик");
            System.out.println("5 - Зробити замовлення");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товару (за назвою або категорією)");
            System.out.println("0 - Вийти");

            System.out.print("Ваш вибір: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nКаталог товарів:");
                    for (Product p : catalog) {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.print("Введіть ID товару для додавання до кошика: ");
                    int addId = scanner.nextInt();
                    Product productToAdd = findProductById(catalog, addId);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар успішно додано до кошика!");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній, нічого видаляти.");
                    } else {
                        System.out.println(cart);
                        System.out.print("Введіть ID товару, який потрібно видалити з кошика: ");
                        int removeId = scanner.nextInt();
                        Product productToRemove = findProductInCart(cart, removeId);
                        if (productToRemove != null) {
                            cart.removeProduct(productToRemove);
                            System.out.println("Товар видалено з кошика.");
                        } else {
                            System.out.println("У кошику немає товару з таким ID.");
                        }
                    }
                    break;

                case 4:
                    System.out.println(cart);
                    break;

                case 5:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        orderHistory.add(order);
                        System.out.println("Замовлення успішно оформлено!");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;

                case 6:
                    if (orderHistory.isEmpty()) {
                        System.out.println("Історія замовлень порожня.");
                    } else {
                        System.out.println("\nІсторія ваших замовлень:");
                        for (int i = 0; i < orderHistory.size(); i++) {
                            System.out.println("Замовлення #" + (i + 1) + ":");
                            System.out.println(orderHistory.get(i));
                            System.out.println("**************************1");
                        }
                    }
                    break;

                case 7:
                    System.out.print("Введіть ключове слово для пошуку (назва або категорія): ");
                    String keyword = scanner.nextLine().toLowerCase();
                    boolean found = false;
                    System.out.println("\nРезультати пошуку:");
                    for (Product p : catalog) {
                        if (p.getName().toLowerCase().contains(keyword) ||
                                p.getCategory().getName().toLowerCase().contains(keyword)) {
                            System.out.println(p);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Нічого не знайдено за вашим запитом.");
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }

    private static Product findProductById(List<Product> catalog, int id) {
        for (Product p : catalog) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private static Product findProductInCart(Cart cart, int id) {
        for (Product p : cart.getProducts()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}