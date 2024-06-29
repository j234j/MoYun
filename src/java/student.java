import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

// 用户类
class student extends User {
    Scanner scanner = new Scanner(System.in);
    private final int type = 1; // 用户属性
    private String username; // 用户名称
    private String password; // 用户密码
    private String Id; // 用户id
    private int level; // 用户等级
    private String reTime; //用户注册时间
    private double totalRead; // 用户总共阅读量
    private String phoneNumber; // 用户手机号
    private String email; // 用户邮箱
    private ArrayList<Book> userlibrary; // 个人书库
    private ArrayList<readHistory> readHistory; //浏览历史

    student() {
        super();
    }

    student(String username, String password,
            String Id, int level, String reTime,
            double totalCost, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.Id = Id;
        this.level = level;
        this.reTime = reTime;
        this.totalRead = totalCost;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setReTime(String reTime) {
        this.reTime = reTime;
    }

    public void setTotalRead(double totalRead) {
        this.totalRead = totalRead;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return Id;
    }

    public int getLevel() {
        return level;
    }

    public String getReTime() {
        return reTime;
    }

    public double getTotalRead() {
        return totalRead;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // 基本功能
    // 注册功能
    void register() {
        int i = 0;
        int flag = 0;

        String customerName = null;
        String password = null;
        String phone = null;
        String email = null;

        while (i <= 10 && flag < 4) {
            switch (flag) {
                case 0:
                    System.out.print("请输入用户名：");
                    customerName = scanner.nextLine();

                    if (customerName == null) {
                        System.out.println("用户名不能为空");
                    } else if (customerName.length() < 5) {
                        System.out.println("用户名长度必须不少于5个字符。");
                    } else {
                        flag++;
                    }
                    break;
                case 1:
                    System.out.print("请输入密码：");
                    password = scanner.nextLine();

                    if (isValidPassword(password)) {
                        System.out.println("密码长度必须大于8个字符，且必须是大小写字母、数字和标点符号的组合。");
                    } else {
                        flag++;
                    }
                    break;
                case 2:
                    System.out.print("请输入手机号码：");
                    phone = scanner.nextLine();

                    if (!isValidPhone(phone)) {
                        System.out.println("请输入正确的手机号码。");
                    } else {
                        flag++;
                    }
                    break;
                case 3:
                    System.out.print("请输入邮箱：");
                    email = scanner.nextLine();

                    if (!isValidEmail(email)) {
                        System.out.println("请输入正确的邮箱地址。");
                    } else {
                        flag++;
                    }
                    break;
            }

            i++;
        }

        if (flag == 4) {
            student newCustomer = new student();
            newCustomer.setUsername(customerName);
            newCustomer.setPassword(password);
            newCustomer.setId(Integer.toString(system.students.size() - 1));
            newCustomer.setLevel(0);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
            String formattedDate = currentTime.format(formatter);
            newCustomer.setReTime(formattedDate);
            newCustomer.setTotalRead(0.0);
            newCustomer.setPhoneNumber(phone);
            newCustomer.setEmail(email);
            system.students.add(newCustomer);
            System.out.println("注册成功！");
        }
    }
    // 验证密码是否满足要求
    static boolean isValidPassword(String password) {
        if (password == null || password.equals(""))
            return true;
        if (password.length() <= 8) {
            return true;
        }

        boolean hasLowercase = Pattern.compile("[a-z]").matcher(password).find();
        boolean hasUppercase = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasDigit = Pattern.compile("\\d").matcher(password).find();
        boolean hasPunctuation = Pattern.compile("[!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]").matcher(password).find();

        return !hasLowercase || !hasUppercase || !hasDigit || !hasPunctuation;
    }

    // 验证电子邮件格式是否有效
    boolean isValidEmail(String email) {
        // 此正则表达式用于简单检查电子邮件格式
        String regex = "^[A-Za-z\\d+_.-]+@(.+)$";
        return email.matches(regex);
    }

    // 验证手机号码格式是否有效
    boolean isValidPhone(String phone) {
        // 此正则表达式用于简单检查手机号码格式
        String regex = "^(\\d{11})$"; // 手机号码是11位数字
        return phone.matches(regex);
    }

    // 进入圈子功能
    void circle(student user) {
        System.out.print("请输入注册时使用的邮箱地址: ");
        String email = scanner.nextLine();
        boolean emailValid = checkEmailValidity(user.getUsername(), email);
        if (!emailValid) {
            System.out.println("请确认后再试。");
            return;
        }
        String newPassword = generateRandomPassword();
        updatePassword(newPassword);
        System.out.println("正在进入圈子，请稍侯...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("已进入圈子！");
    }

    // 检查邮箱地址是否正确
    private boolean checkEmailValidity(String username, String email) {
        boolean isValid = false;

        while (!isValid) {
            System.out.print("请输入邮箱地址: ");
            email = scanner.nextLine();

            String regex = "^[A-Za-z\\d+_.-]+@(.+)$";
            if (!email.matches(regex)) {
                System.out.println("邮箱格式错误!");
                continue;  // 重新开始循环
            }

            String userEmail = "";
            for (student cust : system.students) {
                if (cust.getUsername().equals(username)) {
                    userEmail = cust.getEmail();
                    break;
                }
            }

            if (!email.equals(userEmail)) {
                System.out.println("邮箱错误!");
                continue;  // 重新开始循环
            }

            isValid = true;  // 邮箱格式和匹配都正确，退出循环
        }

        return true;
    }

    // 生成随机密码
    private String generateRandomPassword() {
        // 生成随机密码的逻辑
        // 返回随机密码
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }



    // 密码管理
    // 用户-修改密码
    void changePassword() {
        System.out.print("请输入旧密码: ");
        String oldPassword;
        oldPassword = scanner.nextLine();
        // 检查旧密码是否匹配
        while (!checkOldPassword(oldPassword)) {
            System.out.println("旧密码输入错误。");
            System.out.print("请输入旧密码: ");
            oldPassword = scanner.nextLine();
        }
        // 输入新密码
        System.out.print("请输入新密码: ");
        String newPassword;
        newPassword = scanner.nextLine();
        // 检查新密码格式是否有效
        while (isValidPassword(newPassword)) {
            System.out.println("密码长度必须大于8个字符，且必须是大小写字母、数字和标点符号的组合。");
            System.out.print("请输入新密码: ");
            newPassword = scanner.nextLine();
        }
        // 确认新密码
        System.out.print("请确认新密码: ");
        String conPassword;
        conPassword = scanner.nextLine();
        while (!newPassword.equals(conPassword)) {
            System.out.println("请确认新密码: ");
            conPassword = scanner.nextLine();
        }
        // 更新密码
        updatePassword(newPassword);
        System.out.println("密码已成功修改. ");
    }

    // 检查旧密码是否匹配
    private boolean checkOldPassword(String oldPassword) {
        if (system.getPassword() != null) {
            return system.getPassword().equals(oldPassword);
        }
        return false;
    }

    // 更新密码
    private void updatePassword(String newPassword) {
        // 更新 ArrayList<student> 中的密码
        for (student cust : system.students) {
            if (cust.getUsername().equals(system.getUsername())) {
                cust.setPassword(newPassword);
                return;
            }
        }

        System.out.println("找不到匹配的用户。密码更新失败。");
    }

    // 用户-展示所有图书
    void displayLibrary() {
        if (system.Products == null) {
            System.out.println("书库信息获取失败!");
            return;
        }
        for (Book pro : system.Products) {
            System.out.println(pro);
        }
    }

    // 用户-展示个人书库
    void displayUserlibrary(student user) {
        if (user.userlibrary != null) {
            System.out.println("列表详情: ");
            for (int i = 0; i < user.userlibrary.size(); i++) {
                System.out.println(user.userlibrary.get(i).toString());
            }
        } else
            System.out.println("暂无");
    }

    // 用户-添加书评
    void add(student user) {
        // 显示列表供用户选择
        displayLibrary();
        System.out.print("请输入要添加的书评: ");
        String str;
        boolean flag = false;
        str = scanner.nextLine();
        int productIndex = Integer.parseInt(str);
        while (!flag) {
            if (str.equals("")) {
                System.out.println("输入不能为空!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = Integer.parseInt(str);
            } else if (!str.matches("\\d+")) {
                System.out.println("输入格式错误!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = Integer.parseInt(str);
            }
            // 验证商品编号是否有效
            else if (productIndex < 1 || productIndex >= system.Products.size()) {
                System.out.println("无效");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = Integer.parseInt(str);
            } else
                flag = true;
        }
        Book selectedProduct = system.Products.get(productIndex - 1);
        flag = false;
        int quantity;
        System.out.print("确认输入？: ");
        str = scanner.nextLine();
        quantity = Integer.parseInt(str);
        while (!flag) {
            if (str.equals("")) {
                System.out.println("输入不能为空!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                quantity = Integer.parseInt(str);
            }
            // 验证输入是否正确
            else if (!str.matches("\\d+")) {
                System.out.println("输入格式错误!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                quantity = Integer.parseInt(str);
            }
            //
            else if (quantity <= 0) {
                System.out.println("无效");
                System.out.print("请重输: ");
                str = scanner.nextLine();
                quantity = Integer.parseInt(str);
            } else
                flag = true;
        }
        // 创建个人书库
        if (user.userlibrary == null) {
            user.userlibrary = new ArrayList<>();
        }
        selectedProduct.setAmount(quantity);
        user.userlibrary.add(selectedProduct);
        System.out.println("已将信息添加到列表。");
        // 展示
        displayUserlibrary(user);
    }

    // 用户-删除书评
    void remove(student user) {
        displayUserlibrary(user);
        if (user.userlibrary == null || user.userlibrary.isEmpty()) {
            System.out.println("列表为空，无需删除。");
            return;
        }
        String str;
        boolean flag = false;
        int productIndex = 1;
        System.out.print("请输入要移除的书评序号: ");
        str = scanner.nextLine();
        while (!flag) {
            if (str.equals("")) {
                System.out.println("输入不能为空!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = scanner.nextInt();
            } else if (!str.matches("\\d+")) {
                System.out.println("输入格式错误!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = scanner.nextInt();
            }
            else if (productIndex < 1 || productIndex > user.userlibrary.size()) {
                System.out.println("无效的序号");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = scanner.nextInt();
            } else
                flag = true;
        }
        Book selectedProduct = user.userlibrary.get(productIndex - 1);
        // 提示用户确认移除操作
        System.out.print("确认要移除 " + selectedProduct.getName() + " 吗?(Y?): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("Y")) {
            // 执行移除操作
            user.userlibrary.remove(selectedProduct);
            if (user.userlibrary.isEmpty()) {
                user.userlibrary = null;
            }
            System.out.println("已从个人书库中移除 " + selectedProduct.getName() + "。");
        } else {
            System.out.println("取消移除操作。");
        }
    }

    // 用户-修改购物车中的商品
    void updateCartProduct(student customer) {
        // 显示购物车中的商品供用户选择
        if (customer.userlibrary == null || customer.userlibrary.isEmpty()) {
            System.out.println("购物车为空，无法修改商品数量。");
            return;
        }
        displayUserlibrary(customer);
        String str;
        boolean flag = false;
        int productIndex = 1;
        System.out.print("请输入要修改的商品编号: ");
        str = scanner.nextLine();
        while (!flag) {
            if (str.equals("")) {
                System.out.println("输入不能为空!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = scanner.nextInt();
            } else if (!str.matches("\\d+")) {
                System.out.println("输入格式错误!");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = scanner.nextInt();
            }
            // 验证商品编号是否有效
            else if (productIndex < 1 || productIndex > customer.userlibrary.size()) {
                System.out.println("无效的商品编号");
                System.out.print("请重新输入: ");
                str = scanner.nextLine();
                productIndex = scanner.nextInt();
            } else
                flag = true;
        }
        Book selectedProduct = customer.userlibrary.get(productIndex - 1);
        flag = false;
        int newQuantity; // 商品数量
        System.out.print("请输入要修改的商品数量: ");
        str = scanner.nextLine();
        newQuantity = Integer.parseInt(str);
        while (!flag) {
            if (str.equals("")) {
                System.out.println("输入不能为空!");
                System.out.print("请重输商品数量: ");
                str = scanner.nextLine();
                newQuantity = Integer.parseInt(str);
            }
            // 验证输入是否正确
            else if (!str.matches("\\d+")) {
                System.out.println("输入格式错误!");
                System.out.print("请重输商品数量: ");
                str = scanner.nextLine();
                newQuantity = Integer.parseInt(str);
            }
            // 验证商品数量是否有效
            else if (newQuantity <= 0) {
                System.out.println("商品数量必须大于0。将从购物车中移除该商品。");
                customer.userlibrary.remove(selectedProduct);
            } else {
                selectedProduct.setAmount(newQuantity);
                System.out.println("商品数量已更新。");
                flag = true;
            }
        }
    }


    // 用户-查看历史
    void viewHistory(student user) {
        if (user.readHistory == null || user.readHistory.isEmpty()) {
            System.out.println("没有浏览历史记录。");
            return;
        }
        System.out.println("浏览历史记录数量: " + user.readHistory.size());
        int i = 1;
        for (readHistory history : user.readHistory) {
            System.out.println("清单 [" + i + "] --");
            System.out.println("浏览时间: " + history.getTime());
            System.out.println("浏览的图书清单:");
            for (Total p : history.getProducts()) {
                System.out.println(p.getName() + " -- " + p.getQuantity());
            }
            i++;
        }
    }

    // 将历史记录写入文本中
    void writeHistory(readHistory history) {
        String shopHistory_path = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\readHistory.csv";
        // 更新文本中的商品信息
        StringBuilder result = new StringBuilder("");
        for (Total p : history.getProducts()) {
            result.append("\t").append(p.getName()).append(" -- ").append(p.getQuantity());
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(shopHistory_path, true))) {
            writer.println("用户[" + getUsername() + "]浏览记录: " +
                    history.getTime() + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
