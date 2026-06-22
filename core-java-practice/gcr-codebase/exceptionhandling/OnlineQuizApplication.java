/**
 * Online Quiz Application - Handles ArrayIndexOutOfBoundsException and NullPointerException
 * Student answers are stored in an array.
 * The program compares answers using: answers[5].equals("A")
 * Task: Identify possible exceptions, handle them, and continue checking remaining answers
 */
public class OnlineQuizApplication {
    
    private static class QuizQuestion {
        String question;
        String correctAnswer;
        String[] options;
        
        QuizQuestion(String q, String correct, String[] opts) {
            this.question = q;
            this.correctAnswer = correct;
            this.options = opts;
        }
    }
    
    private QuizQuestion[] questions;
    private String[] studentAnswers;
    private int totalQuestions;
    private int correctCount = 0;
    private int attemptedCount = 0;
    
    /**
     * Constructor - initialize quiz with questions
     */
    public OnlineQuizApplication(int numberOfQuestions) {
        this.totalQuestions = numberOfQuestions;
        this.questions = new QuizQuestion[numberOfQuestions];
        this.studentAnswers = new String[numberOfQuestions];
    }
    
    /**
     * Add a question to the quiz
     */
    public void addQuestion(int index, String question, String correct, String[] options) {
        try {
            if (index < 0 || index >= totalQuestions) {
                throw new ArrayIndexOutOfBoundsException("Invalid question index: " + index);
            }
            questions[index] = new QuizQuestion(question, correct, options);
            System.out.println("✓ Question " + (index + 1) + " added");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - " + e.getMessage());
        }
    }
    
    /**
     * Record student answer
     */
    public void recordAnswer(int questionIndex, String answer) {
        try {
            if (questionIndex < 0 || questionIndex >= totalQuestions) {
                throw new ArrayIndexOutOfBoundsException(
                    "Invalid question index: " + questionIndex + ". Valid range: 0-" + (totalQuestions - 1));
            }
            
            studentAnswers[questionIndex] = answer;
            System.out.println("✓ Answer recorded for Question " + (questionIndex + 1));
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Cannot record answer: " + e.getMessage());
        }
    }
    
