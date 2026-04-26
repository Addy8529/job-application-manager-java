package models;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
            Company company = new Company(0,"IBM", "description", new URI("http://ibm.com"), 0);
            assertAll(
                () -> assertNotNull(company),
                () -> assertEquals(0, company.getId()),
                () -> assertEquals("IBM", company.getName()),
                () -> assertEquals("description", company.getDescription()),
                () -> assertEquals(new URI("http://ibm.com"), company.getUrl()),
                () -> assertEquals(0, company.getNumberOfEmployees())

            );

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

    @Test
    void ConstructorRejectsNullName(){
        try{
            assertThrows( IllegalArgumentException.class , ()-> new Company(0  , null, "description", new URI("http://ibm.com"), 0));
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource( strings = {" IBM ", "Deloitte   ", " Good  Company"} )
    void ConstructorTrimsCompanyName( String name){

        String expected = name.trim();

        try{
            assertEquals( expected, new Company(0  , name, "description", new URI("http://ibm.com"), 0).getName());
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource( strings = {"", "  "})
    void ConstructorRejectsInvalidDescription(String description){
        try{
            assertThrows( IllegalArgumentException.class , ()-> new Company(0  , "IBM", description, new URI("http://ibm.com"), 0));
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

    @Test
    void ConstructorRejectsNullDescription(){
        try{
            assertThrows( IllegalArgumentException.class , ()-> new Company(0  , "IBM", null, new URI("http://ibm.com"), 0));
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource( strings = {" IBM ", "Deloitte   ", " Good  Company"} )
    void ConstructorTrimsDescription( String description){

        String expected = description.trim();

        try{
            assertEquals( expected, new Company(0  , "IBM", description, new URI("http://ibm.com"), 0).getName());
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }
    }

    @Test
    void ConstructorRejectsNullURL(){
        assertThrows( IllegalArgumentException.class, () -> new Company(0, "IBM", "description", null, 0));
    }

    @ParameterizedTest
    @ValueSource( strings = {"", "http://", "www.com", "ibm.com"})
    void ConstructorRejectsInvalidUrl( String url){

        try{
            URI uri = new URI(url);
            assertThrows( IllegalArgumentException.class, () -> new Company(0, "IBM", "description", uri, 0));
        }catch ( Exception e){
            System.out.println("External caused by URI: "+ e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource( strings = {"http://www.ibm.com", "ws://www.ibm.com", "https://ibm.com"})
    void ConstructorAcceptsValidUrl( String url){

        try{
            URI uri = new URI(url);
            assertEquals( uri, new Company(0, "IBM", "description", uri, 0).getUrl());
        }catch ( Exception e){
            System.out.println("External caused by URI: "+ e.getMessage());
        }
    }

    @Test
    void ConstructorRejectsInvalidNumberOfPeople(){
        try{
            assertThrows( IllegalArgumentException.class , ()-> new Company(0 ,"IBM", "description", new URI("http://ibm.com"), -1));
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource( ints = {0, 10 , 100})
    void ConstructorAcceptsValidNumberOfPeople(int n){
        try{
            assertEquals( n , new Company(0 ,"IBM", "description", new URI("http://ibm.com"), n).getNumberOfEmployees());
        }catch(Exception e){
            System.out.println("Caused by URI class: "+ e.getMessage());
        }

    }


}
