package uk.ac.abdn.io;

import uk.ac.abdn.logic.DefeasibleTheory;
import uk.ac.abdn.logic.Literal;

public class Main {

    public static void main(String[] args) {

        if(args.length != 2)
            errorMessage();
        else{
            String filepath = args[0];
            String query = args[1];

            long startTime = System.nanoTime();
            Parser p = new Parser();
            DefeasibleTheory T = p.Parse(filepath);
            //System.out.println(T);

            System.out.println(DefeasibleTheory.setToString(T.computeEssentialRules(Parser.convert(query))));

            long endTime = System.nanoTime();
            long timeElapsed = endTime-startTime;
            System.out.println("Execution Time in milliseconds: "+timeElapsed/1000000);
        }


    }


    public static void errorMessage(){
        System.out.println("Please enter two arguments: the filepath to the defeasible theory and the query.\n\nAn example is:\n\tjava -jar filtrationApp.jar DefeasibleTheory.txt '!b'");
    }
}
