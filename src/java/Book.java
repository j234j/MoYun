// 图书类
class Book {
    private String Id; // t图书编号
    private String name; // 图书名称
    private String author; // 作者
    private String writerdata; // 写作日期
    private String type; // 版号
    private double pagenumber; // 页数
    private double borrownumber; // 借阅次数
    private int amount; // 数量

    Book(){}

    public Book(String id, String name, String author, String writeDate, String type,
                double pagenumber, double borrownumber, int amount) {
        this.Id = id;
        this.name = name;
        this.author = author;
        this.writerdata = writeDate;
        this.type = type;
        this.pagenumber = pagenumber;
        this.borrownumber = borrownumber;
        this.amount = amount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWriterdata() {
        return writerdata;
    }

    public void setWriterdata(String writerdata) {
        this.writerdata = writerdata;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(double pagenumber) {
        this.pagenumber = pagenumber;
    }

    public double number() {
        return borrownumber;
    }

    public void setRetPrice(double retPrice) {
        this.borrownumber = retPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void updateInfo(String name, String author, String writerdata, String type,
                           double pagenumber, double borrownumber, int amount) {
        this.name = name;
        this.author = author;
        this.writerdata = writerdata;
        this.type = type;
        this.pagenumber = pagenumber;
        this.borrownumber = borrownumber;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return String.format("编号: %-2s 图书名称: %-5s  作者: %-5s  写作日期: %-10s " +
                        "版号: %-15s  借阅次数: %-10.1f 数量: %-5d",
                Id, name, author, writerdata, type, borrownumber, amount);
    }
}