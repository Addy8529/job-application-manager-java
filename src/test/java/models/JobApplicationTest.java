package models;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class JobApplicationTest {

    @Test
    void ConstructorReturnsValidObject(){

        assertNotNull(new JobApplication(0, 0, "Software Developer", JobType.FULL_TIME));

    }
}
