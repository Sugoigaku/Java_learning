package com.gaku.datastructures.Graph;

import java.util.ArrayList;
import java.util.List;

// 加权有向图的通用实现（邻接表）
@SuppressWarnings("rawtypes")
public class WeightedDigraphAdjacencyTable implements MyGraphIF {

    // 存储相邻节点及边的权重
    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private List<Edge>[] graph;

    public WeightedDigraphAdjacencyTable(int n) {
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        graph[from].add(new Edge(to, weight));
    }

    @Override
    public void removeEdge(int from, int to) {
        //  remove(object) => O(n)
        // for (Edge edge : graph[from]) {
        //     if (edge.to == to) {
        //         graph[from].remove(edge);
        //         // no need to continue after remove
        //         break;
        //     }
        // }
        for (int i = 0; i < graph[from].size(); i++) {
            if (graph[from].get(i).to == to) {
                graph[from].remove(i);
                // no need to continue after remove
                break;
            }
        }
    }

    @Override
    public boolean hasEdge(int from, int to) {
        for (Edge edge : graph[from]) {
            if (edge.to == to) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int weight(int from, int to) {
        if (hasEdge(from, to)) {
            for (Edge edge : graph[from]) {
                if (edge.to == to) {
                    return edge.weight;
                }
            }
        }
        // return 0;
        throw new IllegalArgumentException("No such edge");
    }

    @Override
    public List<Edge> neighbors(int v) {
        return graph[v];
    }

    @Override
    public int size() {
        return graph.length;
    }

    public static void main(String[] args) {
        WeightedDigraphAdjacencyTable graph = new WeightedDigraphAdjacencyTable(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 0, 3);
        graph.addEdge(2, 1, 4);

        System.out.println(graph.hasEdge(0, 1)); // true
        System.out.println(graph.hasEdge(1, 0)); // false

        graph.neighbors(2).forEach(edge -> {
            System.out.println(2 + " -> " + edge.to + ", wight: " + edge.weight);
        });
        // 2 -> 0, wight: 3
        // 2 -> 1, wight: 4

        graph.removeEdge(0, 1);
        System.out.println(graph.hasEdge(0, 1)); // false
    }
}
