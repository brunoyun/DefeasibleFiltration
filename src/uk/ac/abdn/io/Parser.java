package uk.ac.abdn.io;

import uk.ac.abdn.logic.DefeasibleTheory;
import uk.ac.abdn.logic.Literal;
import uk.ac.abdn.logic.Rule;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    public DefeasibleTheory Parse(String filepath){
        File file = new File(filepath);

        BufferedReader br;

        DefeasibleTheory T = new DefeasibleTheory();

        try {
            br = new BufferedReader(new FileReader(file));
            String st ="";

            while (true) {
                try {
                    if ((st = br.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //We find the arguments
                String regeRule = "^\\s*(?:([a-z0-9A-Z_]+)\\s*:\\s*)?" +
                        "((?:\\s*!?[a-z0-9A-Z_]+\\s*,?\\s*)*)" +
                        "\\s*(=|-)>\\s*" +
                        "(!?[a-z0-9A-Z_]+)\\s*" +
                        "(?:,\\s*([0-9]+\\.?[0-9]*|[0-9]*\\.[0-9]+))?$";

                Pattern patterRule = Pattern.compile(regeRule);
                Matcher matcher = patterRule.matcher(st);

                while(matcher.find())
                {
                    //We find literals
                    ArrayList<String> body = new ArrayList<>();
                    for(String s : matcher.group(2).split(","))
                    {
                        String literalInBody = s.replaceAll("\\s+","");
                        if( (!literalInBody.equals("")) && (!body.contains(literalInBody)))
                            body.add(literalInBody);
                    }

                    Literal head = convert(matcher.group(4));

                    boolean isDefeasible = matcher.group(3).equals("=");
                    String strength = matcher.group(5);
                    boolean isNull = (strength == null);
                    double ruleStrength = isNull? 0.5 : Double.parseDouble(strength);

                    Rule currentRule = new Rule(head, isDefeasible, ruleStrength);
                    for(String s:body)
                        currentRule.addToBody(convert(s));

                    if(matcher.group(1) != null)
                        currentRule.setName(convert(matcher.group(1)));
                    T.addRule(currentRule);
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return T;
    }


    public static Literal convert(String s){
        boolean isNegatedHead = s.substring(0,1).equals("!");
        return new Literal(isNegatedHead? s.substring(1): s, isNegatedHead);
    }


}
