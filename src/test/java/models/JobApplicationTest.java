package models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class JobApplicationTest {

    @Test
    void ConstructorReturnsValidObject(){

        assertNotNull(new JobApplication(0, 0, "Software Developer", JobType.FULL_TIME));

    }

    @Test
    void ConstructorRejectsInvalidId(){
        assertThrows( IllegalArgumentException.class, () -> new JobApplication(-1, 0, "Software Developer", JobType.FULL_TIME));
    }

    @Test
    void ConstructorRejectsInvalidCompanyId(){
        assertThrows( IllegalArgumentException.class, () -> new JobApplication(0 , -1 , "Software Developer", JobType.FULL_TIME));
    }

    @Test
    void ConstructorRejectsNullTitel(){
        assertThrows( IllegalArgumentException.class, () -> new JobApplication(0 , 0 , null, JobType.FULL_TIME));
    }
}
