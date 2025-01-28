import java.util.ArrayList;
import java.util.List;

public class Product {
    private String IdNumber;
    private String name;
    private String discription;
    private double price;

    public Product(String idNumber, String name, String discription,double price) {
        this.IdNumber = idNumber;
        this.name = name;
        this.discription = discription;
        this.price = price;
    }
    public String fullName(){
        return name +" "+discription;
    }
    public String getAge(){
        return String.valueOf( 2024-price);
    }
    public String getAge(int year){
        return String.valueOf(year-price);
    }
    public List<String> toCSV(){
        List<String> returnStuff = new ArrayList<>();
        returnStuff.add(IdNumber);
        returnStuff.add(name);
        returnStuff.add(discription);
        returnStuff.add(String.valueOf(price));
        return returnStuff;

    }
    public String toJson() {
        return "{\n" +
                "  \"Id\": \"" + IdNumber + "\",\n" +
                "  \"Item Name\": \"" + name + "\",\n" +
                "  \"Discription\": \"" + discription + "\",\n" +
                "  \"Price\": " + price + "\n" +
                "}";
    }
    public String toXML() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<person>\n" +
                "  <Id>" + IdNumber + "</Id>\n" +
                "  <Item Name>" + name + "</Item Name>\n" +
                "  <Discription>" + discription + "</Discription>\n" +
                "  <Price>" + price + "</Price>\n" +
                "</person>";
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public String getname() {
        return name;
    }

    public String getdiscription() {
        return discription;
    }


    public double getprice() {
        return price;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setdiscription(String discription) {
        this.discription = discription;
    }


    public void setprice(int price) {
        this.price = price;
    }
}
