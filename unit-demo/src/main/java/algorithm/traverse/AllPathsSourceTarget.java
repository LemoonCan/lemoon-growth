package algorithm.traverse;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有可能的路径
 * https://leetcode-cn.com/problems/all-paths-from-source-to-target/
 *
 * @author lee
 * @since 2021/11/24
 */
public class AllPathsSourceTarget {
    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        AllPathsSourceTarget ap = new AllPathsSourceTarget();
        System.out.println(ap.allPathsSourceTarget(graph));
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        dfs(graph, 0, paths, new ArrayList<>());
        return paths;
    }

    void dfs(int[][] graph, int vertex, List<List<Integer>> paths, List<Integer> path) {
        path.add(vertex);
        if (vertex == graph.length - 1) {
            paths.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        for (int i = 0; i < graph[vertex].length; i++) {
            dfs(graph, graph[vertex][i], paths, path);
        }
        path.remove(path.size() - 1);
    }
}
