# SDA- — Software Design & Architecture (coursework)

Coursework for the Software Design & Architecture course in my BS Software Engineering at COMSATS University Abbottabad: architecture assignments, lab tasks, an MVC lab, a database project, and a Java smart-home project.

## What it is

A semester archive for the SDA course. Each folder is one deliverable — written architecture analysis (Markdown/Word/PDF), UML and class diagrams, SQL, and Java code where the task called for an implementation.

Highlights:

- **Netflix architecture overview** — analysis of Netflix's microservices architecture, AWS infrastructure, Open Connect CDN, resilience patterns (Chaos Monkey, circuit breakers, load balancing) and data storage choices (Cassandra, MySQL, DynamoDB, S3).
- **Lab final — Smart Home Energy Saving System** — a Java implementation for controlling a smart light through a smart-home hub: user authentication, hub connection, and light control (on/off, timer, intensity, colour) via a mobile-app-style interface. Code, output and PDF report included, with its own detailed `Readme.md` in `LAB FINAL/`.
- **Database project** — a SQL Server schema for a movie theater system (`Movies`, `Theaters`, …) with seed data, in `DATABASE PROJECT/SQLQuery1.sql`.
- **Scalability presentation** ("first video") — slides, a recorded video, AI voice-over files, handwritten notes, and two small Java demos (`UserCreator.java`, `Scalability1.java`).

## Contents

```
ASSIGNMENT 2 VideobNetflix Architecture Overview.md   Netflix architecture write-up
ASSIGNMENT/            Architecture problems with solutions (Markdown) + code files
LAB TASK/              Lab 6 work (LAB 6 FULL, LAB06)
LAB 11/                Lab 11 (MICRO)
MID LAB/               Mid lab — class diagram (JPG), Java src/, LAB MID.rar
LAB FINAL/             Smart Home Energy Saving System (Java) — CODE/, FINAL OUTPUT/,
                       PDF FILE/, plus its own Readme.md
MVC LAB 1st/           MVC pattern examples (simple + complex)
MVC LAB 1st.rar        Archive of the MVC lab
SPRING INTI/           Introductory Spring project (demo/)
DATABASE PROJECT/      MovieTheater SQL Server schema + seed data (SQLQuery1.sql)
GROUP TASK/            Netflix architecture group document (ARCH Netflix.docx)
PROJECT JAVA/          Java project folder
first video/           Scalability presentation: PPTX, MP4, AI audio + script, notes,
                       UserCreator.java, Scalability1.java
```

## Run it

This is a coursework archive, not a single runnable app:

- **Documents** — open the `.md`, `.docx`, `.pdf`, `.pptx` files directly.
- **SQL** — run `DATABASE PROJECT/SQLQuery1.sql` in SQL Server (it creates and seeds the `MovieTheater` database).
- **Java demos** — e.g. `javac "first video/UserCreator.java"` then `java` the class; the lab-final smart-home system lives in `LAB FINAL/CODE/` and is described in `LAB FINAL/Readme.md`.

## Tech

- Java, Spring (intro project), SQL Server (T-SQL), UML/architecture documentation in Markdown, Word and PDF

## License

MIT — see [LICENSE](LICENSE).
