import java.util.Scanner;
import java.util.regex.Pattern;

// 管理员
class administrator extends User {
    Scanner scanner = new Scanner(System.in);
    private final int type = 0; // 用户属性
    private String name; // 用户名称
    private String password; // 用户密码
    administrator(String username, String password){
        this.name = username;
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public String getPassword() {

        return password;
    }

    administrator() {

        super();
    }

    // 密码管理
        // 管理员-修改自身密码
    void changeAdminPassword() {
        System.out.print("请输入旧密码: ");
        String oldPassword = scanner.nextLine();

        // 检查旧密码是否匹配
        while (!checkOldPassword(oldPassword)) {
            System.out.println("旧密码输入错误。");
            System.out.print("请输入旧密码: ");
            oldPassword = scanner.nextLine();
        }

        String newPassword;
        do {
            // 输入新密码
            System.out.print("请输入新密码: ");
            newPassword = scanner.nextLine();

            // 检查新密码格式是否有效
            if (!isValidPassword(newPassword)) {
                System.out.println("密码长度必须大于8个字符，且必须是大小写字母、数字和标点符号的组合。");
                continue;
            }

            // 确认新密码
            System.out.print("请确认新密码: ");
            String conPassword = scanner.nextLine();
            if (!newPassword.equals(conPassword)) {
                System.out.println("密码不匹配，请重新输入。");
            } else {
                // 更新密码
                updatePassword(newPassword);
                System.out.println("密码已成功修改。");
                break;
            }
        } while (true);
    }

    private static Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~])[A-Za-z\\d!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]{9,}$");

    static boolean isValidPassword(String password) {
        if (password == null || password.equals(""))
            return true;

        return !passwordPattern.matcher(password).matches();
    }

    // 检查旧密码是否匹配
            private boolean checkOldPassword(String oldPassword) {
                if(system.getPassword() != null) {
                    return system.getPassword().equals(oldPassword);
                }
                return false;
            }
            // 更新密码
            private void updatePassword(String newPassword) {
                // 更新 ArrayList<customer> 中的密码
                for (administrator admin : system.administrators) {
                    if (admin.getName().equals(system.getUsername())) {
                        admin.setPassword(newPassword);
                        break;
                    }
                }
            }
        // 管理员-重置用户密码
        void resetCustomersPassword() {
            // 展示所有用户
            System.out.println("当前全部用户名称信息: ");
            int i = 1;
            for (student cust : system.students){
                System.out.println("user[" + i + "] ->" + cust.getUsername());
                i++;
            }
            System.out.print("请输入要重置密码的用户的用户名: ");
            String username = scanner.nextLine();
            boolean flag = false;
            for (student cust : system.students){
                if (cust.getUsername().equals(username)){
                    flag = true;
                    cust.setPassword("Ynu123456.");
                    System.out.println("修改成功!");
                    break;
                }
            }
            if (!flag)
                System.out.println("未找到该用户!");
        }

    // 用户管理
        // 管理员-列出所有用户信息
    void listAllUsers() {
        if (system.students == null || system.students.isEmpty()) {
            System.out.println("暂无用户信息");
            return;
        }

        // 展示所有用户
        System.out.println("当前全部用户名称信息: ");
        int i = 1;
        for (student cust : system.students) {
            System.out.println("User[" + i + "] ->" + cust);
            i++;
        }
    }

    // 管理员-删除用户信息
    void deleteCustomer() {
        // 展示所有用户
        System.out.println("当前全部用户名称信息:");
        int i = 1;
        for (student cust : system.students) {
            System.out.println("Customer[" + i + "] -> " + cust.getUsername());
            i++;
        }

        System.out.print("请输入要删除的用户的用户名: ");
        String username = scanner.nextLine();

        // 检查用户名是否存在
        boolean found = false;
        student userToDelete = null;
        for (student user : system.students) {
            if (user.getUsername().equals(username)) {
                found = true;
                userToDelete = user;
                break;
            }
        }

        if (found) {
            // 给出警告提示
            System.out.print("确定要删除用户 " + username + " 吗? (Y/N): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("Y")) {
                system.students.remove(userToDelete);
                System.out.println("用户已删除。");
            } else {
                System.out.println("删除操作已取消。");
            }
        } else {
            System.out.println("未找到用户名。");
        }
    }

    // 管理员-查询用户信息
    void searchCustomer() {
        System.out.println("请选择查询方式:");
        System.out.println("1. 根据ID查询");
        System.out.println("2. 根据用户名查询");
        System.out.println("3. 查询所有用户");
        System.out.print("请输入选项: ");

        String str = scanner.nextLine();
        while (!str.matches("[1-3]")) {
            System.out.print("无效选项，请重新输入: ");
            str = scanner.nextLine();
        }

        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1:
                System.out.print("请输入要查询的用户ID: ");
                String idQuery = scanner.nextLine();
                searchById(idQuery);
                break;
            case 2:
                System.out.print("请输入要查询的用户名: ");
                String usernameQuery = scanner.nextLine();
                searchByUsername(usernameQuery);
                break;
            case 3:
                searchAllUsers();
                break;
            default:
                System.out.println("无效选项...");
                searchCustomer();
                break;
        }
    }

    // 根据用户ID查找
            private static void searchById(String id) {
                for (student customer : system.students) {
                    if (customer.getId().equals(id)) {
                        System.out.println("查询结果:");
                        System.out.println(customer);
                        return;
                    }
                }
                System.out.println("未找到对应ID的用户。");
            }
            // 根据用户名称查找
            private static void searchByUsername(String username) {
                for (student customer : system.students) {
                    if (customer.getUsername().equals(username)) {
                        System.out.println("查询结果:");
                        System.out.println(customer);
                        return;
                    }
                }
                System.out.println("未找到对应用户名的用户。");
            }
            // 查找所有用户
            private static void searchAllUsers() {
                System.out.println("查询结果:");
                for (student customer : system.students) {
                    System.out.println(customer);
                }
            }
            // 规范话输入
            boolean normalized_Input(String str){
                if(str.matches("\\d+"))
                    return false;
                else {
                    System.out.println("输入不合规范");
                    return true;
                }
            }
    // 图书管理
        // 管理员-列出所有图书信息
        void listAllProducts(){
            if (system.students == null){
                System.out.println("暂无信息");
                return;
            }
            System.out.println("所有图书信息:");
            for(Book pro : system.Products){
                System.out.println(pro);
            }
        }
        // 管理员-添加图书信息
        void addProduct(){
            System.out.println("请输入图书信息:");
            System.out.print("图书编号: ");
            String id = scanner.nextLine();
            System.out.print("图书名称: ");
            String name = scanner.nextLine();
            System.out.print("作者: ");
            String author = scanner.nextLine();
            System.out.print("写作日期: ");
            String writeDate = scanner.nextLine();
            System.out.print("版号: ");
            String type = scanner.nextLine();
            System.out.print("页数: ");
            double pagenumber = scanner.nextDouble();
            System.out.print("借阅次数: ");
            double borrownumber = scanner.nextDouble();
            System.out.print("数量: ");
            int amount = scanner.nextInt();
            Book product = new Book(id, name, author, writeDate, type,
                    pagenumber, borrownumber, amount);
            system.Products.add(product);
            System.out.println("图书信息已添加。");
        }

        // 管理员-删除图书信息
        void deleteProduct(){
            System.out.print("请输入要删除的图书编号: ");
            String idToDelete = scanner.nextLine();
            Book targetProduct = null;
            for (Book product : system.Products) {
                if (product.getId().equals(idToDelete)) {
                    targetProduct = product;
                    break;
                }
            }
            if (targetProduct == null) {
                System.out.println("找不到指定编号的图书。");
                return;
            }
            System.out.println("您确定要删除以下图书吗？");
            System.out.println(targetProduct);
            System.out.print("请输入 'y' 确认删除，或其他任意键取消: ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                system.Products.remove(targetProduct);
                System.out.println("商品已删除。");
            } else {
                System.out.println("删除操作已取消。");
            }
        }
        // 管理员-查询商品信息
        void searchProduct() {
            System.out.println("1. 根据图书名称查询");
            System.out.println("2. 根据作者查询");
            System.out.println("3. 根据借阅次数查询");
            System.out.println("4. 组合查询");
            System.out.print("请选择查询方式:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除输入缓冲区中的换行符
            switch (choice) {
                case 1 -> {
                    System.out.print("请输入图书名称: ");
                    String nameQuery = scanner.nextLine();
                    searchByName(nameQuery);
                }
                case 2 -> {
                    System.out.print("请输入作者: ");
                    String producerQuery = scanner.nextLine();
                    searchByAuthor(producerQuery);
                }
                case 3 -> {
                    System.out.print("请输入借阅次数: ");
                    String priceRange = scanner.nextLine();
                    String[] prices = priceRange.split("-");
                    if (prices.length == 2) {
                        double minPrice = Double.parseDouble(prices[0]);
                        double maxPrice = Double.parseDouble(prices[1]);
                        searchByBorrowNumber(minPrice, maxPrice);
                    } else {
                        System.out.println("无效格式。");
                    }
                }
                case 4 -> {
                    System.out.print("请输入图书名称: ");
                    String nameQueryCombined = scanner.nextLine();
                    System.out.print("请输入作者: ");
                    String producerQueryCombined = scanner.nextLine();
                    System.out.print("请输入借阅次数: ");
                    double minPriceCombined = scanner.nextDouble();
                    searchCombined(nameQueryCombined, producerQueryCombined, minPriceCombined);
                }
                default -> System.out.println("无效的选项。");
            }
        }
            // 按照名称查询
            public static void searchByName(String name) {
                for (Book product : system.Products) {
                    if (product.getName().contains(name)) {
                        System.out.println(product);
                    }
                }
            }
            // 按照作者查询
            public static void searchByAuthor(String author) {
                for (Book product : system.Products) {
                    if (product.getAuthor().contains(author)) {
                        System.out.println(product);
                    }
                }
            }
            // 按照借阅次数查询
            public static void searchByBorrowNumber(double min, double max) {
                for (Book product : system.Products) {
                    if (product.number() >= min && product.number() <= max) {
                        System.out.println(product);
                    }
                }
            }
            // 按照多约束查询
            public static void searchCombined(String name, String author, double number) {
                for (Book product : system.Products) {
                    if (product.getName().contains(name) && product.getAuthor().contains(author)
                            && product.number() >= number) {
                        System.out.println(product);
                    }
                }
            }
}