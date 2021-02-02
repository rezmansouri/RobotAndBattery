
import java.util.ArrayList;

class Floor {

    Tile[][] tiles;
    Tile start;
    Tile goal;

    Floor(Tile[][] tiles, int startX, int startY, int goalX, int goalY) {
        this.tiles = tiles;
        start = tiles[startX][startY];
        goal = tiles[goalX][goalY];
        setTilesHeuristics();
    }

    private void setTilesHeuristics() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j].distanceFromGoal = Math.abs(i - goal.x) + Math.abs(j - goal.y);
            }
        }
    }

    public ArrayList<Tile> getMoves(Tile tile) throws FloorException {
        ArrayList<Tile> successors = new ArrayList<>();
        try {
            if (!tile.WALL_ON_UP) {
                successors.add(tiles[tile.x - 1][tile.y]);
            }
            if (!tile.WALL_ON_RIGHT) {
                successors.add(tiles[tile.x][tile.y + 1]);
            }
            if (!tile.WALL_ON_DOWN) {
                successors.add(tiles[tile.x + 1][tile.y]);

            }
            if (!tile.WALL_ON_LEFT) {
                successors.add(tiles[tile.x][tile.y - 1]);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new FloorException("Exception in Tile: " + tile + "\nIt Doesn't Match Regulations");
        }

        return successors;
    }

    class FloorException extends Exception {

        FloorException(String msg) {
            super(msg);
        }
    }
}
