package com.mahad.jobmanager.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class JobApplicationTest {

    @Test
    void ConstructorReturnsValidObjectWithDefaultValues(){
        LocalDate today = LocalDate.now();
        JobApplication application = new JobApplication(
            0,
            0,
            "Software Developer",
            JobType.FULL_TIME
        );

        assertAll(
            () -> assertNotNull(application),
            () -> assertEquals(0, application.getId()),
            () -> assertEquals(0, application.getCompanyId()),
            () -> assertEquals("Software Developer", application.getRoleTitle()),
            () -> assertEquals(JobType.FULL_TIME, application.getJobType()),
            () -> assertEquals(ApplicationStatus.APPLIED, application.getStatus()),
            () -> assertEquals(today, application.getDateApplied()),
            () -> assertEquals(today.plusDays(7), application.getFollowUpDate()),
            () -> assertEquals("No notes.", application.getNotes())
        );

    }

    @Test
    void FullConstructorReturnsValidObject(){
        LocalDate applied = LocalDate.of(2026, 5, 1);
        LocalDate followUp = LocalDate.of(2026, 5, 8);

        JobApplication application = new JobApplication(
            0,
            1,
            " Backend Developer ",
            JobType.PART_TIME,
            ApplicationStatus.INTERVIEW,
            applied,
            followUp,
            " follow up by email "
        );

        assertAll(
            () -> assertEquals(0, application.getId()),
            () -> assertEquals(1, application.getCompanyId()),
            () -> assertEquals("Backend Developer", application.getRoleTitle()),
            () -> assertEquals(JobType.PART_TIME, application.getJobType()),
            () -> assertEquals(ApplicationStatus.INTERVIEW, application.getStatus()),
            () -> assertEquals(applied, application.getDateApplied()),
            () -> assertEquals(followUp, application.getFollowUpDate()),
            () -> assertEquals("follow up by email", application.getNotes())
        );
    }

    @Test
    void ConstructorRejectsInvalidId(){
        assertThrows(
            IllegalArgumentException.class,
            () -> new JobApplication(
                -1,
                0,
                "Software Developer",
                JobType.FULL_TIME
            )
        );
    }

    @Test
    void ConstructorRejectsInvalidCompanyId(){
        assertThrows(
            IllegalArgumentException.class,
            () -> new JobApplication(
                0 ,
                -1 ,
                "Software Developer",
                JobType.FULL_TIME
            )
        );
    }

    @Test
    void ConstructorRejectsNullTitle(){
        assertThrows(
             IllegalArgumentException.class,
            () -> new JobApplication(
                0 ,
                0 ,
                null,
                JobType.FULL_TIME
            )
        );
    }

    @Test
    void ConstructorRejectsNullJobType(){
        assertThrows(
            IllegalArgumentException.class,
            () -> new JobApplication(
                0,
                0,
                "Software Developer",
                null
            )
        );
    }

    @Test
    void FullConstructorRejectsNullDateApplied(){
        assertThrows(
            IllegalArgumentException.class,
            () -> new JobApplication(
                0,
                0,
                "Software Developer",
                JobType.FULL_TIME,
                ApplicationStatus.APPLIED,
                null,
                LocalDate.now(),
                "notes"
            )
        );
    }

    @Test
    void FullConstructorRejectsFollowUpBeforeDateApplied(){
        LocalDate applied = LocalDate.of(2026, 5, 10);

        assertThrows(
            IllegalArgumentException.class,
            () -> new JobApplication(
                0,
                0,
                "Software Developer",
                JobType.FULL_TIME,
                ApplicationStatus.APPLIED,
                applied,
                applied.minusDays(1),
                "notes"
            )
        );
    }

    @Test
    void FullConstructorDefaultsNullableFields(){
        LocalDate applied = LocalDate.of(2026, 5, 10);

        JobApplication application = new JobApplication(
            0,
            0,
            "Software Developer",
            JobType.FULL_TIME,
            null,
            applied,
            null,
            " "
        );

        assertAll(
            () -> assertEquals(ApplicationStatus.APPLIED, application.getStatus()),
            () -> assertEquals(applied.plusDays(7), application.getFollowUpDate()),
            () -> assertEquals("No notes.", application.getNotes())
        );
    }

    @ParameterizedTest
    @ValueSource( strings = {"", "  ","   "})
    void ConstructorRejectsEmptyTitle(String title){

        assertThrows(
            IllegalArgumentException.class,
            () ->  new JobApplication(
                0 ,
                0 ,
                title,
                JobType.FULL_TIME
            )
        );
    }

    @ParameterizedTest
    @ValueSource( strings = {"Software Developer  ", "  PenTester","   Backend Developer     "})
    void ConstructorTrimsTitle(String title){

        JobApplication app =  new JobApplication(0, 0, title, JobType.FULL_TIME);
        String trimmedTitel = title.trim();

        assertEquals(trimmedTitel, app.getRoleTitle());
    }

    @Test
    void mutableFieldsCanBeUpdated(){
        JobApplication app =  new JobApplication(0, 0, "Software Developer", JobType.FULL_TIME);
        LocalDate followUp = LocalDate.now().plusDays(3);

        app.setStatus(ApplicationStatus.REJECTED);
        app.setFollowUpDate(followUp);
        app.setNotes("updated notes");

        assertAll(
            () -> assertEquals(ApplicationStatus.REJECTED, app.getStatus()),
            () -> assertEquals(followUp, app.getFollowUpDate()),
            () -> assertEquals("updated notes", app.getNotes())
        );
    }

    @Test
    void hasFollowUpDueReturnsTrueForTodayOrPastDates(){
        JobApplication app =  new JobApplication(0, 0, "Software Developer", JobType.FULL_TIME);

        app.setFollowUpDate(LocalDate.now());
        assertTrue(app.hasFollowUpDue());

        app.setFollowUpDate(LocalDate.now().minusDays(1));
        assertTrue(app.hasFollowUpDue());
    }

    @Test
    void hasFollowUpDueReturnsFalseForFutureOrNullDates(){
        JobApplication app =  new JobApplication(0, 0, "Software Developer", JobType.FULL_TIME);

        app.setFollowUpDate(LocalDate.now().plusDays(1));
        assertFalse(app.hasFollowUpDue());

        app.setFollowUpDate(null);
        assertFalse(app.hasFollowUpDue());
    }

    @Test
    void equalityAndHashCodeUseIdOnly(){
        JobApplication app = new JobApplication(1, 0, "Software Developer", JobType.FULL_TIME);
        JobApplication sameId = new JobApplication(1, 1, "Backend Developer", JobType.PART_TIME);
        JobApplication otherId = new JobApplication(2, 0, "Software Developer", JobType.FULL_TIME);

        assertAll(
            () -> assertEquals(app, app),
            () -> assertEquals(app, sameId),
            () -> assertEquals(app.hashCode(), sameId.hashCode()),
            () -> assertNotEquals(app, otherId),
            () -> assertNotEquals(app, null),
            () -> assertNotEquals(app, "application")
        );
    }

    @Test
    void toStringContainsAllDisplayFields(){
        LocalDate applied = LocalDate.of(2026, 5, 1);
        LocalDate followUp = LocalDate.of(2026, 5, 8);
        JobApplication app = new JobApplication(
            1,
            2,
            "Software Developer",
            JobType.FULL_TIME,
            ApplicationStatus.APPLIED,
            applied,
            followUp,
            "No notes."
        );

        assertEquals(
            "[1] Software Developer | CompanyId: 2 | Type: FULL_TIME | Status: APPLIED | Applied: 2026-05-01 | Follow-up: 2026-05-08 | Notes: No notes.",
            app.toString()
        );
    }
}
