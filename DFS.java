import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import javax.swing.*;


public class DFS {
    private static int shortestDistance = Integer.MAX_VALUE;
    private static List<String> shortestPath = new ArrayList<>();

    public static void dfs(Graph graph, String startCity, String endCity, int depthLimit, JTextArea resultTextArea, JTextArea resultTextArea2) {
    	 
        String[] cityNames = graph.getCityNames();

        int INF = Integer.MAX_VALUE;

        ArrayList<String> dfsPath = new ArrayList<>();
        boolean[] dfsVisited = new boolean[cityNames.length];
        int dfsSum = 0;

        dfsPath.add(startCity); // Include the starting city in the path

        // Call the modified DFS method
        depthLimitedSearch(graph.getAdjacencyList(), startCity, endCity, 0, dfsPath, dfsVisited, dfsSum, INF, depthLimit);

        // Display the results
        if (shortestDistance == Integer.MAX_VALUE) {
            System.out.println("No path found using DFS with depth limit");
        } else {
            System.out.println("DFS with depth limit Shortest distance: " + shortestDistance);
            System.out.println("DFS with depth limit Shortest path: \n\n⦿" + formatPath(shortestPath, graph));
            resultTextArea.setText("DFS with depth limit Shortest distance: " + shortestDistance);
            resultTextArea2.setText("DFS with depth limit Shortest path: \n⦿ " + formatPath(shortestPath, graph));
          //  text.append("DFS with depth limit Shortest path: " + formatPath(shortestPath, graph));
        
            //"DFS with depth limit Shortest distance: " + shortestDistance
            
        }
    }

    // Modified DFS function for finding the path with depth limit
    private static void depthLimitedSearch(Map<String, Map<String, Integer>> adjacencyList, String startCity, String endCity,
                                           int currentDepth, ArrayList<String> currentPath, boolean[] visited, int sum, int INF, int depthLimit) {
        if (startCity.equals(endCity)) { // If the start City is the same as the end city
            int distance = calculateTotalDistance(currentPath, adjacencyList); // Calculates the total distance
            if (distance < shortestDistance) { // If this path is shorter
                shortestDistance = distance; // Updates the shortest path length
                shortestPath = new ArrayList<>(currentPath); // Updates the shortest path
            }
            return; // Exits DFS
        }

        if (currentDepth >= depthLimit) { // If the depth limit has been reached
            return; // Exits DFS
        }

        visited[getCityIndex(startCity, adjacencyList)] = true;

        Map<String, Integer> neighbors = adjacencyList.get(startCity); // Gets the neighbors of the current city
        for (Map.Entry<String, Integer> neighbor : neighbors.entrySet()) { // For each neighbor
            String nextCity = neighbor.getKey(); // Gets the neighboring city
            int distance = neighbor.getValue(); // Gets the distance between cities

            if (distance < 99999 && !visited[getCityIndex(nextCity, adjacencyList)]) { // If this path is valid
                currentPath.add(nextCity); // Adds the neighboring city to the temporary path
                depthLimitedSearch(adjacencyList, nextCity, endCity, currentDepth + 1, currentPath, visited, sum, INF, depthLimit); // Calls DFS
                currentPath.remove(currentPath.size() - 1); // Removes the last added city from the path
            }
        }

        visited[getCityIndex(startCity, adjacencyList)] = false;
    }

  // Total distance method for Limited DFS class
  private static int calculateTotalDistance(ArrayList<String> path, Map<String, Map<String, Integer>> adjacencyList) {
      int totalDistance = 0;
      for (int i = 0; i < path.size() - 1; i++) {
          String currentCity = path.get(i);
          String nextCity = path.get(i + 1);
          // Assuming adjacencyList is a Map<String, Map<String, Integer>>
          totalDistance += adjacencyList.get(currentCity).get(nextCity);
      }
      return totalDistance;
  }

    // Helper method to get the index of a city
    private static int getCityIndex(String cityName, Map<String, Map<String, Integer>> adjacencyList) {
        int i = 0;
        for (String city : adjacencyList.keySet()) {
            if (city.equals(cityName)) {
                return i;
            }
            i++;
        }
        throw new NullPointerException("Error: City not found.");
    }

  private static String formatPath(List<String> path, Graph graph) {
    StringBuilder formattedPath = new StringBuilder();
    int totalDistance = 0;

    Map<String, Map<String, Integer>> adjacencyList = graph.getAdjacencyList(); // Added this line

    for (int i = 0; i < path.size() - 1; i++) {
        String currentCity = path.get(i);
        String nextCity = path.get(i + 1);
        int distance = adjacencyList.get(currentCity).get(nextCity);

        formattedPath.append(currentCity).append(" \n🡇 ❬").append(distance).append(" km❭\n⦿ ");
        totalDistance += distance;
    }

    formattedPath.append(path.get(path.size() - 1));
    formattedPath.append(" (Total Distance: ").append(totalDistance).append(" km)");

    return formattedPath.toString();
  }
}