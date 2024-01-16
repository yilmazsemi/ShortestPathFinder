import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import java.util.*;
import javax.swing.*;


public class BFS {
    private static Map<String, Integer> distanceMap;  // Move the distanceMap to class level

  public static void bfs(Graph graph, String startCity, String endCity, JTextArea textArea, JTextArea textArea2) {
      int[][] intMatrix = graph.getGraph();
      String[] cityNames = graph.getCityNames();

      // BFS logic
      Queue<String> queue = new LinkedList<>();
      Map<String, String> parentMap = new HashMap<>();
      distanceMap = new HashMap<>();  // Initialize distanceMap

      queue.offer(startCity);
      parentMap.put(startCity, null);
      distanceMap.put(startCity, 0);

      while (!queue.isEmpty()) {
          String current = queue.poll();

          for (int i = 0; i < cityNames.length; i++) {
              if (intMatrix[getCityIndex(current, cityNames)][i] != 0) {
                  String neighbor = cityNames[i];
                  int distanceToNeighbor = intMatrix[getCityIndex(current, cityNames)][i];
                  int totalDistance = distanceMap.get(current) + distanceToNeighbor;

                  if (!distanceMap.containsKey(neighbor) || totalDistance < distanceMap.get(neighbor)) {
                      distanceMap.put(neighbor, totalDistance);
                      parentMap.put(neighbor, current);
                      queue.offer(neighbor);
                  }
              }
          }
      }

      printBFSResult(parentMap, startCity, endCity, intMatrix, cityNames, textArea, textArea2);
  }

    // Helper method to format the path with distances
    private static void printBFSResult(Map<String, String> parentMap, String startCity, String endCity, int[][] intMatrix, String[] cityNames, JTextArea textArea, JTextArea textArea2) {
        List<String> path = new ArrayList<>();
        String at = endCity;

        while (at != null && !at.equals(startCity)) {
            path.add(at);
            at = parentMap.get(at);
        }

        if (at != null && at.equals(startCity)) {
            path.add(startCity);
            Collections.reverse(path);
        }

        System.out.println("BFS Shortest distance: " + distanceMap.get(endCity));
        System.out.println("BFS Shortest path: " + formatPath(path, intMatrix, cityNames));
        textArea.append("BFS Shortest distance: " + distanceMap.get(endCity));
        textArea2.append("BFS Shortest path: " + "\n⦿ " +formatPath(path, intMatrix, cityNames));
    }

    // Helper method to format the path with distances
    private static String formatPath(List<String> path, int[][] intMatrix, String[] cityNames) {
        StringBuilder formattedPath = new StringBuilder();
        int totalDistance = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            String currentCity = path.get(i);
            String nextCity = path.get(i + 1);
            int distance = intMatrix[getCityIndex(currentCity, cityNames)][getCityIndex(nextCity, cityNames)];

            formattedPath.append(currentCity).append(" \n🡇 ❬").append(distance).append(" km❭\n⦿ ");
            totalDistance += distance;
        }

        formattedPath.append(path.get(path.size() - 1));
        formattedPath.append(" (Total Distance: ").append(totalDistance).append(" km)");

        return formattedPath.toString();
    }
    // Helper method to get the index of a city
    private static int getCityIndex(String cityName, String[] cityNames) {
        for (int i = 0; i < cityNames.length; i++) {
            if (cityNames[i].equals(cityName)) {
                return i;
            }
        }
        throw new NullPointerException("Error: City not found.");
    }
}
