# CSC483 - Algorithms Analysis and Design Assignment
**Student:** Doherty ayomide victor 
**Matriculation:** U2022/557103
**Submission Date:** April 5, 2026

## Project Structure
- `src/main/java/...` → All source code
- `test/java/...` → JUnit 5 tests
- `data/` → Sample datasets
- `outputs/` → Generated screenshots/tables (run the demos)

## How to Compile & Run

**Using IntelliJ / Eclipse / VS Code:**
1. Open the project folder.
2. Run `TechMartSearchDemo.java` → Question 1 (100,000 products)
3. Run `BenchmarkSorting.java` → Question 2 empirical analysis

**Manual Compile:**
```bash
javac -d bin src/main/java/com/csc483/assignment/search/*.java
javac -d bin src/main/java/com/csc483/assignment/sorting/*.java
java -cp bin com.csc483.assignment.search.TechMartSearchDemo
