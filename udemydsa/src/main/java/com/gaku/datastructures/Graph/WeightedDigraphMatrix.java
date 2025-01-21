package com.gaku.datastructures.Graph;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class WeightedDigraphMatrix implements MyGraphIF{

    public static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private int[][] matrix;

    public WeightedDigraphMatrix(int size) {
        matrix = new int[size][size];
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        matrix[from][to] = weight;
    }

    @Override
    public void removeEdge(int from, int to) {
        matrix[from][to] = 0;
    }

    @Override
    public boolean hasEdge(int from, int to) {
        return matrix[from][to] != 0;
    }

    @Override
    public int weight(int from, int to) {
        return matrix[from][to];
    }

    @Override
    public List<Edge> neighbors(int v) {
        List<Edge> res = new ArrayList<>();
        for (int i = 0; i < matrix[v].length; i++) {
            if (matrix[v][i] != 0) {
                res.add(new Edge(i, matrix[v][i]));
            }
        }
        return res;
    }

    @Override
    public int size() {
        return matrix.length;
    }
    
    public static void main(String[] args) {
        WeightedDigraphMatrix graph = new WeightedDigraphMatrix(3);
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
