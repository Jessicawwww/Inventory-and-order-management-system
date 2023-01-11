package demo02;

import java.util.Scanner;
import java.util.Scanner;
public class QuizTime {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Quiz myQuiz = new Quiz();
        //add 25 questions for this quiz
        myQuiz.addQuestion("What is the max speed of the Boeing 777 in km per hour?","950",2);
        myQuiz.addQuestion(" I am tall when I am young and I am short when I am old. What am I?","candle",2);
        myQuiz.addQuestion("What word becomes shorter when you add two letters to it?","short",2);
        myQuiz.addQuestion("How many moons does the planet Venus have?","4",3);
        myQuiz.addQuestion("What can you catch but not throw?","taxi",3);

        //reference for questions: https://hobbylark.com/party-games/Free-Fun-100-Question-Quiz-3
        myQuiz.addQuestion("What country is Mt. Fujiyama  located in?","japan",3);
        myQuiz.addQuestion("Which sea creature has three hearts?","Octopus",3);
        myQuiz.addQuestion("Which instrument has forty-seven strings and seven pedals? ","Harp",3);
        myQuiz.addQuestion("Whose face was said to have launched 1,000 ships?","Helen of Troy",3);
        myQuiz.addQuestion("How many pedals do most modern pianos have? ","3",3);

        myQuiz.addQuestion("Who went to school with a lamb?","Mary",1);
        myQuiz.addQuestion("Who sat in a corner eating a Christmas pie?","Little Jack Horner",1);
        myQuiz.addQuestion("Which item of nursery furniture was set on top of a tree?","Cradle",1);
        myQuiz.addQuestion("If you rode a Chopper, what was this?","Bicycle",1);
        myQuiz.addQuestion("What is the Italian word for pie ?","Pizza",1);

        myQuiz.addQuestion("Which reptile should you never smile at, according to the song?","Crocodile",4);
        myQuiz.addQuestion("What was Marilyn Monroe's natural hair colour?","Ginger",4);
        myQuiz.addQuestion("What was Louis Armstrong's chosen form of music? ","Jazz",4);
        myQuiz.addQuestion("Which country does the sport of pelato come from? ","Spain",4);
        myQuiz.addQuestion("When did the French Revolution end?","1799",4);

        myQuiz.addQuestion("Which ocean surrounds the Maldives?","Indian Ocean",5);
        myQuiz.addQuestion("Which planet shares its name with a dog?","Pluto",5);
        myQuiz.addQuestion("Which famous novel featured Jo, Meg, Beth and Amy March?","Little Women",5);
        myQuiz.addQuestion("Who wrote the epic poem Paradise Lost?","John Milton",5);
        myQuiz.addQuestion("Name the Chinese writer, born in 551 BCE, known for preaching high moral standards.","Confucius",5);

        //begin the quiz
        System.out.print(" ----------MURLI'S QUIZ TIME WITH COMPLEXITY CUSTOMIZATION----------\n"+
                " #####Quiz questions range from a minimum difficulty level of 1\n" +
                " ##### to max difficulty level of 5. \n" +
                " #####Decide if you want a random quiz or else provide a range to produce quiz questions.\n"+
                " Enter 1 for random quiz, 2 for customized quiz, 0 to quit: ");
        //choose the quiz mode
        int mode = input.nextInt();
        if (mode==0){
            //0 for exit
            System.exit(0);
        }
        else if (mode==1){
            //1 for random quiz
            myQuiz.giveQuiz();
            System.out.println("Do you want to review all questions in this quiz? 1 for yes, 0 for no");
            int review = input.nextInt();
            //review all questions
            if (review==1){
                myQuiz.displayAllQuestions();
            }
        } else {
            //customize the quiz and choose difficulty level
            System.out.print("Enter minimum difficulty level: ");
            int minLevel = input.nextInt();
            System.out.print("Enter maximum difficulty level: ");
            int maxLevel = input.nextInt();
            System.out.println(" Lets go........");

            myQuiz.giveQuiz(minLevel,maxLevel);
            System.out.println("Do you want to review all questions under the range of these complexities? 1 for yes, 0 for no");
            int review = input.nextInt();
            if (review==1){
                myQuiz.displayAllQuestions(minLevel,maxLevel);
            }
        }

    }
}
