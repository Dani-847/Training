# Fundamentals of Programming (C++23)

Exercises, example programs and study material for an introductory programming course taught in modern C++.
It uses the C++23 standard library: `std::print`, `std::array`, structs, functions, ranges algorithms and views (`take`, `chunk_by`).

> The exercise statements and code comments are in Spanish, the language the course is taught in.

## Layout

```
FundamentalsOfProgramming/
├── Theory/                  my own summary and study notes (PDF)
├── Exercises/
│   ├── Unit1/               types, operators, control flow, arrays, structs, algorithms
│   ├── Unit2/               functions, value and reference semantics, views
│   └── Unit3/               containers and classes (extension topics)
├── ExampleCode/             worked examples from the course, plus a CMake template
├── CollaborativeSessions/   group exercises
└── MockExam/                a final exercise that combines every examinable topic
```

Each exercise folder contains:

| File | Content |
|---|---|
| `README.md` | Statement, concepts used and a sample run (user input and expected output) |
| `solution.cpp` | Official reference solution provided by the instructor |
| `proposed_solution.cpp` | My own solution. Written when no official solution was published, or when the official one does not do exactly what the statement asks (U1-6, U2-2, SC2; each README explains the difference) |

When a folder has more than one solution, all of them are kept on purpose as alternative approaches to the same problem:

| Folder | Alternatives |
|---|---|
| [U1-06 Inventory](Exercises/Unit1/06_Inventory), [U2-02 LatencyTake](Exercises/Unit2/02_LatencyTake), [SC2 Avengers](CollaborativeSessions/SC2_Avengers) | Official solution vs. my proposed solution |
| [SC1 Death Stranding](CollaborativeSessions/SC1_DeathStranding) | Three parallel arrays vs. an array of structs |
| [SC3 Spider-Man](CollaborativeSessions/SC3_SpiderMan) | Data read from a JSON Lines file vs. data in an in-memory array |

## Theory

| File | Description |
|---|---|
| [00_ContentOverview.pdf](Theory/00_ContentOverview.pdf) | One-page-per-level overview of the whole syllabus, with a beginner's introduction and a suggested exercise order |
| [01_StudyNotes.pdf](Theory/01_StudyNotes.pdf) | Step-by-step study notes with short examples. Its appendix covers reading JSON Lines with nlohmann/json |
| [Archive/ContentOverview_v1.pdf](Theory/Archive/ContentOverview_v1.pdf) | First version of the overview |

The instructor's slide decks are not included (third-party material).

## Exercises

### Unit 1: types, operators, control flow and aggregates

| # | Exercise | Concepts |
|---|---|---|
| 01 | [Parity](Exercises/Unit1/01_Parity) | `getline`, `stoi`, `try`/`catch`, `%` |
| 02 | [Temperature](Exercises/Unit1/02_Temperature) | `stod`, formatting with `{:.2f}`, custom functions |
| 03 | [VowelCounter](Exercises/Unit1/03_VowelCounter) | Indexed `for`, functions that return `bool` |
| 04 | [ArraySumProduct](Exercises/Unit1/04_ArraySumProduct) | `std::array`, accumulator pattern |
| 05 | [Grades](Exercises/Unit1/05_Grades) | `std::array<double,5>`, `if`/`else`, average |
| 06 | [Inventory](Exercises/Unit1/06_Inventory) | Array of `string`, `.empty()` |
| 07 | [RectangleArea](Exercises/Unit1/07_RectangleArea) | Struct with a member function |
| 08 | [Pointers1](Exercises/Unit1/08_Pointers1) | Pointers: `&`, `*` (extension) |
| 09 | [Pointers2_Student](Exercises/Unit1/09_Pointers2_Student) | Pointer to struct, `->` (extension) |
| 10 | [Pointers3_SortSubrange](Exercises/Unit1/10_Pointers3_SortSubrange) | Pointer arithmetic, `ranges::sort` on a sub-range (extension) |
| 11 | [QuarterlySales](Exercises/Unit1/11_QuarterlySales) | Array of structs, sorting with a projection |
| 12 | [ParityPartition](Exercises/Unit1/12_ParityPartition) | `ranges::partition` with a predicate |
| 13 | [InsecurePasswords](Exercises/Unit1/13_InsecurePasswords) | `partition`, sorting a sub-range, iterators |
| 14 | [DigitalLibrary](Exercises/Unit1/14_DigitalLibrary) | `partition`, sorting a sub-range, iterators |

### Unit 2: functions, value and reference semantics, views

| # | Exercise | Concepts |
|---|---|---|
| 01 | [AmountsUntilEOF](Exercises/Unit2/01_AmountsUntilEOF) | Reading until EOF, `while`/`continue`, `try`/`catch` |
| 02 | [LatencyTake](Exercises/Unit2/02_LatencyTake) | `views::take`, input validation |
| 03 | [ChunkByIntegers](Exercises/Unit2/03_ChunkByIntegers) | `views::chunk_by` with a binary predicate |
| 04 | [ChunkByStarWars](Exercises/Unit2/04_ChunkByStarWars) | Structs, sorting with a projection, `chunk_by` |

### Unit 3: containers and classes (extension)

| # | Exercise | Concepts |
|---|---|---|
| 01 | [VectorPushBack](Exercises/Unit3/01_VectorPushBack) | `std::vector`, `push_back`, `sort` |
| 02 | [Map](Exercises/Unit3/02_Map) | `std::map` |
| 03 | [RectangleClass](Exercises/Unit3/03_RectangleClass) | Classes: `private`, constructor, methods |
| 04 | [CarClass](Exercises/Unit3/04_CarClass) | Classes: `private`, constructor, methods |

## Example code

| Example | Difficulty | Concepts |
|---|---|---|
| [CE1 · Assassin's Creed](ExampleCode/CE1_AssassinsCreed) | 2/5 | Struct containing an array, array passed by reference |
| [CE2 · Astrophysics statistics](ExampleCode/CE2_AstrophysicsStatistics) | 3/5 | Struct as a return value, validation with several returns |
| [CE5 · File streams](ExampleCode/CE5_FileStreams) | 4/5 | `ifstream`, JSON Lines, nlohmann::json, `vector` (extension) |

CE3 and CE4 were not part of the material I received.

## Collaborative sessions

| Session | Difficulty | Concepts |
|---|---|---|
| [SC1 · Death Stranding](CollaborativeSessions/SC1_DeathStranding) | 2/5 | `std::array`, accumulators. Two candidate solutions: three parallel arrays vs. a struct |
| [SC2 · Avengers](CollaborativeSessions/SC2_Avengers) | 3/5 | Structs, array passed by reference, percentages |
| [SC3 · Spider-Man](CollaborativeSessions/SC3_SpiderMan) | 3/5 | Structs, sorting with a projection. Proposed solutions with and without a JSON Lines file |

## Mock exam

[MockExam](MockExam) (eSports tournament): structs, arrays of structs, pass-by-reference functions and finding a maximum with its index.
Written as final practice for this course.

## Build

You need a C++23 compiler (GCC 14+, or Visual Studio 2022 17.10+).

```bash
g++ -std=c++23 -o app solution.cpp
```

To use CMake, copy [`ExampleCode/CMakeLists.template.txt`](ExampleCode/CMakeLists.template.txt) next to your source file and rename it to `CMakeLists.txt`.
CE5 and SC3 (file variant) also need [nlohmann/json](https://github.com/nlohmann/json).
