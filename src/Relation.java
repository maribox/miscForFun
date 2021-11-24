import java.sql.Array;
import java.util.*;

public class Relation {
    public static void main(String[] args) {
        Random rand = new Random();
        Relation testRelation = new Relation("R = {}");
        for (int i = 1; i <= 7; i++) {
            testRelation.addVertex(i);
        }
        System.out.println(testRelation.vertices);
        for (int i = 1; i <= 7; i++) {
            testRelation.addEdge(i, rand.nextInt(7) + 1);
        }
        testRelation.addEdge(1,1);

        System.out.println(testRelation);
        System.out.println(testRelation.relationalProduct(testRelation));

    }

    public Set<Integer> vertices = new HashSet<>();
    public Set<Edge> edges = new HashSet<>();
    public String name;



    public Relation(String name, Set<Edge> Edges) {
        this.name = name;
        this.edges = Edges;
        for(Edge k : Edges) {
            vertices.add(k.from);
            vertices.add(k.to);
        }
    }

    public Relation(String Relation) { // Format: "R = {(2, 2), (1, 2)}"
        name = Relation.split(" ")[0];
        String[] Elemente = Relation.replaceAll("[\\D]", "|").split("|");
        // System.out.println(Arrays.toString(Elemente));
        int firstElement = -1;
        for (String el : Elemente) {
            try {
                if (firstElement == -1) {
                    firstElement = Integer.parseInt(el);
                } else {
                    addEdge(firstElement, Integer.parseInt(el));
                    firstElement = -1;
                }
            } catch(Exception e) {
                // System.out.println(e + " ");
            }
        }
    }

    public String toString() {
        String returnString = "" + name + " = {";
        if (edges.size() == 0) {
            return returnString + "}";
        }

        for (Edge k : edges) {
                    returnString += (k + ", ");
        }
        return returnString.substring(0,returnString.length() - 2) + "}";
    }

    public void addEdge(int from, int to) {
        vertices.add(from);
        vertices.add(to);
        edges.add(new Edge(from, to));
        // System.out.println("Added (" + from + ", " + to + ")");
    }


    public void addVertex(int vertex) {
        vertices.add(vertex);
    }

    public Relation relationalProduct(Relation other) { // Input another Relation and build the relational product in O(n²). Efishensee.
        Relation afterStep = new Relation(this.name + other.name);
        for (Edge edge : edges) {
            for (Edge otherEdge : other.edges) {
                if (edge.to == otherEdge.from) {
                    afterStep.addEdge(edge.from, otherEdge.to);
                }
            }
        }
        return afterStep;
    }

    public void randomRelation() {
        // To be continued...
    }

}
