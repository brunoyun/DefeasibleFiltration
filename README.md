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
| ->e      | There is a strict rule with no name, no literals in its body that concludes 'e'       |
| R4:m,k=>!R7 |  The defeasible rule 'R4' has two literals 'm' and 'k' in its body and concludes 'not R7'|
| R7:c,t=>b, 0.6|  The defeasible rule 'R7' has a strength of 0.6, two literals 'c' and 't' in its body that concludes 'b'|

Without loss of generality, we consider rules with atomic heads. The name and strength of a rule can be omitted. If not specified, the strength of a rule is 0.5 by default.

## Usage

To run the software, just use the following command in a terminal:


```sh
java -jar DefeasibleFiltering.jar [DefeasibleTheory_file] [Query]
```

where [DefeasibleTheory_file] refers to a text file in the aforementioned format and [Query] is a literal or its negation. Please note that to write negations, it is recommended to surround it by single quotes, e.g. [Query]='!b'.

### Requirements

It is advised to install the latest [Java Runtime Environment][3].
