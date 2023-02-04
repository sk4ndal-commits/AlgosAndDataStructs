import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        WeightedGraph<String> g = new WeightedGraph<>();

        g.addEdgeWithWeight("A", "B", 4);
        g.addEdgeWithWeight("A", "C", 2);
        g.addEdgeWithWeight("B", "E", 3);
        g.addEdgeWithWeight("C", "D", 2);
        g.addEdgeWithWeight("C", "F", 4);
        g.addEdgeWithWeight("D", "F", 1);
        g.addEdgeWithWeight("D", "E", 3);
        g.addEdgeWithWeight("E", "F", 1);


        //g.printGraph();

        g.shortestPath("A", "B");

    }

    private static class WeightedGraph<T> {
        private final Map<T, List<WeightedEdge<T>>> adjacencyList;

        WeightedGraph() {
            adjacencyList = new HashMap<>();
        }

        public void addVertex(T vertex) {
            if (!adjacencyList.containsKey(vertex)) adjacencyList.put(vertex, new ArrayList<>());
        }

        public void addEdgeWithWeight(T from, T to, int weight) {
            addVertex(from);
            addVertex(to);

            adjacencyList.get(from).add(new WeightedEdge<>(to, weight));
            adjacencyList.get(to).add(new WeightedEdge<>(from, weight));
        }

        public void printGraph() {
            for (Map.Entry<T, List<WeightedEdge<T>>> mp : this.adjacencyList.entrySet()) {
                System.out.println("from: " + mp.getKey());
                mp.getValue().forEach(System.out::println);
            }
        }

        /**
         * uses Dijkstra Algorithm
         * <p>
         * 1  function Dijkstra(Graph, source):
         *         // distance from each node to the start node
         * 2      dist[source] ← 0                           // Initialization
         * 3
         *          // stores node + weight to get there from starting node
         * 4      create vertex priority queue Q
         * 5
         * 6      for each vertex v in Graph.Vertices:
         * 7          if v ≠ source
         * 8              dist[v] ← INFINITY                 // Unknown distance from source to v
         * 9              prev[v] ← UNDEFINED                // Predecessor of v
         * 10
         * 11         Q.add_with_priority(v, dist[v])
         * 12
         * 13
         * 14     while Q is not empty:                      // The main loop
         * 15         u ← Q.extract_min()                    // Remove and return best vertex
         * 16         for each neighbor of u:              // Go through all v neighbors of u
         * 17             alt ← dist[u] + Graph.Edges(u, neighbour)
         * 18             if alt < dist[neighbour]:
         * 19                 dist[neighbour] ← alt
         * 20                 prev[neighbour] ← u
         * 21                 Q.decrease_priority(neighbour, alt)
         * 22
         * 23     return dist, prev
         * <p/>
         * @param from starting node
         * @param to ending node
         */
        public void shortestPath(T from, T to) {
            Map<T, Integer> dist = new HashMap<>();
            Map<T, T> prev = new HashMap<>();
            Queue<WeightedEdge<T>> queue = new PriorityQueue<>();
            List<T> path;
            T current;
            int newWeight;

            dist.put(from, 0);
            prev.put(from, null);

            int INF = (int) 1e9;

            for (T vertex : this.adjacencyList.keySet()) {
                if (vertex != from) {
                    dist.put(vertex, INF);
                    prev.put(vertex, null);
                }

                queue.add(new WeightedEdge<>(vertex, dist.get(vertex)));
            }

            while (!queue.isEmpty()) {
                current = queue.remove().to;

                for (WeightedEdge<T> neighbour : this.adjacencyList.get(current)) {
                    newWeight = dist.get(current) + neighbour.weight;

                    if (newWeight < dist.get(neighbour.to)) {
                        dist.remove(neighbour.to);
                        dist.put(neighbour.to, newWeight);

                        prev.remove(neighbour.to);
                        prev.put(neighbour.to, current);

                        queue.remove(neighbour);
                        queue.add(new WeightedEdge<>(neighbour.to, newWeight));

                    }
                }
            }


            path = buildPath(prev, to);

            System.out.println("path we look for:\t\t " + from + "->" + to);
            System.out.println("path to take:\t\t\t " + path);
            System.out.println("shortest path weight:\t " + dist.get(to));
        }

        private List<T> buildPath(Map<T, T> prev, T vertex) {

            List<T> result = new ArrayList<>();

            while (vertex != null) {
                result.add(vertex);
                vertex = prev.get(vertex);
            }

            return result;
        }

    }

    private static class WeightedEdge<T> implements Comparable<WeightedEdge<T>> {
        T to;
        int weight;

        WeightedEdge(T to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return " to: " + this.to + " with weight: " + this.weight;
        }

        @Override
        public int compareTo(@NotNull WeightedEdge<T> o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
