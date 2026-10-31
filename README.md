# Introduction to GitHub – Week 1 Submission

This assignment explains how to create a GitHub account, create a remote repository, connect a local repository to GitHub, and submit screenshots or command outputs with explanations.

## 1) Create a GitHub account

Go to:

https://github.com/

Click on "Sign up" and create a personal account using a valid email address, username, and password.

A GitHub account is required so that local repositories can be pushed to a remote location for backup, sharing, and collaboration.

## 2) Create a remote repository

After logging in to GitHub:

1. Click on the "+" button in the top-right corner
2. Select "New repository"
3. Enter a repository name, for example:
   - `github-introduction-demo`
4. Choose visibility (Public or Private)
5. Optionally add a README file
6. Click "Create repository"

GitHub will generate a remote repository URL similar to:

```bash
https://github.com/your-username/github-introduction-demo.git
```

## 3) Connect local repository to GitHub

If the local repository already exists, run:

```bash
git remote add origin https://github.com/your-username/github-introduction-demo.git
```

If the repository is new, first initialize it:

```bash
mkdir github-introduction-demo
cd github-introduction-demo
git init
git status
```

Create a file:

```bash
echo "This project is connected to GitHub." > intro.txt
```

Stage and commit the file:

```bash
git add intro.txt
git commit -m "Add introduction file"
```

Then connect the local repository to the GitHub remote:

```bash
git remote add origin https://github.com/your-username/github-introduction-demo.git
```

Finally, push to GitHub:

```bash
git branch -M main
git push -u origin main
```

## 4) Why this is important

Connecting a local Git repository to GitHub allows you to:

- store code remotely
- collaborate with others
- keep a backup of your work
- share projects with teachers or teammates
- track project history in a central location

## 5) Example command sequence

```bash
git init

git status

echo "This project is connected to GitHub." > intro.txt

git add intro.txt
git commit -m "Add introduction file"

git remote add origin https://github.com/your-username/github-introduction-demo.git
git branch -M main
git push -u origin main
```

## 6) Screenshot or command output checklist

To complete the assignment, include screenshots or command outputs for:

- GitHub account creation page or login
- new repository creation screen
- local repository commands: `git init`, `git add`, `git commit`
- remote connection: `git remote add origin ...`
- successful push to GitHub: `git push -u origin main`

Each screenshot or output should include a brief explanation of what it demonstrates.

## 7) Common beginner mistakes

### Mistake 1: Forgetting to create a remote repository first

If a remote URL does not exist yet, pushing will fail. The repository must exist on GitHub before the local repository can be connected and pushed.

### Mistake 2: Using the wrong remote URL or branch name

If the URL is incorrect, Git will not connect. If the branch name is wrong, the push may fail or publish to the wrong branch.

## 8) Final summary

This assignment demonstrates how GitHub is used as a remote hosting platform for Git repositories. Creating a GitHub account, creating a repository, linking it to a local repo, and pushing changes are essential skills for software development and collaboration.

This work is submitted on the branch:

`introduction-to-github-week1-2373`
