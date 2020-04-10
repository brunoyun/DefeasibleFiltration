# Defeasible Filtering

We work in the setting of defeasible theories expressed using propositional logic. This particular software can filter a defeasible theory for a given query by keeping only the essential rules.

## Content

This git repository contains the following files:
- A runnable JAR that computes the filtered defeasible theory (_out/artifacts/DefeasibleFiltering.jar/_)
- The corresponding sourcecode (_src/_)

## Input Format

In order to specify a defeasible theory, we encode rules (one per line) in a text file with the following syntax:

| Syntax        | Description  |
| ------------- |:------------------:|
| R1:->c     | The strict rule 'R1' has literals in its body and concludes 'c' |
| ->e      | The strict rule with no name, no literals in its body and concludes 'e'       |
| R4:m,k=>!R7 |  The defeasible rule 'R4' with two literals 'm' and 'k' in its body that concludes 'not R7'|



## Usage

To run the software, just use the following command in a terminal:


```sh
java -jar WeightedBipolarSETAFs.jar [ASPARTIX_file]
```

where [ASPARTIX_file] refers to a text file in the aforementioned ASPARTIX format.

### Requirements

It is advised to install the latest [Java Runtime Environment][3].


[1]: https://www.irit.fr/~Leila.Amgoud/BAFs.pdf
[2]: https://spiral.imperial.ac.uk/bitstream/10044/1/35993/5/Main.pdf
[3]: https://java.com/en/download/manual.jsp
[4]: https://www.dbai.tuwien.ac.at/research/argumentation/aspartix/setaf.html