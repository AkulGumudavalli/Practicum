import java.util.ArrayList;
import java.util.List;

public class Human {
    private String IdNumber;
    private String firstName;
    private String lastName;
    private String title;
    private int YOB;

    public Human(String idNumber, String firstName, String lastName, String title,int YOB) {
        this.IdNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.YOB = YOB;
    }
    public String fullName(){
        return firstName +" "+lastName;
    }
    public String formalName(){
        return title +". "+fullName();
    }
    public String getAge(){
        return String.valueOf( 2024-YOB);
    }
    public String getAge(int year){
        return String.valueOf(year-YOB);
    }
    public List<String> toCSV(){
        List<String> returnStuff = new ArrayList<>();
        returnStuff.add(IdNumber);
        returnStuff.add(firstName);
        returnStuff.add(lastName);
        returnStuff.add(title);
        returnStuff.add(String.valueOf(YOB));
        return returnStuff;

    }
    public String toJson() {
        return "{\n" +
                "  \"Id\": \"" + IdNumber + "\",\n" +
                "  \"FirstName\": \"" + firstName + "\",\n" +
                "  \"LastName\": \"" + lastName + "\",\n" +
                "  \"Title\": \"" + title + "\",\n" +
                "  \"YearOfBirth\": " + YOB + "\n" +
                "}";
    }
    public String toXML() {

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<person>\n" +
                "  <Id>" + IdNumber + "</Id>\n" +
                "  <FirstName>" + firstName + "</FirstName>\n" +
                "  <LastName>" + lastName + "</LastName>\n" +
                "  <Title>" + title + "</Title>\n" +
                "  <YearOfBirth>" + YOB + "</YearOfBirth>\n" +
                "</person>";
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getYOB() {
        return YOB;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYOB(int YOB) {
        this.YOB = YOB;
    }
}
