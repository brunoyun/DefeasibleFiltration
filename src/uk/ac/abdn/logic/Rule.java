package uk.ac.abdn.logic;

import java.util.HashSet;
import java.util.Objects;


public class Rule {
    public HashSet<Literal> body;
    public Literal head;
    public Literal name;
    public boolean isDefeasible;
    public double strength;
    static int id=0;
    private int personalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return isDefeasible == rule.isDefeasible &&
                Double.compare(rule.strength, strength) == 0 &&
                personalId == rule.personalId &&
                body.equals(rule.body) &&
                head.equals(rule.head) &&
                Objects.equals(name, rule.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, head, name, isDefeasible, strength, personalId);
    }

    public Rule(Literal head, boolean isDefeasible, double strength) {
        this.body = new HashSet<>();
        this.head = head;
        this.name = null;
        this.isDefeasible = isDefeasible;
        this.strength = strength;
        personalId = id;
        id++;
    }

    public void addToBody(Literal l){
            body.add(l);
    }

    public void setName(Literal n){
        this.name = n;
    }


    @Override
    public String toString() {
        return ((name == null)?"null": name.toString()) +
                ": " + body +
                (isDefeasible? "=>" : "->")+
                head.toString() +" "+ strength;
    }



}