    /**
     * Compare a single answer with the correct answer
     * Handles: ArrayIndexOutOfBoundsException, NullPointerException
     */
    public boolean compareAnswer(int index) {
        try {
            // Check for valid index
            if (index < 0 || index >= totalQuestions) {
                throw new ArrayIndexOutOfBoundsException(
                    "Question index " + index + " is out of bounds. Valid range: 0-" + (totalQuestions - 1));
            }
            
            // Check if question exists
            if (questions[index] == null) {
                throw new NullPointerException("Question at index " + index + " is null (not initialized)");
            }
            
            // Check if answer is provided
            if (studentAnswers[index] == null) {
                System.out.println("⚠ Question " + (index + 1) + ": No answer provided (unanswered)");
                return false;
            }
            
            // Compare answers using equals()
            boolean isCorrect = studentAnswers[index].equals(questions[index].correctAnswer);
            
            if (isCorrect) {
                correctCount++;
                System.out.println("✓ Question " + (index + 1) + ": CORRECT");
            } else {
                System.out.println("✗ Question " + (index + 1) + ": INCORRECT");
                System.out.println("  Your answer: " + studentAnswers[index]);
                System.out.println("  Correct answer: " + questions[index].correctAnswer);
            }
            
            attemptedCount++;
            return isCorrect;
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ ERROR - Array Index Out of Bounds: " + e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.out.println("❌ ERROR - Null Pointer Exception: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Compare all answers and continue even if exceptions occur
     * This demonstrates exception handling with continuation
     */
    public void evaluateAllAnswers() {
        System.out.println("\n========== Quiz Evaluation ==========");
        
        for (int i = 0; i < totalQuestions; i++) {
            try {
                compareAnswer(i);
            } catch (Exception e) {
                System.out.println("❌ Unexpected error evaluating question " + (i + 1) + ": " + e.getMessage());
                // Continue to next question instead of crashing
            }
        }
        
        displayResults();
    }
    
    /**
     * Safe answer comparison that handles all exceptions
     * Returns true if correct, false otherwise, never throws exceptions
     */
    public boolean safeCompareAnswer(int index) {
        try {
            // Validate index
            if (index < 0 || index >= totalQuestions) {
                System.out.println("Question #" + index + " - ERROR: Index out of bounds");
                return false;
            }
            
            // Null check for question
            if (questions[index] == null) {
                System.out.println("Question #" + index + " - ERROR: Question not found");
                return false;
            }
            
            // Null check for student answer
            if (studentAnswers[index] == null) {
                System.out.println("Question #" + (index + 1) + " - Unanswered");
                return false;
            }
            
            // Safe comparison
            return studentAnswers[index].equals(questions[index].correctAnswer);
            
        } catch (NullPointerException e) {
            System.out.println("Question #" + (index + 1) + " - ERROR: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Question #" + (index + 1) + " - Unexpected error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Display detailed quiz results
     */
    public void displayResults() {
        System.out.println("\n========== Quiz Results ==========");
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Questions Attempted: " + attemptedCount);
        System.out.println("Correct Answers: " + correctCount);
        System.out.println("Incorrect Answers: " + (attemptedCount - correctCount));
        
        if (attemptedCount > 0) {
            double percentage = (correctCount * 100.0) / attemptedCount;
            System.out.printf("Score: %.2f%%%n", percentage);
            
            if (percentage >= 80) {
                System.out.println("Grade: A (Excellent)");
            } else if (percentage >= 60) {
                System.out.println("Grade: B (Good)");
            } else if (percentage >= 40) {
                System.out.println("Grade: C (Pass)");
            } else {
                System.out.println("Grade: F (Fail)");
            }
        }
        System.out.println("==================================\n");
    }
    
    /**
     * Display quiz details
     */
    public void displayQuiz() {
        System.out.println("\n========== Quiz Details ==========");
        for (int i = 0; i < totalQuestions; i++) {
            if (questions[i] != null) {
                System.out.println("\nQ" + (i + 1) + ". " + questions[i].question);
                for (String option : questions[i].options) {
                    System.out.println("   " + option);
                }
                System.out.println("   Your answer: " + (studentAnswers[i] != null ? studentAnswers[i] : "Not answered"));
            }
        }
        System.out.println("==================================\n");
    }
    
    // Main method to demonstrate the system
    public static void main(String[] args) {
        OnlineQuizApplication quiz = new OnlineQuizApplication(5);
        
        // Add questions
        quiz.addQuestion(0, "What is the capital of India?",
            "B", new String[]{"A. Mumbai", "B. Delhi", "C. Bangalore", "D. Chennai"});
        
        quiz.addQuestion(1, "Which planet is closest to the sun?",
            "A", new String[]{"A. Mercury", "B. Venus", "C. Earth", "D. Mars"});
        
        quiz.addQuestion(2, "What is 2 + 2?",
            "C", new String[]{"A. 3", "B. 5", "C. 4", "D. 6"});
        
        quiz.addQuestion(3, "Which language is Java based on?",
            "B", new String[]{"A. Python", "B. C++", "C. JavaScript", "D. Ruby"});
        
        // Note: Question 4 is not added to test null pointer exception handling
        
        quiz.displayQuiz();
        
        // Test 1: Record valid answers
        System.out.println("Test 1: Recording answers");
        quiz.recordAnswer(0, "B");
        quiz.recordAnswer(1, "A");
        quiz.recordAnswer(2, "C");
        quiz.recordAnswer(3, "B");
        // Don't answer question 4
        
        // Test 2: Try to record answer at invalid index
        System.out.println("\nTest 2: Try to record answer at invalid index");
        quiz.recordAnswer(5, "A"); // Out of bounds
        quiz.recordAnswer(-1, "A"); // Negative index
        
        // Test 3: Compare individual answers
        System.out.println("\nTest 3: Comparing individual answers");
        quiz.compareAnswer(0); // Should be correct
        quiz.compareAnswer(1); // Should be correct
        quiz.compareAnswer(2); // Should be correct
        quiz.compareAnswer(3); // Should be correct
        
        // Test 4: Try to compare at invalid index (ArrayIndexOutOfBoundsException)
        System.out.println("\nTest 4: Try to compare answer at invalid index 5");
        quiz.compareAnswer(5);
        
        // Test 5: Try to compare unanswered question
        System.out.println("\nTest 5: Try to compare unanswered question 4");
        quiz.compareAnswer(4);
        
        // Test 6: Try to compare at negative index
        System.out.println("\nTest 6: Try to compare at negative index -1");
        quiz.compareAnswer(-1);
        
        // Test 7: Evaluate all answers (continues despite exceptions)
        System.out.println("\nTest 7: Evaluate all answers (will continue despite errors)");
        quiz.correctCount = 0; // Reset
        quiz.attemptedCount = 0;
        quiz.evaluateAllAnswers();
        
        // Test 8: Safe comparison method
        System.out.println("\nTest 8: Safe comparison (no exceptions thrown)");
        System.out.println("Question 0 is correct: " + quiz.safeCompareAnswer(0));
        System.out.println("Question 5 is correct: " + quiz.safeCompareAnswer(5)); // Will handle gracefully
        System.out.println("Question 4 is correct: " + quiz.safeCompareAnswer(4)); // Will handle gracefully
        
        // Test 9: Record wrong answer and check
        System.out.println("\nTest 9: Record wrong answer and compare");
        quiz.recordAnswer(0, "A"); // Wrong answer
        quiz.compareAnswer(0);
    }
}
