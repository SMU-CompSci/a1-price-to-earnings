# A1 — Price-to-Earnings (CS 1342, Fall 2025)

## Intro (Context)

A small business owner estimates **market value** using the *price-to-earnings* approach:

```
market value = projected earning × price-to-earnings ratio
```

You will read **5** projected earnings and **5** price-to-earnings ratios (one per year), validate them, compute the **5** market values, and print each year’s earning, ratio, and market value on its own line. This specification aligns with our course PDF handout for Assignment 1.&#x20;

---

## Example

For the following inputs (earnings then ratios):

```
200000, 230000, 250000, 300000, 325000
6.0, 6.5, 6.75, 7.0, 7.25
```

The corresponding market values are:

```
1200000, 1495000, 1687500, 2100000, 2356250
```

---

## Project layout & rules

```
a1-price-to-earnings/
├─ gradlew / gradlew.bat / build.gradle  (provided; do not edit)
├─ src
│  └─ main
│     └─ java
│        ├─ Business.java               (you add)
│        └─ BusinessEvaluation.java     (you add)
└─ (other project files)                (do not edit)
```

* **Only** add your code to `src/main/java/`.
* **Do not** modify Gradle files, tests, or any files outside `src/main/java/`.
* Your code must **compile**.

---

## Function Description

### `Business` (in `src/main/java/Business.java`)

Create a class to encapsulate year-by-year data.

* **Fields**

    * `double[] projectedEarning` — length **5**
    * `double[] marketValue` — length **5**

* **Constructor**

    * `Business(double[] projectedEarning)`
      Initializes `this.projectedEarning` from the argument (length must be 5).
      Initializes all entries of `marketValue` to `0.0`.

* **Getters**

    * `double[] getProjectedEarning()`
    * `double[] getMarketValue()`

* **Setters (market values)**

    * `void setMarketValue(int index, double value)` — update one element by index
    * `void setMarketValue(double[] values)` — bulk update (length must be 5)

> Keep names and array sizes **exactly** as specified.

### `BusinessEvaluation` (in `src/main/java/BusinessEvaluation.java`)

This is the **entry point** with `public static void main(String[] args)`:

1. **Read 5 earnings** (as doubles). Each must be in **\[200000, 500000]**; re-prompt until valid.
2. **Construct** a `Business` with the earnings array.
3. **Read 5 ratios** (as doubles). Each must be in **\[0.0, 10.0]**; re-prompt until valid.
4. **Compute** `marketValue[i] = projectedEarning[i] * ratio[i]` and store in the `Business`.
5. **Print 5 lines**, each containing:
   `projectedEarning[i] ratio[i] marketValue[i]`

You may write small helper methods (e.g., `readInRange(...)`) as needed.

---

## Returns

This is a console program. It **prints** exactly **5** lines of results (no extra commentary) to standard output. Methods in `Business` return arrays or `void` as documented above.

---

## Input Format

The program will be run interactively. It should prompt and read values in this order:

1. **Five** projected earnings (doubles), one at a time. Prompt:

   ```
   Enter a value of earnings (200,000 - 500,000) :
   ```
2. **Five** price-to-earnings ratios (doubles), one at a time. Prompt:

   ```
   Enter a value of ratio (0.0 - 10.0) :
   ```

If a value is out of range, **re-prompt** using the **same** prompt text until a valid number is provided.

---

## Constraints

* Exactly **5** earnings, each **200,000 ≤ earning ≤ 500,000**.
* Exactly **5** ratios, each **0.0 ≤ ratio ≤ 10.0**.
* Use `double` for all numeric inputs and calculations.
* Output must be exactly **5** lines; each line:
  `earning ratio marketValue`
* **Project layout:** only edit

    * `src/main/java/Business.java`
    * `src/main/java/BusinessEvaluation.java`
      Do **not** modify Gradle files, tests, or any other files.
* Code must **compile**.

---

## Sample Input

*(Shown with the required prompts; user types numbers after each prompt.)*

```
Enter a value of earnings (200,000 - 500,000) : 200000
Enter a value of earnings (200,000 - 500,000) : 230000
Enter a value of earnings (200,000 - 500,000) : 250000
Enter a value of earnings (200,000 - 500,000) : 300000
Enter a value of earnings (200,000 - 500,000) : 325000
Enter a value of ratio (0.0 - 10.0) : 6.0
Enter a value of ratio (0.0 - 10.0) : 6.5
Enter a value of ratio (0.0 - 10.0) : 6.75
Enter a value of ratio (0.0 - 10.0) : 7.0
Enter a value of ratio (0.0 - 10.0) : 7.25
```

---

## Sample Output

