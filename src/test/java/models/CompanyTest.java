package models;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CompanyTest {

    @Test
    void CompanyConstructorAcceptsValidParameters(){
        try{
            assertNotNull( new Company(0,"IBM", "description", new URI("http://ibm.com"), 0) );
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

    @Test
    void ConstructorRejectsInvalidId(){
        try{
            assertThrows( IllegalArgumentException.class , ()-> new Company(-1 ,"IBM", "description", new URI("http://ibm.com"), 0));
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource( strings = {"", "  "})
    void ConstructorRejectsInvalidName(String name){
        try{
            assertThrows( IllegalArgumentException.class , ()-> new Company(0  , name, "description", new URI("http://ibm.com"), 0));
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

}
