import java.util.Scanner;
// 用户类
class User {
    Scanner scanner = new Scanner(System.in);
    // 用户属性: 0为管理员, 1为学生，2为老师
    private int type; // 用户属性
    private String username; // 用户名
    private String password; // 用户密码

    public void setUsername(String username) {

        this.username = username;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    void login() {
        String UserName;
        System.out.print("请输入用户名: ");
        UserName = scanner.nextLine();
        while (UserName == null || UserName.equals("")) {
            System.out.println("用户名不能为空!");
            System.out.print("请重输用户名: ");
            UserName = scanner.nextLine();
        }

        int i=0;
        for(;i<5;i++) {
            throwErrorException(i);
            System.out.print("请输入密码: ");
            String password = scanner.nextLine();
            if (!checkInput(UserName, password)) {
                System.out.println("用户名或密码错误，请重试.");
            }
            else {
                break;
            }
        }
        throwErrorException(i);
    }
    // 用户登录-信息核对
    private boolean checkInput(String username, String password) {
        Menu Moyun = new Menu();
        for (student user : system.students) {
            if (user.getUsername().trim().equals(username.trim()) && user.getPassword().equals(password)) {
                System.out.println("欢迎！");
                system.setUsername(username);
                system.setPassword(password);
                Moyun.students_menu();
                return true;
            }
        }

        /*for (teacher user : system.teachers) {
            if (user.getUsername().trim().equals(username.trim()) && user.getPassword().equals(password)) {
                System.out.println("欢迎！");
                system.setUsername(username);
                system.setPassword(password);
                Moyun.teachers_menu();
                return true;
            }
        }*/


        for (administrator admin : system.administrators) {
            if (admin.getName().equals(username) && admin.getPassword().equals(password)) {
                System.out.println("欢迎，管理员！");
                system.setUsername(username);
                system.setPassword(password);
                Moyun.administrator_menu();
                return true;
            }
        }

        return false;
    }

    // 用户登录-次数监测
    private void throwErrorException(int i) {
        if (i == 3) {
            System.out.println("您已经连续错误三次，还剩最后两次机会！");
        }
        else if (i == 5) {
            System.out.println("账号已冻结。");
            System.exit(-1);
        }
    }


}
