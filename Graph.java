import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    private static final int INF = Integer.MAX_VALUE;

    private int[][] graph;
    private String[] cityNames;
    private Map<String, Map<String, Integer>> adjacencyList;

    public Graph(String csvFilename) {
        readCSVFile(csvFilename);
        buildAdjacencyList();
    }

    private void readCSVFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] cities = br.readLine().split(",");
            cityNames = Arrays.copyOfRange(cities, 1, cities.length);

            int size = cityNames.length;
            graph = new int[size][size];

            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != size + 1) {
                    throw new IOException("Invalid number of columns in CSV");
                }
                for (int col = 1; col < values.length; col++) {
                    if (values[col].equals("INF")) {
                        graph[row][col - 1] = INF;
                    } else {
                        graph[row][col - 1] = Integer.parseInt(values[col]);
                    }
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildAdjacencyList() {
        adjacencyList = new HashMap<>();

        for (int i = 0; i < cityNames.length; i++) {
            Map<String, Integer> neighbors = new HashMap<>();

            for (int j = 0; j < cityNames.length; j++) {
                if (graph[i][j] != INF) {
                    neighbors.put(cityNames[j], graph[i][j]);
                }
            }

            adjacencyList.put(cityNames[i], neighbors);
        }
    }

    public int[][] getGraph() {
        return graph;
    }

    public String[] getCityNames() {
        return cityNames;
    }

    public Map<String, Map<String, Integer>> getAdjacencyList() {
        return adjacencyList;
    }
}