```
200000 6.0 1200000
230000 6.5 1495000
250000 6.75 1687500
300000 7.0 2100000
325000 7.25 2356250
```

---

## Explanation

Each year’s **market value** is computed by multiplying the corresponding **projected earning** and **price-to-earnings ratio**:

* `200000 × 6.0 = 1200000`
* `230000 × 6.5 = 1495000`
* `250000 × 6.75 = 1687500`
* `300000 × 7.0 = 2100000`
* `325000 × 7.25 = 2356250`

The program prints one line per year with `earning ratio marketValue`.

---

## Grading (100 pts)

* **Business.java design (fields, constructor, getters/setters):** 25
* **Input validation — earnings (range & re-prompt):** 15
* **Input validation — ratios (range & re-prompt):** 15
* **Correct market value calculations:** 20
* **Output format (exact 5 lines, correct order & spacing):** 10
* **Code quality (comments, naming, indentation):** 10
* **Project rules followed (files/compilation):** 5

**Late policy** (based on the course handout):
0–2 days late: −20%; 2–4 days late: −40%; 4–7 days late: −60%; >7 days late: **no credit**.&#x20;

---

## Build, run, and (optional) local test

* **Run** (from terminal in the project root):

    * Windows: `.\gradlew.bat run`
    * macOS/Linux: `./gradlew run`

* **Compile only**:

    * Windows: `.\gradlew.bat build`
    * macOS/Linux: `./gradlew build`

* **(If provided) Run tests**:

    * Windows: `.\gradlew.bat test`
    * macOS/Linux: `./gradlew test`

> In Eclipse, you can also use the **Gradle Tasks** view (or **Run As → Gradle build**) if Gradle integration is installed.

---

## Common pitfalls

* ❌ Wrong array sizes (must be exactly 5).
* ❌ Skipping input validation or not re-prompting.
* ❌ Printing additional debug text in the final results.
* ❌ Renaming required fields/methods (autograder may depend on names).
* ❌ Placing files outside `src/main/java/`.

## Need help?

* Can’t push? Recheck token permissions (Content: Read & Write), repo access, and that you’re using the token as your password.
* Build problems? Make sure you didn’t edit Gradle or test files; keep your classes in `src/main/java/`.
* Validation loops? Use `while` loops that continue until the entered value is inside the allowed range.



# GitHub Classroom Setup & IDE Integration

This guide walks you through creating your private GitHub Classroom repo, generating a fine-grained Personal Access Token (PAT), and connecting your IDE. Use the **Eclipse**, **IntelliJ**, or **VS Code** section as needed.

---

## 1) Get your private repo (GitHub Classroom)

1. Open the **assignment invitation link** on Canvas.
2. Click **Accept assignment** for **“A1-Price-to-earnings.”**
3. GitHub Classroom creates your **private** repo in the **SMU-CompSci** organization. You’ll push your code to this repo.

---

## 2) Create a Fine-Grained Personal Access Token (PAT)

Use a fine-grained PAT for authenticated Git operations from your IDE.

1. GitHub → **Settings** → **Developer settings** → **Personal access tokens** → **Fine-grained tokens** → **Generate new token**
2. **Token name:** `First_Last_<IDE>` (e.g., `Lawrence_Klinkert_Eclipse`)
3. **Resource owner:** `SMU-CompSci`
4. **Expiration:** `12/15/2025` (last day of school)
5. **Repository access:** **All repositories** (safe—your org grants only your private assignment repos)
6. **Repository permissions:** **Contents → Read & write** (this also includes **Metadata**)
7. Click **Generate**, then **copy** the token **once** and store it securely (e.g., `Keys.txt`).
8. **Do not commit** your token or `Keys.txt` to Git.
9. **Email the TA** to approve your token connection to `SMU-CompSci`.
10. After approval, you can pull/commit/push from your IDE for the term.

> **Tip:** If you’re used to classic scopes, the fine-grained equivalent you need is **Repository → Contents: Read & write**. You do **not** need admin or workflow scopes for basic Git.

---

## 3) Eclipse IDE

### A) Clone & import your repo

1. Open **Eclipse** → in **Package Explorer**, **Right-click** → **Import…**
2. **Git → Projects from Git (with smart import)** → **Clone URI**
3. Paste your repo **HTTPS URL** (from your private SMU-CompSci repo).
4. **User:** your GitHub username
   **Password:** your **fine-grained token** (from `Keys.txt`) → **Next**
5. Branches: **check `main` only** (uncheck `feedback`) → **Next**
6. Choose a local folder (e.g., `C:\Users\<you>\CS1342\a1`) → **Next** → **Finish**
7. Wait for Eclipse to build. Expand your project (e.g., `a1-price-to-earnings`).
8. In `src/main/java/`, **create**:

    * `Business.java`
    * `BusinessEvaluation.java`
