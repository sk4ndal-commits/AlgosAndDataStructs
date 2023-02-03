import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {

    public static void main(String[] args) {

        UndirectedGraph g = new UndirectedGraph();

        g.addVertex("sascha");
        g.addVertex("kann");
        g.addVertex("noch");
        g.addVertex("nicht");
        g.addVertex("kochen");

        g.addEdge("sascha", "kochen");
        g.addEdge("sascha", "nicht");


        g.printGraph();

        g.removeEdge("sascha", "nicht");

        System.out.println("################################");

        g.printGraph();

        System.out.println("################################");

        g.removeVertex("kochen");

        g.printGraph();
    }


    private static class UndirectedGraph {
        private final Map<String, List<String>> adjacencyList;

        UndirectedGraph() {
            adjacencyList = new HashMap<>();
        }


        public void addVertex(String vertex) {
            if (!adjacencyList.containsKey(vertex)) adjacencyList.put(vertex, new ArrayList<>());
        }

        public void addEdge(String from, String to) {
            this.addVertex(from);
            this.addVertex(to);

            this.adjacencyList.get(from).add(to);
            this.adjacencyList.get(to).add(from);
        }

        public void removeVertex(String vertex) {
            if (!this.adjacencyList.containsKey(vertex)) return;

            for (Entry<String, List<String>> mp : this.adjacencyList.entrySet()) {
                mp.getValue().remove(vertex);
            }

            this.adjacencyList.get(vertex).clear();
            this.adjacencyList.remove(vertex);
        }

        public void removeEdge(String from, String to) {
            if (!(this.adjacencyList.containsKey(from) && this.adjacencyList.containsKey(to))) return;

            this.adjacencyList.get(from).remove(to);
            this.adjacencyList.get(to).remove(from);
        }

        public void printGraph() {
            for (Entry<String, List<String>> mp : adjacencyList.entrySet()) {
                System.out.println("-----------------------");
                System.out.println("Vertex: " + mp.getKey());
                System.out.println("Values: " );
                mp.getValue().forEach(System.out::println);
            }
        }

    }
}
