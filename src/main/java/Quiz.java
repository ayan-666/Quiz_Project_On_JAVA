import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) throws IOException, ParseException {

        Scanner options = new Scanner(System.in);
        System.out.println("Please Choose an Option\n");
        System.out.println("Option 1: Add Quiz\n");
        System.out.println("Option 2: Start Quiz");
        System.out.print("------------------------------------------\n");
        System.out.print("Press 1 for Option1 or 2 for Option2: ");

        int Option = options.nextInt();

        if(Option==1){
            addQuiz();
        }
        else if(Option==2){
            startQuiz();
        }
        else{
            System.out.println("Your given number is invalid. Please enter 1 or 2");
        }
    }
    public static void addQuiz() throws IOException, ParseException {

        char ch = 'y';
        String fileName = "./src/main/resources/Question_Bank.json";
        Scanner input = new Scanner(System.in);

        do {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(fileName));

            JSONObject addQuiz = new JSONObject();
            System.out.println();
            System.out.print("Please Add a Question Here:");
            addQuiz.put("Question", input.nextLine());
            System.out.println();
            System.out.println("You have to enter four options:");
            System.out.println();
            System.out.print("Option a: ");
            addQuiz.put("Option a", input.nextLine());
            System.out.println();
            System.out.print("Option b: ");
            addQuiz.put("Option b", input.nextLine());
            System.out.println();
            System.out.print("Option c: ");
            addQuiz.put("Option c", input.nextLine());
            System.out.println();
            System.out.print("Option d: ");
            addQuiz.put("Option d", input.nextLine());
            System.out.println();
            System.out.print("Please Give The Correct Answer: ");
            addQuiz.put("Correct Ans ", input.nextLine());


            JSONArray jsonArray = (JSONArray) obj;
            jsonArray.add(addQuiz);

            FileWriter file = new FileWriter(fileName);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

            System.out.println("Your Qustions are saved. Do you want to add more Questions? (y/n)");
            ch = input.nextLine().charAt(0);
        }

        while (ch != 'n');
        System.out.println("Thanks For Adding The Questions");
    }

    public static void startQuiz() throws IOException, ParseException {

        System.out.println("\nYou will be asked 5 Questions. Are You Ready? (y/n)");
        int count = 0;
        Scanner input = new Scanner(System.in);
        char ready = input.nextLine().charAt(0);
        Random ran = new Random();

        if(ready == 'y') {
            for (int i = 1; i <= 5; i++) {

                int queNo = ran.nextInt(17);

                String fileName = "./src/main/resources/Question_Bank.json";
                JSONParser jsonParser = new JSONParser();
                Object obj = jsonParser.parse(new FileReader(fileName));

                JSONArray jsonArray = (JSONArray) obj;

                JSONObject jsonObject = (JSONObject) jsonArray.get(queNo);

                System.out.println("\nQuestion - " + i + ": " + (String) jsonObject.get("Question"));

                String option1 = (String) jsonObject.get("Option a");
                System.out.println("Option a: " + option1);
                String option2 = (String) jsonObject.get("Option b");
                System.out.println("Option b: " + option2);
                String option3 = (String) jsonObject.get("Option c");
                System.out.println("Option c: " + option3);
                String option4 = (String) jsonObject.get("Option d");
                System.out.println("Option d: " + option4);

                System.out.println();
                System.out.println("Please Give Your Answer");

                String userAns = input.nextLine();
                String realAns = (String) jsonObject.get("Correct Ans ");

                if (userAns.equals(realAns)) {
                    System.out.println();
                    System.out.println("Your Answer is CORRECT !!!");
                    count++;
                } else {
                    System.out.println("Your Answer is INCORRECT !!!");
                }

            }
            System.out.println();
            System.out.println("You Got " + count + " marks");
            System.out.println("Thanks for Participation.");
        }

        else
        {
            System.out.println("You didn't Participate.");
        }
    }
}
