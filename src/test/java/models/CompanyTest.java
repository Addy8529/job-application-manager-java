package models;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.net.URI;
import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    void CompanyConstructorAcceptsValidParameters(){
        try{
            assertNotNull( new Company(0,"IBM", "description", new URI("http://ibm.com"), 0) );
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

}
