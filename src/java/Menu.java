import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
// 菜单类
class Menu {
    Scanner scanner = new Scanner(System.in);
    // 初级菜单
    void main_menu(){
        User user = new User();
        student student = new student();
        System.out.println("当前您在:\n        <主页>");
        System.out.println("······ 开始菜单 ······");
        System.out.println("\t\t1.注册");
        System.out.println("\t\t2.登录");
        System.out.println("\t\t3.退出");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {student.register();
            user.login();}
            case 2 -> {
                user.login();
            }
            case 3 -> {
                System.out.println("正在退出系统...");
                saveExcel();
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            default -> System.out.println("错误,请重新输入.");
        }
    }
    // 二级菜单
    // 用户菜单
    void teachers_menu(){
        student teacher= new student(system.getUsername(),system.getPassword());
        System.out.println("当前您在:\n        <二级>");
        System.out.println("······ 导师菜单 ······");
        System.out.println("\t 1.浏览书库");
        System.out.println("\t 2.进入圈子");
        System.out.println("\t 3.修改密码");
        System.out.println("\t 4.退出登录");
        System.out.print("请输入选项: ");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                teacher_menu1(teacher);
                teachers_menu();
            }
            case 2 -> {
                teacher.circle(teacher);
                teachers_menu();
            }
            case 3 -> {
                teacher.changePassword();
                teachers_menu();
                teacher_menu1(teacher);
                teachers_menu();
            }
            case 4 -> {
                System.out.println("正在退出登录...");
                main_menu();
            }
            default -> {
                System.out.println("无效选项...");
                teachers_menu();
            }
        }
    }

    //学生菜单
    // 二级菜单
    // 用户菜单
    void students_menu(){
        student student= new student(system.getUsername(),system.getPassword());
        System.out.println("当前您在:\n        <二级>");
        System.out.println("······ 学生菜单 ······");
        System.out.println("\t 1.进入书库");
        System.out.println("\t 2.进入圈子");
        System.out.println("\t 3.修改密码");
        System.out.println("\t 4.退出登录");
        System.out.print("请输入选项: ");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                student_menu1(student);
                teachers_menu();
            }
            case 2 -> {
                student.circle(student);
                teachers_menu();
            }
            case 3 -> {
                student.changePassword();
                teachers_menu();
                teacher_menu1(student);
                teachers_menu();
            }
            case 4 -> {
                System.out.println("正在退出登录...");
                main_menu();
            }
            default -> {
                System.out.println("无效选项...");
                teachers_menu();
            }
        }
    }
    // 管理员菜单
    void administrator_menu(){
        administrator administrator = new administrator();
        System.out.println("当前您在:\n        <二级>");
        System.out.println("······ 管理员菜单 ······");
        System.out.println("\t  1.密码管理");
        System.out.println("\t  2.用户管理");
        System.out.println("\t  3.书库管理");
        System.out.println("\t  4.退出登录");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                passwordManagement_menu(administrator);
                administrator_menu();
            }
            case 2 -> {
                customersManagement_menu(administrator);
                administrator_menu();
            }
            case 3 -> {
                productsManagement_menu(administrator);
                administrator_menu();
            }
            case 4 -> {
                System.out.println("正在退出登录...");
                main_menu();
            }
            default -> {
                System.out.println("无效选项...");
                administrator_menu();
            }
        }
    }
    // 三级菜单
    // 管理员-1.密码管理菜单
    void passwordManagement_menu(administrator admin){
        System.out.println("当前您在:\n        <三级>");
        System.out.println("······ 管理员菜单 ······");
        System.out.println("\t 1.修改自身密码");
        System.out.println("\t 2.重置用户密码");
        System.out.println("\t 3.返回上一级菜单");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                admin.changeAdminPassword();
                passwordManagement_menu(admin);
            }
            case 2 -> {
                admin.resetCustomersPassword();
                passwordManagement_menu(admin);
            }
            case 3 -> administrator_menu();
            default -> {
                System.out.println("无效选项...");
                passwordManagement_menu(admin);
            }
        }
    }
    // 管理员-2.用户管理菜单
    void customersManagement_menu(administrator admin){
        System.out.println("当前您在:\n        <三级>");
        System.out.println("······ 管理员菜单 ······");
        System.out.println("\t 1.列出所有用户信息");
        System.out.println("\t 2.删除用户信息");
        System.out.println("\t 3.查询用户信息");
        System.out.println("\t 4.返回上一级菜单");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                admin.listAllUsers();
                customersManagement_menu(admin);
            }
            case 2 -> {
                admin.deleteCustomer();
                customersManagement_menu(admin);
            }
            case 3 -> {
                admin.searchCustomer();
                customersManagement_menu(admin);
            }
            case 4 -> administrator_menu();
            default -> {
                System.out.println("无效选项...");
                customersManagement_menu(admin);
            }
        }
    }
    // 管理员-3.商品管理菜单
    void productsManagement_menu(administrator admin){
        System.out.println("当前您在:\n        <三级>");
        System.out.println("······ 管理员菜单 ······");
        System.out.println("\t 1.列出书库信息");
        System.out.println("\t 2.添加图书");
        System.out.println("\t 3.修改图书");
        System.out.println("\t 4.删除信息");
        System.out.println("\t 5.查询信息");
        System.out.println("\t 6.返回上一级菜单");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请重新输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                admin.listAllProducts();
                productsManagement_menu(admin);
            }
            case 2 -> {
                admin.addProduct();
                productsManagement_menu(admin);
            }

            case 4 -> {
                admin.deleteProduct();
                productsManagement_menu(admin);
            }
            case 5 -> {
                admin.searchProduct();
                productsManagement_menu(admin);
            }
            case 6 -> administrator_menu();
            default -> {
                System.out.println("无效选项...");
                productsManagement_menu(admin);
            }
        }
    }
    // 用户-购物菜单
    void teacher_menu1(student teacher){
        System.out.println("当前您在:\n        <三级>");
        System.out.println("······ 导师菜单 ······");
        System.out.println("\t 1.查看书库");
        System.out.println("\t 2.浏览图书");
        System.out.println("\t 3.查看信息");
        System.out.println("\t 4.发送信息");
        System.out.println("\t 5.查看列表");
        System.out.println("\t 6.统计");
        System.out.println("\t 7.查看历史浏览信息");
        System.out.println("\t 8.返回上级菜单");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请正确输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                teacher.displayLibrary();
                teacher_menu1(teacher);
            }
            case 2 -> {
                teacher.add(teacher);
                teacher_menu1(teacher);
            }
            case 3 -> {
                teacher.remove(teacher);
                teacher_menu1(teacher);
            }
            case 4 -> {
                teacher.updateCartProduct(teacher);
                teacher_menu1(teacher);
            }
            case 5 -> {
                teacher.displayUserlibrary(teacher);
                teacher_menu1(teacher);
            }
            case 7 -> {
                teacher.viewHistory(teacher);
                teacher_menu1(teacher);
            }
            case 8 -> teachers_menu();
            default -> {
                System.out.println("无效选项...");
                teacher_menu1(teacher);
            }
        }
    }

    void student_menu1(student student){
        System.out.println("当前您在:\n        <三级>");
        System.out.println("······ 学生菜单 ······");
        System.out.println("\t 1.查看书库");
        System.out.println("\t 2.添加书评");
        System.out.println("\t 3.查看信息");
        System.out.println("\t 4.发送信息");
        System.out.println("\t 5.查看借阅列表");
        System.out.println("\t 6.统计");
        System.out.println("\t 7.查看历史浏览信息");
        System.out.println("\t 8.返回上级菜单");
        System.out.print("请输入选项:");
        String str = scanner.nextLine();
        while(normalized_Input(str)){
            System.out.print("请正确输入选项:");
            str = scanner.nextLine();
        }
        int choice = Integer.parseInt(str);
        switch (choice) {
            case 1 -> {
                student.displayLibrary();
                student_menu1(student);
            }
            case 2 -> {
                student.add(student);
                student_menu1(student);
            }
            case 3 -> {
                student.remove(student);
                student_menu1(student);
            }
            case 4 -> {
                student.updateCartProduct(student);
                student_menu1(student);
            }
            case 5 -> {
                student.displayUserlibrary(student);
                student_menu1(student);
            }
            case 7 -> {
                student.viewHistory(student);
                student_menu1(student);
            }
            case 8 -> students_menu();
            default -> {
                System.out.println("无效选项...");
                student_menu1(student);
            }
        }
    }
    // 输入规范化判定
    boolean normalized_Input(String str){
        if(str.matches("\\d+"))
            return false;
        else {
            System.out.println("输入不合规范");
            return true;
        }
    }
    // 写入文本
    void saveExcel(){
        String product_path = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\library.csv";
        String admin_path = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\administrator.csv";
        String student_path = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\student.csv";
        String teacher_path = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\teacher.csv";

        // 更新文本中的信息
        try (PrintWriter writer = new PrintWriter(new FileWriter(product_path))) {
            for (Book product : system.Products) {
                writer.println(product.getId() + "," + product.getName() + "," + product.getAuthor() +
                        "," + product.getWriterdata() + "," + product.getType() + "," + product.getPagenumber() +
                        "," + product.number() + "," + product.getAmount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 更新文本中的用户信息
        try (PrintWriter writer = new PrintWriter(new FileWriter(admin_path))) {
            for (administrator admin : system.administrators) {
                writer.println(admin.getName() + "," + admin.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 更新文本中的用户信息
        try (PrintWriter writer = new PrintWriter(new FileWriter(student_path))) {
            for(student customer : system.students) {
                writer.println(customer.getUsername() + "," + customer.getPassword() + "," +
                        customer.getId() + "," + customer.getLevel() + "," + customer.getReTime() + "," +
                        customer.getTotalRead() + "," + customer.getPhoneNumber() + "," + customer.getEmail());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
