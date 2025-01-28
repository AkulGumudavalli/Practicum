import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PersonTest {

    Human p1,p2,p3,p4,p5;
    @BeforeEach
    void setUp(){
         p1 = new Human("000001", "Akul", "Gumudavalli", "Mr.", 2009);
         p2 = new Human("000002", "Emma", "Smith", "Ms.", 1995);
         p3 = new Human("000003", "Liam", "Johnson", "Mr.", 1987);
         p4 = new Human("000004", "Sophia", "Williams", "Mrs.", 2000);
         p5 = new Human("000005", "Noah", "Brown", "Dr.", 1978);
    }
    @Test
    void setFirstName(){
        p1.setFirstName("Anakin");
        assertEquals("Anakin",p1.getFirstName());
    }
    @Test
    void setLastName(){
        p2.setLastName("SkyWaker");
        assertEquals("SkyWaker",p2.getLastName());

    }
    @Test
    void setIdNumber(){
        p3.setIdNumber("000001");
        assertEquals("000001",p3.getIdNumber());
    }
    @Test
    void setTitle(){
        p3.setTitle("Mr.");
        assertEquals("Mr.",p3.getTitle());
    }
    @Test
    void setYOB(){
        p3.setYOB(2005);
        assertEquals(2005,p3.getYOB());
    }
}
