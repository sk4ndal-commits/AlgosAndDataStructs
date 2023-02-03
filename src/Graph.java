import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {

    public static void main(String[] args) {

        UndirectedGraph<String> g = new UndirectedGraph<>();

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


    private static class UndirectedGraph<T> {
        private final Map<T, List<T>> adjacencyList;

        UndirectedGraph() {
            adjacencyList = new HashMap<>();
        }


        public void addVertex(T vertex) {
            if (!adjacencyList.containsKey(vertex)) adjacencyList.put(vertex, new ArrayList<>());
        }

        public void addEdge(T from, T to) {
            this.addVertex(from);
            this.addVertex(to);

            this.adjacencyList.get(from).add(to);
            this.adjacencyList.get(to).add(from);
        }

        public void removeVertex(T vertex) {
            if (!this.adjacencyList.containsKey(vertex)) return;

            while (this.adjacencyList.get(vertex).size()  > 0) {
                T key = this.adjacencyList.get(vertex).get(0);
                this.removeEdge(key, vertex);
            }

            this.adjacencyList.get(vertex).clear();
            this.adjacencyList.remove(vertex);
        }

        public void removeEdge(T from, T to) {
            if (!(this.adjacencyList.containsKey(from) && this.adjacencyList.containsKey(to))) return;

            this.adjacencyList.get(from).remove(to);
            this.adjacencyList.get(to).remove(from);
        }

        public void printGraph() {
            for (Entry<T, List<T>> mp : adjacencyList.entrySet()) {
                System.out.println("-----------------------");
                System.out.println("Vertex: " + mp.getKey());
                System.out.println("Values: " );
                mp.getValue().forEach(System.out::println);
            }
        }

    }
}