9. **Do not edit** other provided files (Gradle config, tests, etc.).

### B) Basic Git (Pull → Commit → Push)

* **Pull** updates: **Right-click project → Team → Pull…**
* **Commit** changes:

    1. **Right-click → Team → Commit…** (or open **Git Staging**: **Window → Show View → Other… → Git → Git Staging**)
    2. Move files to **Staged Changes** (+), write a clear **Commit Message**, verify **Author/Committer**.
    3. Click **Commit** (local) **or** **Commit and Push** (local + remote).
* **Push** (if you only committed): **Right-click → Team → Push to Upstream**.

> **Common push error (rejected: non-fast-forward):**
> Pull first (**Team → Pull…**), resolve merges, then commit/push again.

> **Pro tips:**
> • Pull before each work session. • Commit early/often with meaningful messages.
> • Never commit secrets (tokens, `Keys.txt`). Consider a global gitignore entry.

---

## 4) IntelliJ IDEA

### A) Add your GitHub account (token)

1. **File → Settings → Version Control → GitHub → Add account → Log In with Token**
2. Paste your **fine-grained token** → **Add Account**
3. Verify Git is detected: **Settings → Version Control → Git → Test**.

### B) Clone & import your repo

1. In your GitHub repo, click **Code → HTTPS**, copy the URL.
2. IntelliJ (Welcome screen) → **Get from VCS**
   *(or* **File → New → Project from Version Control**\*)\*
3. Paste URL → choose local folder → **Clone**.
4. When prompted, **Trust** the project; IntelliJ will auto-import **Gradle**.

> **Re-open later:** Open the project root or `build.gradle` and IntelliJ will re-import.

### C) Basic Git (Pull → Commit → Push)

* **Pull/Fetch:** **Git** widget (top bar) → **Pull** / **Fetch**
* **Commit:** Open **Commit** tool window → select files → write message → **Commit**
* **Push:** **Git → Push…** (first push uses your saved token)

### D) Quick fixes

* **Push rejected:** **Git → Pull** (optionally **rebase**), resolve conflicts, then **Push**.
* **403 / auth failed:** Confirm token is **fine-grained**, **owner = SMU-CompSci**, **Contents: Read & write**, and has org approval.
* **Gradle issues:** Click **Reload All Gradle Projects**, or **File → Invalidate Caches / Restart**, then re-import.

---

## 5) VS Code

### A) Install prerequisites (one-time)

1. **Install Git** on your OS (VS Code uses your system Git).
2. **Install a JDK** (Java 17+ recommended) and tell VS Code which JDK to use.

    * In VS Code, run **Command Palette → “Java: Configure Java Runtime”** to view/set the runtime used by your projects.
3. **Install the Extension Pack for Java** (language support, project manager, test/debug).

> If you have multiple JDKs, “Java: Configure Java Runtime” lets you choose defaults per project or workspace.

---

### B) Clone your private repo using your fine-grained PAT

1. In your GitHub repo, click **Code → HTTPS** and copy the URL.
2. In VS Code: **Source Control** → **Clone Repository** (or **Command Palette → Git: Clone**), paste the HTTPS URL.
3. When Git prompts for credentials:

    * **Username:** your GitHub username
    * **Password:** **paste your fine-grained token** from Step 2 of this guide.
      Git will accept a PAT **in place of a password** for HTTPS, and you can cache it with a credential helper (e.g., Git Credential Manager).

> Note: The **Accounts** sign-in in VS Code is for GitHub OAuth features (PRs, Settings Sync). For Git **clone/pull/push** with a **fine-grained PAT**, authenticate via the Git HTTPS prompt as above.
---

### C) Open & build the Java project

1. After cloning, **Open Folder** (the repo root).
2. VS Code’s Java tools will detect Gradle/Maven projects and prompt to import/build. Use the Java run/debug views to compile/run tests as needed.

---

### D) Basic Git (Pull → Commit → Push)

* **Pull** updates: **Source Control** view → **…** → **Pull** (or Command Palette).
* **Stage & Commit**: check files, write a clear message, **Commit**.
* **Push**: **… → Push** (or the cloud-arrow icon). VS Code wraps standard Git commands.

---

### E) Quick fixes & common errors

* **403 / auth failed on push** → Your token likely lacks **Repository → Contents: Read & write** or org approval isn’t complete. Adjust token permissions/approval.
* **Endless auth prompts** → Ensure a credential helper (e.g., Git Credential Manager) is active so your PAT is cached securely.
* **JDK not detected / wrong version** → Use **Java: Configure Java Runtime** to select the correct JDK for the project.



