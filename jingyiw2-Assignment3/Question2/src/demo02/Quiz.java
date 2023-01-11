package demo02;

import java.util.ArrayList; // class importing
import java.util.Scanner;
public class Quiz{

    private ArrayList<Question> questionList = new ArrayList<>();

    //define add method for Quiz class and return the updated question list
    public ArrayList<Question> addQuestion(String query, String result, int level) {
        //initialize each element with fields and call the set method to set complexity
        Question newQuestion = new Question(query,result);
        newQuestion.setComplexity(level);
        //add new question
        this.questionList.add(newQuestion);
        return this.questionList;
    }

    //define giveQuiz method to display each question, accept answer and check its result in turn
    public void giveQuiz(){
        //to accept user' inputs
        Scanner input = new Scanner(System.in);
        //keep track of the results;
        ArrayList<Boolean> results = new ArrayList<>();

        //record the number of questions in this quiz
        int number = this.questionList.size();
        ArrayList<Question> list = this.questionList;

        //display each question
        for (int i = 0; i < number; i++) {
            System.out.println(list.get(i).getQuestion());
            //get right answer
            String correctAnswer = list.get(i).getAnswer();
            //get user's answer
            System.out.print("Please type in the answer: (type in \"exit\" to terminate the quiz)");
            String candidateAnswer = input.nextLine();
            //in case the candidate want to end the quiz in advance
            if (candidateAnswer.equals("exit")){
                break;
            }
            //get the result of each question and record it
            results.add(list.get(i).answerCorrect(candidateAnswer));
        }

        //display results of the quiz
        int correctNumber=0,wrongNumber=0;
        //calculate the results
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i)){
                correctNumber+=1;
            }else{
                wrongNumber+=1;
            }
        }
        //print out the results
        System.out.println("Results: "+"Correct: "+correctNumber+"; Incorrect: "+wrongNumber);
    }

    //method for user to see all questions and corresponding answers
    public void displayAllQuestions(){
        //record the number of questions in this quiz
        int number = this.questionList.size();
        ArrayList<Question> list = this.questionList;

        for (int i = 0; i < number; i++) {
            System.out.println(list.get(i).toString());
        }
    }

    //
    public void giveQuiz(int minLevel, int maxLevel){
        //to accept user' inputs
        Scanner input = new Scanner(System.in);
        //keep track of the results;
        ArrayList<Boolean> results = new ArrayList<>();

        //record the number of questions in this quiz
        int number = this.questionList.size();
        ArrayList<Question> list = this.questionList;

        //display each question
        for (int i = 0; i < number; i++) {
            if ((list.get(i).getComplexity()>=minLevel) && (list.get(i).getComplexity()<=maxLevel)){
                System.out.println(list.get(i).getQuestion());
                //get right answer
                String correctAnswer = list.get(i).getAnswer();
                //get user's answer
                System.out.print("Please type in the answer: (type in \"exit\" to terminate the quiz)");
                String candidateAnswer = input.nextLine();
                //in case the candidate want to end the quiz in advance
                if (candidateAnswer.equals("exit")){
                    break;
                }
                //get the result of each question and record it
                results.add(list.get(i).answerCorrect(candidateAnswer));
            }
        }

        //display results of the quiz
        int correctNumber=0,wrongNumber=0;
        //calculate the results
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i)){
                correctNumber+=1;
            }else{
                wrongNumber+=1;
            }
        }
        //print out the results
        System.out.println("Results: "+"Correct: "+correctNumber+"; Incorrect: "+wrongNumber);
    }

    //method for user to see all questions and corresponding answers
    public void displayAllQuestions(int minLevel, int maxLevel){
        //record the number of questions in this quiz
        int number = this.questionList.size();
        ArrayList<Question> list = this.questionList;

        for (int i = 0; i < number; i++) {
            if ((list.get(i).getComplexity()>=minLevel) && (list.get(i).getComplexity()<=maxLevel)) {
                System.out.println(list.get(i).toString());
            }
        }
    }

}
