
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {

    private static BufferedReader fileReader;
    private static Scanner scanner;

    public static void run(JSONArray wallsJSONArray, int startX, int startY, int goalX, int goalY) throws IOException, Floor.FloorException {
        System.out.println(wallsJSONArray);
        int floorSize = wallsJSONArray.length();
        Tile[][] tiles = new Tile[floorSize][floorSize];
        for (int i = 0; i < floorSize; i++) {
            JSONArray i_wallsJSONArray = wallsJSONArray.getJSONArray(i);
            for (int j = 0; j < floorSize; j++) {
                String wallClue = i_wallsJSONArray.getString(j);
                tiles[i][j] = new Tile(
                        i,
                        j,
                        wallClue.charAt(0) == '1',
                        wallClue.charAt(1) == '1',
                        wallClue.charAt(2) == '1',
                        wallClue.charAt(3) == '1'
                );
            }
        }
        scanner = new Scanner(System.in);
        Floor floor = new Floor(tiles, startX, startY, goalX, goalY);
        Robot robot = new Robot(floor);
        ArrayList<Tile> path = robot.search();
        printArrayList(path);
        FloorUI.printPath(path);
    }

    private static String readFile(BufferedReader fileReader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line = fileReader.readLine();
        while (line != null) {
            content.append(line);
            line = fileReader.readLine();
        }
        return content.toString();
    }

    private static void printArrayList(ArrayList list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print("->" + list.get(i));
        }
        System.out.println();
    }
}
