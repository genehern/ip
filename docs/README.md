# Gene User Guide

## Product Screenshot

![Product UI](./Ui.png)

---

## Introduction

Gene is a chatbot that helps users manage their tasks and deadlines efficiently. It is ideal for users who want to stay
organized and on top of their work, especially those familiar with CLI usage for quick access.

---

## Features

| Feature      | Command Format                                     | Example Usage                             |
|--------------|----------------------------------------------------|-------------------------------------------|
| Add Todo     | `todo <task description>`                          | `todo read book`                          |
| Add Deadline | `deadline <task description> /by <deadline>`       | `deadline submit report /by 2024-06-30`   |
| Add Event    | `event <task description> /from <start> /to <end>` | `event project meeting /from 2pm /to 4pm` |
| Delete Task  | `delete <task number>`                             | `delete 2`                                |
| Mark as Done | `mark <task number>`                               | `mark 1`                                  |
| Unmark Task  | `unmark <task number>`                             | `unmark 1`                                |
| List Tasks   | `list`                                             | `list`                                    |
| Remind Tasks | `remind /by <date>`                                | `remind 2024-06-30`                       |
| Exit Program | `bye`                                              | `bye`                                     |

---

## Quick Start

### Task List

- [x] Add tasks
- [x] Mark/unmark tasks
- [x] Delete tasks
- [ ] Save/load tasks from file
- [ ] GUI support

---

## Usage Examples

### Adding a Todo

```sh
todo read book
```

**Expected Output:**

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### Adding a Deadline

```sh
deadline submit report /by 2024-06-30
```

**Expected Output:**

```
Got it. I've added this task:
  [D][ ] submit report (by: 2024-06-30)
Now you have 2 tasks in the list.
```

### Marking a Task as Done

```sh
mark 1
```

**Expected Output:**

```
Nice! I've marked this task as done:
  [T][X] read book
```

### Listing All Tasks

```sh
list
```

**Expected Output:**

```
Here are the tasks in your list:
1. [T][X] read book
2. [D][ ] submit report (by: 2024-06-30)
```

---

## Advanced Commands

- `remind <date>`: Show all tasks due/happening before a specific date.
- `bye`: Exit the program.

---

## Contact

For feedback or issues, please open an issue on GitHub.

---
