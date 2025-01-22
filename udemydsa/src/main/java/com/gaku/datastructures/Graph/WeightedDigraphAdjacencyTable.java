package com.gaku.datastructures.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 加权有向图的通用实现（邻接表）
@SuppressWarnings("rawtypes")
public class WeightedDigraphAdjacencyTable implements MyGraphIF {

    // 存储相邻节点及边的权重
    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    private static class State {
        int node;
        int weight;

        State(int node, int weight) {
            this.node = node;
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
        // remove(object) => O(n)
        // for (Edge edge : graph[from]) {
        // if (edge.to == to) {
        // graph[from].remove(edge);
        // // no need to continue after remove
        // break;
        // }
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

    public void dfsAllVertex() {
        boolean[] visited = new boolean[graph.length];
        traverseVtx(this, 0, visited);
    }

    public void traverseVtx(WeightedDigraphAdjacencyTable graph, int s, boolean[] visited) {
        // base case
        if (s < 0 || s >= graph.size()) {
            return;
        }
        // avoid cicle
        if (visited[s]) {
            return;
        }

        visited[s] = true;
        System.out.println("visit " + s);

        for (Edge e : graph.neighbors(s)) {
            traverseVtx(graph, e.to, visited);
        }
    }

    // find all paath from src to dest
    public void dfsAllPath(int src, int dest) {
        // vertex on curent path
        boolean[] onPath = new boolean[graph.length];
        List<Integer> path = new LinkedList<>();
        traversePath(this, src, dest, onPath, path);
    }

    private void traversePath(WeightedDigraphAdjacencyTable graph, int src, int dest, boolean[] onPath,
            List<Integer> path) {
        if (src < 0 || src >= graph.size()) {
            return;
        }

        // if a vertex has been visited, then return
        if (onPath[src]) {
            return;
        }

        onPath[src] = true;
        path.add(src);
        if (src == dest) {
            System.out.println("find path: " + path);
        }

        for (Edge e : graph.neighbors(src)) {
            traversePath(graph, e.to, dest, onPath, path);
        }

        onPath[src] = false;
        path.remove(path.size() - 1);
    }

    public void bfs() {
        bfs0(this, 0);
        bfs1(this, 0);
        bfs2(this, 0);
    }

    private void bfs0(WeightedDigraphAdjacencyTable graph, int src) {
        if (src < 0 || src >= graph.size()) {
            return;
        }

        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            System.out.println("visit " + cur);
            for (Edge e : graph.neighbors(cur)) {
                if (!visited[e.to]) {
                    queue.offer(e.to);
                    // add into queue, means it will be visited, so set ture
                    visited[e.to] = true;
                }
            }
        }
    }

    private void bfs1(WeightedDigraphAdjacencyTable graph, int src) {
        if (src < 0 || src >= graph.size()) {
            return;
        }

        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(src);
        visited[src] = true;
        int step = 0;

        while (!queue.isEmpty()) {
            // all node in same level in the queue
            for (int i = 0; i < queue.size(); i++) {                
                int cur = queue.poll();
                System.out.println("visit " + cur+" step " + step);
                for (Edge e : graph.neighbors(cur)) {
                    if (!visited[e.to]) {
                        queue.offer(e.to);
                        // add into queue, means it will be visited, so set ture
                        visited[e.to] = true;
                    }
                }
            }
            step++;
        }
    }

    private void bfs2(WeightedDigraphAdjacencyTable graph, int src) {

        boolean[] visited = new boolean[graph.size()];
        Queue<State> queue = new LinkedList<>();

        queue.offer(new State(src, 0));
        visited[src] = true;

        while (!queue.isEmpty()) {
            State state = queue.poll();
            int cur = state.node;
            int weight = state.weight;
            System.out.println("visit " + cur + " with path weight " + weight);
            for (Edge e : graph.neighbors(cur)) {
                if (!visited[e.to]) {
                    queue.offer(new State(e.to, weight+e.weight));
                    // add into queue, means it will be visited, so set ture
                    visited[e.to] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        // WeightedDigraphAdjacencyTable graph = new WeightedDigraphAdjacencyTable(7);
        // graph.addEdge(0, 1, 1);
        // graph.addEdge(0, 2, 1);
        // graph.addEdge(1, 3, 2);
        // graph.addEdge(1, 4, 2);
        // graph.addEdge(2, 5, 3);
        // graph.addEdge(2, 6, 4);
        // graph.addEdge(3, 6, 4);
        // graph.addEdge(6, 0, 4);
        // graph.addEdge(3, 0, 4);
        WeightedDigraphAdjacencyTable graph = new WeightedDigraphAdjacencyTable(5);
        graph.addEdge(0, 3, 1);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 4, 2);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 1, 4);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 0, 4);

        System.out.println(graph.hasEdge(0, 1));
        System.out.println(graph.hasEdge(1, 0));

        graph.neighbors(2).forEach(edge -> {
            System.out.println(2 + " -> " + edge.to + ", wight: " + edge.weight);
        });

        // graph.removeEdge(0, 1);
        System.out.println(graph.hasEdge(0, 1));
        graph.dfsAllVertex();
        graph.dfsAllPath(0, 4);
        graph.bfs();
    }
}
