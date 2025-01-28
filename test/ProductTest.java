import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    Product item1;
    @BeforeEach
    void create(){
        item1 = new Product("000001","ball","Soccer Ball",20.00);

    }
    @Test
    void setIdNumber(){
        item1.setIdNumber("000002");
        assertEquals("000002",item1.getIdNumber());

    }
    @Test
    void setName(){
        item1.setname("bat");
        assertEquals("bat",item1.getname());

    }
    @Test
    void setDiscription(){
        item1.setdiscription("baseball bat");
        assertEquals("baseball bat",item1.getdiscription());

    }
    @Test
    void setPrice(){
        item1.setprice(20);
        assertEquals(20,item1.getprice());

    }


}
