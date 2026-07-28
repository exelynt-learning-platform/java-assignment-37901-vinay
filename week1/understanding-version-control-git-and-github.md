# Understanding Version Control, Git, and GitHub

Description

This document answers the assignment questions about version control, Git, and GitHub in original wording.

## 1) What is version control and why it is important

Version control is a system that records and manages changes to files over time. It lets you save snapshots of a project, review its history, and return to earlier states if needed. This is important because it prevents accidental data loss, makes it easy to track who changed what and when, and supports collaboration by allowing multiple people to work on the same project without overwriting each other’s work.

## 2) Why developers use Git

Developers use Git because it is fast, flexible, and designed for collaboration. Git records each change as a commit with a message and author information, so teams can understand the evolution of the code. Git supports branching and merging, which lets developers experiment, develop features, and fix bugs in isolation, then combine changes safely. Its distributed nature also means each developer has a full copy of the project history locally, enabling offline work and quick operations.

## 3) Common problems Git solves

- Accidental overwrites: Git preserves history so lost work can be recovered.
- Conflicting edits: Branching and merge tools help manage and resolve conflicts.
- Lack of accountability: Commits include author and message metadata for traceability.
- Difficulty experimenting: Branches let you try changes without affecting the main codebase.
- Coordination across a team: Push/pull workflows and remote repositories coordinate contributions.

## 4) Difference between Git and GitHub

Git is the version control system — the software that records changes, manages branches, and stores history on your machine or any server. GitHub is a web-based platform built around Git that hosts Git repositories and adds collaboration features like pull requests, issue tracking, code review, CI integration, and a web UI. In short: Git does the core tracking; GitHub provides hosting, collaboration, and tooling on top of Git.

## 5) Centralized vs distributed version control systems

- Centralized VCS (e.g., Subversion): There is a single central server that stores the project history; developers check out working copies and commit back to that server. Pros: simpler model and centralized control. Cons: the central server is a single point of failure and many operations require network access.
- Distributed VCS (e.g., Git): Every developer has a complete copy of the repository and its full history. Pros: faster local operations, easy branching, no single point of failure, good support for offline work and flexible workflows. Cons: slightly more complex mental model for beginners and different workflows to learn.

---

Submitted by: vinaymadarkhandi
