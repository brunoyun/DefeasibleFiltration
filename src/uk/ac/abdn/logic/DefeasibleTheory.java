package uk.ac.abdn.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DefeasibleTheory {
    private HashSet<Rule> Rules, Activated;
    public HashMap<Literal,HashSet<Rule>> SortedByHead, ASortedByHead;


    public DefeasibleTheory() {
        SortedByHead = new HashMap<>();
        ASortedByHead = new HashMap<>();
        Rules = new HashSet<>();
        Activated = new HashSet<>();
    }


    public void addRule(Rule r){
        SortedByHead.computeIfAbsent(r.head, k-> new HashSet<>());
        SortedByHead.get(r.head).add(r);
        this.Rules.add(r);
    }

    @Override
    public String toString() {
        return setToString(Rules);
    }


    private void checkActivated(){
        HashSet<Literal> reachable = new HashSet<>();
        Activated = new HashSet<>();
        ASortedByHead = new HashMap<>();

        int activatedSizeBefore;
        //Get all the non activated rules
        do{
            activatedSizeBefore = Activated.size();
            HashSet<Rule> nonActivated = Rules;
            nonActivated.removeAll(Activated);
            for(Rule r : Rules){
                if(reachable.containsAll(r.body)){
                    Activated.add(r);
                    reachable.add(r.head);
                }
            }
        }while(activatedSizeBefore != Activated.size());

        //We update the activated sorted
        for(Literal l : SortedByHead.keySet()){
            HashSet<Rule> T = SortedByHead.get(l);
            T.retainAll(Activated);
            ASortedByHead.put(l,T);
        }
    }



    public static String setToString(HashSet<Rule> R){
        StringBuilder result = new StringBuilder();
        for(Rule r : R)
            result.append(r).append("\n");
        return result.toString();
    }

    public HashSet<Rule> computeEssentialRules(Literal l){
        HashSet<Rule> essential = new HashSet<>();
        checkActivated();

        //We begin with the rules that conclude l that are essential
        HashSet<Rule> rulesHeadL = ASortedByHead.get(l);
        if(rulesHeadL != null)
        {
            ArrayList<Rule> rulesToBeTreated = new ArrayList<>(rulesHeadL);
            //We iterate until we do not create other rules
            while(!rulesToBeTreated.isEmpty()){

                Rule currentRule = rulesToBeTreated.get(0);
                essential.add(currentRule);

                //We add rules that can help to activate the rule
                for(Literal L2 : currentRule.body){
                    HashSet<Rule> newRulesforLiteral =  ASortedByHead.get(L2);

                    //We only add those that are not yet essential
                    newRulesforLiteral.removeAll(essential);
                    newRulesforLiteral.removeAll(rulesToBeTreated);
                    rulesToBeTreated.addAll(newRulesforLiteral);
                }

                //We add rules that rebut the current rule
                HashSet<Rule> newRebut = ASortedByHead.get(currentRule.head.negation());
                if(newRebut != null)
                {
                    newRebut.removeAll(essential);
                    newRebut.removeAll(rulesToBeTreated);
                    rulesToBeTreated.addAll(newRebut);
                }

                //We add rules that undercut the current rule
                if(currentRule.name != null) {
                    HashSet<Rule> newUndercut = ASortedByHead.get(currentRule.name.negation());
                    if(newUndercut != null)
                    {
                        newUndercut.removeAll(essential);
                        newUndercut.removeAll(rulesToBeTreated);
                        rulesToBeTreated.addAll(newUndercut);
                    }
                }


                rulesToBeTreated.remove(0);
            }
        }


        return essential;
    }

}
