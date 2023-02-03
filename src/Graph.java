import java.util.*;
import java.util.Map.Entry;

public class Graph {

    public static void main(String[] args) {

        UndirectedGraph<Integer> g = new UndirectedGraph<>();

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.DFS(2);

        System.out.println();

        g.BFS(2);
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

        public void BFS(T startVertex) {
            Set<T> visited = new HashSet<>();

            BFSUtil(startVertex, visited);
        }

        private void BFSUtil(T vertex, Set<T> visited) {
            Queue<T> vertices = new LinkedList<>();
            vertices.add(vertex);
            visited.add(vertex);
            T currentVertex;

            while (!vertices.isEmpty()) {
                currentVertex = vertices.remove();

                System.out.print(currentVertex.toString() + ' ');

                for (T neighbour : this.adjacencyList.get(currentVertex)) {
                    if (!visited.contains(neighbour)) {

                        vertices.add(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
        }

        public void DFS(T startVertex) {
            Set<T> visited = new HashSet<>();

            DFSUtil(startVertex, visited);
        }

        private void DFSUtil(T vertex, Set<T> visited) {


            visited.add(vertex);
            System.out.print(vertex.toString() + ' ');

            for (T neighbour : this.adjacencyList.get(vertex)) {
                if (!visited.contains(neighbour)) DFSUtil(neighbour, visited);
            }
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
