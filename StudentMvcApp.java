import controller.StudentController;
import model.Student;
import view.StudentView;

/**
 * =============================================================================
 *                      MVC ARCHITECTURE DEMO APPLICATION
 * =============================================================================
 *
 * Detailed Data Flow in MVC Pattern:
 * -----------------------------------
 * Step 1: Initialization
 *         - Model is created containing raw student business entity data.
 *         - View is created containing presentation rendering logic.
 *         - Controller is instantiated, with references to both Model and View injected.
 *
 * Step 2: Initial Render (Controller -> Model -> View)
 *         - Controller retrieves state from the Model.
 *         - Controller passes formatted attributes to View.
 *         - View renders the formatted student profile to the screen.
 *
 * Step 3: User Update Interaction (User Action -> Controller)
 *         - User/System invokes a business update (e.g. student scores or course changes).
 *         - Controller intercepts the request, validates the input range (0 - 100).
 *
 * Step 4: Model State Mutation (Controller -> Model)
 *         - Controller updates the Model's attributes via setters.
 *         - The Model updates internal state and recalculates academic letter grade.
 *
 * Step 5: View Refresh (Controller -> View)
 *         - Controller instructs the View to re-render the updated state.
 *         - Neither Model nor View knows about each other's existence;
 *           achieving true Separation of Concerns (SoC).
 * =============================================================================
 */
public class StudentMvcApp {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("          MVC ARCHITECTURE: STUDENT MANAGEMENT SYSTEM DEMO                ");
        System.out.println("==========================================================================");

        // ---------------------------------------------------------------------
        // Step 1: Initialize Model, View, and Controller
        // ---------------------------------------------------------------------
        System.out.println("\n[1] Initializing MVC Components:");
        System.out.println("    - Creating Model  : new Student(101, \"Vinay Madarkhandi\", \"Computer Science\", 78.5)");
        System.out.println("    - Creating View   : new StudentView()");
        System.out.println("    - Wiring Controller: Injecting Model and View into StudentController");

        Student model = new Student(101, "Vinay Madarkhandi", "Computer Science", 78.5);
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        // ---------------------------------------------------------------------
        // Step 2: Initial View Rendering through Controller
        // ---------------------------------------------------------------------
        System.out.println("\n[2] Initial State Rendered by View (via Controller.updateView()):");
        controller.updateView();

        // ---------------------------------------------------------------------
        // Step 3: User Action - Update Course & Marks via Controller
        // ---------------------------------------------------------------------
        System.out.println("\n[3] Triggering User Action / Controller Update:");
        System.out.println("    -> Transferring student to Advanced Artificial Intelligence...");
        controller.setStudentCourse("Advanced Artificial Intelligence");

        System.out.println("    -> Submitting midterm project score: 94.0...");
        controller.setStudentMarks(94.0);

        // ---------------------------------------------------------------------
        // Step 4: Re-render Updated Model through Controller
        // ---------------------------------------------------------------------
        System.out.println("\n[4] Updated State Rendered by View (via Controller.updateView()):");
        controller.updateView();

        // ---------------------------------------------------------------------
        // Step 5: Demonstrating Business Validation inside Controller
        // ---------------------------------------------------------------------
        System.out.println("\n[5] Demonstrating Controller Input Validation (Invalid marks = 125.0):");
        controller.setStudentMarks(125.0); // Out of bounds -> Rejected by controller

        System.out.println("\nVerifying that Model remained protected against invalid data:");
        controller.updateView();

        System.out.println("\n==========================================================================");
        System.out.println("MVC architecture demonstration completed successfully!");
        System.out.println("==========================================================================");
    }
}
