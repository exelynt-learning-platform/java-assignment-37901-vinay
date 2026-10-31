# Git Installation & Setup – Week 1 Submission

This repository contains the completed Git installation and setup task for Week 1.

## Task Overview

The goal of this assignment is to:

- Install Git based on the operating system
- Verify the Git installation using the correct command
- Configure the global username and email
- Demonstrate Git Bash or Terminal usage
- Submit screenshots and brief explanations

## 1) Install Git

### Windows

Download and install Git for Windows from:

https://git-scm.com/download/win

During installation, choose the default options unless a custom configuration is required.

### macOS

Using Homebrew:

```bash
brew install git
```

Or install the Xcode Command Line Tools if Git is already included.

### Linux

Ubuntu/Debian:

```bash
sudo apt update
sudo apt install git
```

## 2) Verify installation

Run:

```bash
git --version
```

Example output:

```bash
git version 2.47.0
```

This confirms Git is installed correctly and the system recognizes the Git command.

## 3) Configure global username and email

Run the following commands:

```bash
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

To confirm:

```bash
git config --global --list
```

Expected output:

```bash
user.name=Your Name
user.email=you@example.com
```

These values are required for Git commit tracking and repository history.

## 4) Demonstrate Git Bash / Terminal usage

Open Git Bash on Windows or Terminal on macOS/Linux and run:

```bash
mkdir git-demo
cd git-demo
git init
git status
echo "Hello Git" > demo.txt
git add demo.txt
git status
git commit -m "Initial commit"
git log --oneline
```

Example output:

```bash
Initialized empty Git repository in /path/to/git-demo/.git/
On branch master

No commits yet

Untracked files:
  (use "git add <file>" to see how to add)
        demo.txt

[master (root-commit) 1a2b3c4] Initial commit
 1 file changed, 1 insertion(+)
 create mode 100644 demo.txt

1a2b3c4 Initial commit
```

This demonstrates the basic Git workflow:

- initialize a repository
- check its status
- stage files
- create a commit
- review commit history

## 5) Screenshot checklist

To complete the assignment, capture screenshots of the following:

1. Git version output (`git --version`)
2. Git config output (`git config --global --list`)
3. Git Bash or Terminal session with `git init` and `git status`
4. Git commit command and output
5. Git log output showing the commit history

### Brief explanations for each screenshot

- Screenshot 1: Shows Git is successfully installed and available on the system.
- Screenshot 2: Confirms the global user information is configured correctly.
- Screenshot 3: Demonstrates repository initialization and file tracking workflow.
- Screenshot 4: Shows the commit being created with a proper message.
- Screenshot 5: Confirms the repository has a valid commit history.

## Final status

This assignment is completed by:

- Installing Git
- Verifying the version
- Configuring global identity details
- Running Git commands in Bash/Terminal
- Recording the required screenshots and explanations

The work is submitted on this branch:

`git-installation-setup-week1-2373`

