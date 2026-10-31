# Tracking Changes with Git – Week 1 Submission

This assignment focuses on tracking project changes using Git. It covers creating or reusing a repository, adding multiple files, staging and committing changes, writing meaningful commit messages, viewing commit history, and identifying common beginner mistakes.

## 1) Create or use an existing repository

If you are starting from a new folder:

```bash
mkdir tracking-changes-demo
cd tracking-changes-demo
git init
```

If a repository already exists, navigate to it:

```bash
cd existing-repository
```

## 2) Add at least 2 files

Create two files to simulate a small project:

```bash
cat > app.txt <<EOF
Welcome to the Git tracking assignment.
This file contains app information.
EOF

cat > notes.txt <<EOF
This is a notes file for practice.
We will track changes using Git.
EOF
```

You can also use `touch`:

```bash
touch app.txt notes.txt
```

## 3) Check status before staging

Run:

```bash
git status
```

Example output:

```bash
On branch master

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        app.txt
        notes.txt
```

This shows both files are present in the working directory, but Git is not yet tracking them.

## 4) Stage the files

Use `git add` to stage the files for the next commit:

```bash
git add app.txt notes.txt
```

You can also stage all tracked and untracked files:

```bash
git add .
```

To confirm the files are staged:

```bash
git status
```

Example output:

```bash
On branch master

Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   app.txt
        new file:   notes.txt
```

## 5) Commit changes with meaningful messages

Create the first commit:

```bash
git commit -m "Add initial project files"
```

This message communicates that the repository now contains the starting project files.

To make additional changes, we can modify one file and commit again:

```bash
echo "Updated app description." >> app.txt
git add app.txt
git commit -m "Update app description and notes"
```

This creates multiple commits and shows a meaningful project history.

## 6) View commit history

To view the commit log:

```bash
git log --oneline
```

Example output:

```bash
abc1234 Update app description and notes
def5678 Add initial project files
```

You can also use:

```bash
git log
```

This gives more detail, including the author, date, and message for each commit.

## 7) Explain the workflow

The Git process for tracking changes is:

1. Modify files in the working directory
2. Check status with `git status`
3. Stage files with `git add`
4. Save the changes with `git commit -m "message"`
5. Review history with `git log`

This is the basic lifecycle for version control and is essential for collaborative project work.

## 8) Two common beginner mistakes

### Mistake 1: Forgetting to stage files before committing

Many beginners create a file or edit content and then run `git commit` without using `git add` first.

Example of the mistake:

```bash
git commit -m "Add file"
```

This usually results in Git saying there is nothing to commit because the file was never staged.

Why this matters:

- Git does not save changes automatically
- The staging area is where Git prepares files for a commit
- Without staging, the change is not included in the commit

### Mistake 2: Writing unclear or vague commit messages

A common beginner mistake is writing messages like:

```bash
git commit -m "changes"
```

or

```bash
git commit -m "update"
```

These messages do not explain what changed.

Why this matters:

- Clear commit messages help others understand the project timeline
- They make debugging easier
- They improve team collaboration and project maintenance

Better examples:

```bash
git commit -m "Add initial project files"
git commit -m "Update app description and notes"
```

## 9) Final assignment summary

This task was completed by:

- creating or using an existing Git repository
- adding at least two files
- checking status with `git status`
- staging files with `git add`
- creating meaningful commits with `git commit -m "..."`
- viewing the commit history with `git log`
- identifying and explaining beginner mistakes

This work is submitted on the branch:

`tracking-changes-with-git-week1-2373`

## 10) Example commands used

```bash
git init
git status
git add app.txt notes.txt
git commit -m "Add initial project files"
git log --oneline
```

This demonstrates correct Git usage for tracking and managing project changes.
