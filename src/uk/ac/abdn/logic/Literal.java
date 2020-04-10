package uk.ac.abdn.logic;

import java.util.Objects;

public class Literal {

    public String name;
    public boolean negated;

    public Literal(String name, boolean negated) {
        this.name = name;
        this.negated = negated;
    }

    public Literal negation(){
        return new Literal(this.name, !this.negated);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literal literal = (Literal) o;
        return negated == literal.negated &&
                name.equals(literal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, negated);
    }

    @Override
    public String toString() {
        return (negated?"!":"")+name;
    }
}
