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

        System.out.println("-----------------");
        int mostEdges = 0;
        Relation best = new Relation("R");
        int[][] whatItShouldLookLike = {{1,2},{3,4}, {5,6}};
        for (int i = 0; i < 10000; i++) {
            Relation test2 = new Relation("A = {}");
            test2 = test2.randomRelation(7);
            Relation ogtest2 = test2.copy();
            test2 = test2.relationalProduct(test2);
            if (test2.allEdgesBetween(whatItShouldLookLike)) {
                if (test2.edges.size() > mostEdges) {
                    mostEdges = test2.edges.size();
                    best = test2;
                }
            }
        }
        System.out.println(mostEdges);
        System.out.println(best);
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

        afterStep.vertices = this.vertices;
        for (Edge edge : edges) {
            for (Edge otherEdge : other.edges) {
                if (edge.to == otherEdge.from) {
                    afterStep.addEdge(edge.from, otherEdge.to);
                }
            }
        }
        return afterStep;
    }

    public Relation transitiveClosure() {
        Relation[] TC = {new Relation(this.name), this.copy()};
        do {
            TC[0] = TC[1].copy();
            for (Edge edge : TC[0].edges) {
                for (Edge otherEdge : TC[1].edges) {
                    if (edge.to == otherEdge.from) {
                        TC[1].addEdge(edge.from, otherEdge.to);
                        break;
                    }
                }
            }
            // System.out.println(TC[0].equals(TC[1]));
        } while (!TC[0].equals(TC[1]));
        TC[1].name += "+";
        return TC[1];
    }

    public Relation reflexiveClosure() {
        Relation RC = this.copy();
        for (int vertex : vertices) {
            RC.addEdge(vertex, vertex);
        }
        RC.name += "*";
        return RC;
    }

    public Relation reflexiveTransitiveClosure() {
        return this.reflexiveClosure().transitiveClosure();
    }

    public Relation randomRelation() {
        return randomRelation(this.vertices.size(), new Random().nextInt(vertices.size() * vertices.size() + 1));
    }

    public Relation randomRelation(int numberOfVertices) {
        return randomRelation(numberOfVertices, new Random().nextInt(numberOfVertices * numberOfVertices) + 1);
    }

    public Relation randomRelation(int numberOfVertices, int numberOfRandomlyGeneratedEdges) {
        Relation randy = new Relation(this.name);

        for (int i = 1; i <= numberOfVertices; i++) {
            randy.addVertex(i);
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfRandomlyGeneratedEdges; i++) {
            int generatedEdge = rand.nextInt(numberOfVertices * numberOfVertices) + 1;
            randy.addEdge(generatedEdge/numberOfVertices + 1, generatedEdge % 3 + 1);
        }
        return randy;
    }

    public Relation copy() {
        return new Relation(this.toString());
    }

    public boolean allEdgesBetween(int[][] colorBoard) {
        boolean found = false;
        for (Edge edge : edges) {
            for (int[] searchEdge : colorBoard) {
                if (edgeIsIn(edge, searchEdge[0], searchEdge[1])){
                    found = true;
                    break;
                }
            }
            if(!found) {
                return false;
            }
            found = false;

        }
        return true;
    }

    public boolean containsEdgeBetween(int a, int b) {
        if (edges.contains(new Edge(a, b)) || edges.contains(new Edge(b, a))) {
            return true;
        }
        return false;
    }

    public boolean edgeIsIn(Edge edge, int a, int b) {
        if (edge.from == a && edge.to == b
        ||  edge.from == b && edge.to == a
        ||  edge.from == a && edge.to == a
        ||  edge.from == b && edge.to == b) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Relation relation = (Relation) o;

        if (vertices != null ? !vertices.equals(relation.vertices) : relation.vertices != null) return false;
        return edges != null ? edges.equals(relation.edges) : relation.edges == null;
    }

    @Override
    public int hashCode() {
        int result = vertices != null ? vertices.hashCode() : 0;
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        return result;
    }
}
