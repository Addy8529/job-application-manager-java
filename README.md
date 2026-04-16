
# Job Application Manager

A console-based Java application for tracking job applications, companies, interview stages, and follow-ups.

This project is designed to evolve from a simple Java console program into a full backend system with database integration, REST APIs, and production-level features.

---

## Overview

The Job Application Manager helps users organize and track their job search process by managing applications, statuses, deadlines, and company information.

It solves a real-world problem by providing a structured way to monitor applications and avoid missing follow-ups or deadlines.

---

## Features (Phase 1)

- Add companies
- Add job applications linked to a company
- Store job role and job type
- Track application status (e.g., Applied, Interview, Offer)
- Record application date
- Add notes for each application
- Set follow-up dates
- List all applications
- Filter applications by status
- View applications by company
- Update application status
- Delete applications
- Show upcoming follow-ups

---

## Technologies Used

- Java
- VS Code
- Object-Oriented Programming (OOP)
- Java Collections (ArrayList)
- Enums
- Java Time API (LocalDate)

---

## Project Structure

```

src/
├── Main.java
├── model/
│   ├── Company.java
│   ├── JobApplication.java
│   ├── ApplicationStatus.java
│   └── JobType.java
├── service/
│   └── ApplicationService.java
└── ui/
└── ConsoleUI.java

````

### Structure Explanation

- **model** → Core domain objects (Company, JobApplication, Enums)
- **service** → Business logic and data handling
- **ui** → Console interaction and input/output handling
- **Main** → Application entry point

---

## Application Status Flow

The application uses enums to represent job states:

- `APPLIED`
- `INTERVIEW`
- `OFFER`
- `REJECTED`
- `ACCEPTED`
- `WITHDRAWN`

---

## Job Types

- `INTERNSHIP`
- `WORKING_STUDENT`
- `FULL_TIME`
- `PART_TIME`

---

## How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/job-application-manager-java.git
````

2. Open the project in IntelliJ IDEA

3. Run:

   ```text
   Main.java
   ```

---

## Example Menu

```
==== Job Application Manager ====
1. Add company
2. Add job application
3. List all applications
4. Filter applications by status
5. View applications by company
6. Update application status
7. Delete application
8. Show upcoming follow-ups
0. Exit
```

---

## Example Output

```
[1] Software Engineer Intern
Company: Siemens
Type: INTERNSHIP
Status: APPLIED
Applied On: 2026-04-15
Follow-Up: 2026-04-22
Notes: Submitted via career portal
```

---

## Validation Rules

* Company name cannot be empty
* Job role cannot be empty
* Application must be linked to an existing company
* Dates must follow format: `yyyy-MM-dd`
* Application IDs must be unique
* Status is automatically set to `APPLIED` when created

---

## Learning Objectives

This project demonstrates:

* Object-oriented design (classes, enums, relationships)
* Domain modeling of real-world workflows
* Separation of concerns (model, service, UI)
* Working with collections and filtering logic
* State management using enums
* Input validation and error handling
* Date handling using Java Time API

---

## Roadmap (Future Improvements)

### Phase 2

* File persistence (save/load applications)

### Phase 3

* Refactor into clean architecture

### Phase 4

* Gradle build system

### Phase 5

* Unit testing with JUnit

### Phase 6

* Database integration (PostgreSQL/MySQL)

### Phase 7

* Spring Boot REST API

### Phase 8

* Authentication, validation, logging, Docker deployment

---

## Why This Project

This project is designed to:

* Reflect a real-world use case (job tracking)
* Demonstrate backend-oriented thinking
* Serve as a strong portfolio project for software development roles
* Showcase the ability to design, build, and evolve a complete system

---

## Author
Muhammad Ahad



