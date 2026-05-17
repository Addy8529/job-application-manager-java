package com.mahad.jobmanager.models;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class CompanyTest {

    @Test
    void CompanyConstructorAcceptsValidParameters(){
        Company company = new Company(0,"IBM", "description", URI.create("http://ibm.com"), 0);

        assertAll(
            () -> assertNotNull(company),
            () -> assertEquals(0, company.getId()),
            () -> assertEquals("IBM", company.getName()),
            () -> assertEquals("description", company.getDescription()),
            () -> assertEquals(URI.create("http://ibm.com"), company.getUrl()),
            () -> assertEquals(0, company.getNumberOfEmployees())
        );

    }

    @Test
    void ConstructorRejectsInvalidId(){
        assertThrows( IllegalArgumentException.class , ()-> new Company(-1 ,"IBM", "description", URI.create("http://ibm.com"), 0));
    }

    @ParameterizedTest
    @ValueSource( strings = {"", "  "})
    void ConstructorRejectsInvalidName(String name){
        assertThrows( IllegalArgumentException.class , ()-> new Company(0  , name, "description", URI.create("http://ibm.com"), 0));
    }

    @Test
    void ConstructorRejectsNullName(){
        assertThrows( IllegalArgumentException.class , ()-> new Company(0  , null, "description", URI.create("http://ibm.com"), 0));
    }

    @ParameterizedTest
    @ValueSource( strings = {" IBM ", "Deloitte   ", " Good  Company"} )
    void ConstructorTrimsCompanyName( String name){

        String expected = name.trim();

        assertEquals( expected, new Company(0  , name, "description", URI.create("http://ibm.com"), 0).getName());
    }

    @ParameterizedTest
    @ValueSource( strings = {"", "  "})
    void ConstructorRejectsInvalidDescription(String description){
        assertThrows( IllegalArgumentException.class , ()-> new Company(0  , "IBM", description, URI.create("http://ibm.com"), 0));
    }

    @Test
    void ConstructorRejectsNullDescription(){
        assertThrows( IllegalArgumentException.class , ()-> new Company(0  , "IBM", null, URI.create("http://ibm.com"), 0));
    }

    @ParameterizedTest
    @ValueSource( strings = {" IBM ", "Deloitte   ", " Good  Company"} )
    void ConstructorTrimsDescription( String description){

        String expected = description.trim();

        assertEquals( expected, new Company(0  , "IBM", description, URI.create("http://ibm.com"), 0).getDescription());
    }

    @Test
    void ConstructorRejectsNullURL(){
        assertThrows( IllegalArgumentException.class, () -> new Company(0, "IBM", "description", null, 0));
    }

    @ParameterizedTest
    @ValueSource( strings = {"", "mailto:test@example.com", "www.com", "ibm.com"})
    void ConstructorRejectsInvalidUrl( String url){
        URI uri = URI.create(url);
        assertThrows( IllegalArgumentException.class, () -> new Company(0, "IBM", "description", uri, 0));
    }

    @ParameterizedTest
    @ValueSource( strings = {"http://www.ibm.com", "ws://www.ibm.com", "https://ibm.com"})
    void ConstructorAcceptsValidUrl( String url){
        URI uri = URI.create(url);
        assertEquals( uri, new Company(0, "IBM", "description", uri, 0).getUrl());
    }

    @Test
    void ConstructorRejectsInvalidNumberOfPeople(){
        assertThrows( IllegalArgumentException.class , ()-> new Company(0 ,"IBM", "description", URI.create("http://ibm.com"), -1));
    }

    @ParameterizedTest
    @ValueSource( ints = {0, 10 , 100})
    void ConstructorAcceptsValidNumberOfPeople(int n){
        assertEquals( n , new Company(0 ,"IBM", "description", URI.create("http://ibm.com"), n).getNumberOfEmployees());

    }

    @Test
    void settersUpdateMutableFields(){
        Company company = new Company(0, "IBM", "description", URI.create("http://ibm.com"), 10);

        company.setName("Apple");
        company.setDescription("hardware");
        company.setUrl(URI.create("https://apple.com"));
        company.setNumberOfEmployees(20);

        assertAll(
            () -> assertEquals("Apple", company.getName()),
            () -> assertEquals("hardware", company.getDescription()),
            () -> assertEquals(URI.create("https://apple.com"), company.getUrl()),
            () -> assertEquals(20, company.getNumberOfEmployees())
        );
    }

    @Test
    void equalityAndHashCodeUseIdOnly(){
        Company company = new Company(1, "IBM", "description", URI.create("http://ibm.com"), 10);
        Company sameId = new Company(1, "Apple", "hardware", URI.create("https://apple.com"), 20);
        Company otherId = new Company(2, "IBM", "description", URI.create("http://ibm.com"), 10);

        assertAll(
            () -> assertEquals(company, company),
            () -> assertEquals(company, sameId),
            () -> assertEquals(company.hashCode(), sameId.hashCode()),
            () -> assertNotEquals(company, otherId),
            () -> assertNotEquals(company, null),
            () -> assertNotEquals(company, "company")
        );
    }

    @Test
    void toStringContainsCoreFields(){
        Company company = new Company(1, "IBM", "description", URI.create("http://ibm.com"), 10);

        assertEquals("ID: 1 | Name: IBM | Url: http://ibm.com | Description: description", company.toString());
    }


}
