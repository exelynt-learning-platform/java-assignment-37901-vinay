# Branching and Merging – Week 1 Submission

This assignment demonstrates the use of Git branches for parallel development and explains how merging works, including merge conflicts.

## 1) Create and switch between branches

Start by creating a new branch from the main branch:

```bash
git checkout -b feature/login-page
```

or the newer equivalent:

```bash
git switch -c feature/login-page
```

To switch back to the main branch:

```bash
git checkout main
```

or:

```bash
git switch main
```

This shows how developers can move between different versions of the project safely.

## 2) Make changes in a separate branch

Once on a feature branch, create or edit files:

```bash
echo "Login page feature started" > feature.txt
git add feature.txt
git commit -m "Add login page feature file"
```

This keeps the work isolated from the main branch until it is ready to merge.

## 3) Merge branch into main

Switch back to main:

```bash
git checkout main
```

Then merge the feature branch:

```bash
git merge feature/login-page
```

If there are no conflicts, Git combines the changes automatically and creates a merge commit.

## 4) Example workflow

```bash
git init
git checkout -b feature/login-page
echo "Login page feature started" > feature.txt
git add feature.txt
git commit -m "Add login page feature file"
git checkout main
git merge feature/login-page
```

This demonstrates how branch-based work is merged into the main project line.

## 5) Explain merge conflicts

A merge conflict happens when two branches change the same line(s) of the same file differently, and Git cannot decide which version should win automatically.

Example:

- Main branch updates a file line to `Welcome to the app`
- Feature branch updates the same line to `Welcome to our product`
- Git cannot merge both changes automatically

When this happens, Git marks the conflict in the file and asks the developer to resolve it manually.

### Typical conflict markers look like:

```text
<<<<<<< HEAD
Welcome to the app
=======
Welcome to our product
>>>>>>> feature/login-page
```

The developer must choose the correct final content and then commit the resolution.

## 6) How conflicts occur

Merge conflicts usually occur when:

- two people edit the same file in different branches
- both branches change the same section of code or text
- one branch deletes a line while another modifies the same line

Git only raises a conflict when the same content cannot be merged cleanly.

## 7) How to resolve merge conflicts

After identifying the conflict:

1. Open the conflicted file
2. Edit the file to keep the correct final version
3. Remove conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`)
4. Save the file
5. Stage the resolved file
6. Commit the merge resolution

Example:

```bash
git add resolved-file.txt
git commit -m "Resolve merge conflict in login page"
```

## 8) Screenshot or command output submission checklist

To complete the assignment, include screenshots or command outputs for:

- creating a new branch
- switching to another branch
- changing files in the feature branch
- merging into main
- a conflict example or conflict markers

These outputs should clearly show:

- branch names
- commands run
- merge success or conflict details

## 9) Final summary

This assignment shows that branching allows developers to work independently without affecting the main codebase. Merging brings those changes together safely, and merge conflicts are a normal part of Git when multiple edits overlap. Understanding how and why conflicts happen is essential for working in real-world team projects.

## 10) Submission statement

This branch-based Git assignment was completed by:

- creating and switching branches
- making changes in a separate branch
- merging the branch into the main branch
- explaining merge conflicts and how they occur
- preparing the required screenshots or command outputs

This work is submitted on the branch:

`branching-merging-week1-2373`
