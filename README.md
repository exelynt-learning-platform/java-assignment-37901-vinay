# Git Repository Basics – Week 1 Submission

This assignment demonstrates how to create a new project folder, initialize a Git repository, inspect the `.git` folder, create a file, check repository status, and understand the three main Git file states.

## 1) Create a new project folder

Open Git Bash or Terminal and create a folder for the project:

```bash
mkdir git-repository-basics
cd git-repository-basics
```

This creates a working directory where the Git repository will live.

## 2) Initialize Git repository

Run:

```bash
git init
```

This command creates a new Git repository in the current folder. Git sets up a hidden `.git` directory that stores all metadata for version control, including:

- commit history
- branches
- configuration
- staging area information
- object database

### What is the `.git` folder?

The `.git` folder is the heart of a Git repository. It is a hidden folder created automatically after `git init`. It contains all the information Git needs to track and manage your project history.

Important files/folders inside `.git` include:

- `HEAD` – points to the current branch
- `config` – repository configuration
- `objects/` – stores all commit and file data
- `refs/` – stores branch and tag references
- `index` – staging area information

This folder should not be manually deleted unless you want to remove the repository history.

## 3) Create a new file

Create a basic file such as `index.txt`:

```bash
echo "This is my first Git repository file." > index.txt
```

We can also check the file content:

```bash
cat index.txt
```

## 4) Check file status using `git status`

Run:

```bash
git status
```

Example output:

```bash
On branch master

No commits yet

Untracked files:
  (use "git add <file>..." to mark for commit)
        index.txt
```

This means the file exists in the working directory but has not yet been added to the Git staging area.

## 5) Explain the three Git file states

Git files generally exist in three states:

### 1. Modified
A file is modified when it has been changed in the working directory but not yet staged.

Example:

```bash
echo "Updated content" >> index.txt
```

After this, `git status` will show the file as modified.

### 2. Staged
A file is staged when it is prepared to be included in the next commit.

Command:

```bash
git add index.txt
```

At this point, the file is in the staging area and is ready for commit.

### 3. Committed
A file is committed after being staged and then saved permanently in the repository history.

Command:

```bash
git commit -m "Add index.txt"
```

After committing, the file becomes part of the repository history and is tracked by Git.

## 6) Example workflow

```bash
mkdir git-repository-basics
cd git-repository-basics
git init
echo "This is my first Git repository file." > index.txt
git status
git add index.txt
git status
git commit -m "Add index.txt"
git log --oneline
```

This workflow shows the standard Git lifecycle:

- create a project folder
- initialize a repository
- add a file
- check status
- stage the file
- commit the change

## 7) Final explanation

This assignment helps understand how Git tracks files and stores project history. The `.git` folder is essential because it stores the repository metadata, while the working directory contains the actual project files. Understanding the modified, staged, and committed states is important because these states explain how changes move from local work to permanent version history.

## 8) Submission summary

The repository basics task was completed by:

- creating a new project folder
- initializing Git
- identifying the `.git` folder and explaining its purpose
- creating `index.txt`
- checking repository status with `git status`
- understanding the three Git file states

This work is submitted on the branch:

`git-repository-basics-week1-2373`
