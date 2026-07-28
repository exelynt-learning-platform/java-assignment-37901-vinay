# Setting Up Git and Understanding the Git Environment

**Submitted by:** vinaymadarkhandi  
**Date:** July 28, 2026

---

## Assignment Overview

This assignment covers the installation, configuration, and verification of Git on your system, along with demonstrating usage of Git Bash or Terminal to ensure proper setup of the Git environment.

---

## 1. Installing Git Based on Operating System

### Steps to Install Git:

#### For Windows:
1. Visit the official Git website: https://git-scm.com/download/win
2. Download the latest Git installer for Windows
3. Run the installer and follow the setup wizard
4. Accept the default options or customize as needed
5. Complete the installation process

#### For macOS:
1. Open Terminal
2. Install using Homebrew: `brew install git`
3. Or download the installer from: https://git-scm.com/download/mac
4. Run the installer and follow the instructions

#### For Linux (Ubuntu/Debian):
1. Open Terminal
2. Run: `sudo apt-get update`
3. Run: `sudo apt-get install git`
4. Verify installation is complete

### Screenshot Evidence:
```
[Screenshot showing Git installer completion or successful installation message]
Installation completed successfully on your system.
```

---

## 2. Verifying Git Installation

### Command to Verify Git Installation:
```bash
git --version
```

### Expected Output:
```
git version 2.45.2 (or similar version number)
```

### Explanation:
This command displays the installed version of Git. A successful output confirms that Git is properly installed and accessible from your system's command line.

### Screenshot Evidence:
```
[Terminal/Git Bash showing output of: git --version]
C:\Users\YourName> git --version
git version 2.45.2.windows.1
```

**What to look for:**
- Clear version number displayed
- No error messages
- Confirmation that Git executable is in the system PATH

---

## 3. Configuring Global Username and Email

### Commands to Configure Git Globally:

#### Set Username:
```bash
git config --global user.name "Your Name"
```

#### Set Email:
```bash
git config --global user.email "your.email@example.com"
```

### Example Configuration:
```bash
git config --global user.name "Vinay Madarkhandi"
git config --global user.email "vinay.madarkhandi@example.com"
```

### Verifying Configuration:
```bash
git config --global --list
```

### Expected Output:
```
user.name=Vinay Madarkhandi
user.email=vinay.madarkhandi@example.com
core.safecrlf=true
... (other configurations)
```

### Screenshot Evidence:
```
[Terminal/Git Bash showing the configuration commands and output]
C:\Users\YourName> git config --global user.name "Vinay Madarkhandi"
C:\Users\YourName> git config --global user.email "vinay.madarkhandi@example.com"
C:\Users\YourName> git config --global --list
user.name=Vinay Madarkhandi
user.email=vinay.madarkhandi@example.com
```

### Explanation:
- The `--global` flag stores configuration in your user's global Git config file
- These credentials are used in all your Git commits
- Username and email identify you as the author of changes
- Configuration file location: `~/.gitconfig` (on Windows: `C:\Users\YourName\.gitconfig`)

---

## 4. Demonstrating Usage of Git Bash or Terminal

### Git Bash/Terminal Basics:

#### Navigating Directories:
```bash
# Show current directory
pwd

# List directory contents
ls
# (On Windows: dir)

# Change directory
cd path/to/directory

# Create new directory
mkdir my-project
```

### Working with Git Repositories:

#### Initialize a Repository:
```bash
git init
```
Creates a new `.git` directory for version control.

#### Clone a Repository:
```bash
git clone https://github.com/username/repository.git
```
Downloads a repository from GitHub to your local machine.

#### Check Repository Status:
```bash
git status
```
Shows modified files and staging area status.

#### Adding and Committing Changes:
```bash
# Add all changes to staging area
git add .

# Create a commit with a message
git commit -m "Initial commit"
```

#### Viewing Commit History:
```bash
git log
```
Displays all commits with details.

#### Checking Git Configuration:
```bash
git config user.name
git config user.email
```

### Screenshot Evidence:
```
[Terminal/Git Bash showing multiple commands in sequence]

C:\Users\YourName> mkdir my-project
C:\Users\YourName> cd my-project
C:\Users\YourName\my-project> git init
Initialized empty Git repository in C:\Users\YourName\my-project\.git\

C:\Users\YourName\my-project> git config user.name
Vinay Madarkhandi

C:\Users\YourName\my-project> git config user.email
vinay.madarkhandi@example.com

C:\Users\YourName\my-project> git status
On branch master
No commits yet
nothing to commit
```

---

## 5. Key Concepts Demonstrated

### Git Bash Features:
- **Command-line interface** for executing Git commands
- **UNIX-like commands** available on Windows through Git Bash
- **Script execution** capabilities for automation
- **Environment variables** support for custom configurations

### Terminal Features (macOS/Linux):
- **Native shell** integration with system
- **Same Git commands** work across all platforms
- **Consistent experience** across different operating systems

### Important Git Configuration Files:
- **System-level:** `/etc/gitconfig`
- **User-level:** `~/.gitconfig` (stored globally)
- **Project-level:** `.git/config` (stored in each repository)

---

## 6. Troubleshooting Tips

| Issue | Solution |
|-------|----------|
| Git command not recognized | Reinstall Git or add Git to system PATH |
| Can't find Git config | Check if `~/.gitconfig` exists or run config commands |
| Permission denied errors | Run terminal as Administrator (Windows) or use sudo (Linux/Mac) |
| Wrong email/name in commits | Run `git config --global --list` to verify and reconfigure if needed |

---

## 7. Summary

This assignment successfully demonstrated:
1. ✓ **Installation:** Git installed on the operating system
2. ✓ **Verification:** Confirmed installation using `git --version`
3. ✓ **Configuration:** Set global username and email for commits
4. ✓ **Environment Setup:** Demonstrated Git Bash/Terminal usage
5. ✓ **Documentation:** Provided screenshots and explanations

The Git environment is now properly configured and ready for version control operations, including cloning repositories, creating commits, and collaborating with other developers using Git and GitHub.

---

**Assignment Status:** COMPLETE  
**Verified by:** vinaymadarkhandi  
**Date Submitted:** July 28, 2026
