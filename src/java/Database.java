import java.io.*;
import java.util.ArrayList;

public class Database {
    static String adminPath = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\administrator.csv";
    static String studentPath = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\student.csv";
    static String libraryPath = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\library.csv";
    static String teacherPath = "C:\\Users\\Lenovo\\OneDrive\\文档\\Tencent Files\\565950870\\FileRecv\\MoYun1.0\\src\\excel\\teacher.csv";

    static ArrayList<administrator> readAdministrators() {
        ArrayList<administrator> administrators = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(adminPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] adminInfo = line.split(",");
                    String name = adminInfo[0];
                    String password = adminInfo[1];
                    administrators.add(new administrator(name, password));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return administrators;
    }

    static ArrayList<student> readStudents() {
        ArrayList<student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(studentPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] custInfo = line.trim().split(",");
                String name = custInfo[0];
                String password = custInfo[1];
                String id = custInfo[2];
                int level = Integer.parseInt(custInfo[3]);
                String time = custInfo[4];
                double expense = Double.parseDouble(custInfo[5]);
                String phone = custInfo[6];
                String email = custInfo[7];
                students.add(new student(name, password, id, level, time, expense, phone, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    static ArrayList<teacher> readTeachers() {
        ArrayList<teacher> teachers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(teacherPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] custInfo = line.trim().split(",");
                String name = custInfo[0];
                String password = custInfo[1];
                String id = custInfo[2];
                int level = Integer.parseInt(custInfo[3]);
                String time = custInfo[4];
                double expense = Double.parseDouble(custInfo[5]);
                String phone = custInfo[6];
                String email = custInfo[7];
                teachers.add(new teacher(name, password, id, level, time, expense, phone, email));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    static ArrayList<Book> readLibrary() {
        ArrayList<Book> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(libraryPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.trim().split(",");
                String id = fields[0];
                String name = fields[1];
                String producer = fields[2];
                String produceTime = fields[3];
                String type = fields[4];
                double purchasePrice = Double.parseDouble(fields[5]);
                double retailPrice = Double.parseDouble(fields[6]);
                int amount = Integer.parseInt(fields[7]);

                Book newProduct = new Book();
                newProduct.setId(id);
                newProduct.setName(name);
                newProduct.setAuthor(producer);
                newProduct.setWriterdata(produceTime);
                newProduct.setType(type);
                newProduct.setPagenumber(purchasePrice);
                newProduct.setRetPrice(retailPrice);
                newProduct.setAmount(amount);
                products.add(newProduct);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}
