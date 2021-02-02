class Tile {
    int distanceFromGoal;
    int x, y;
    boolean WALL_ON_UP, WALL_ON_RIGHT, WALL_ON_DOWN, WALL_ON_LEFT;

    Tile(int x, int y, boolean WALL_ON_UP, boolean WALL_ON_RIGHT, boolean WALL_ON_DOWN, boolean WALL_ON_LEFT) {
        this.x = x;
        this.y = y;
        this.WALL_ON_UP = WALL_ON_UP;
        this.WALL_ON_RIGHT = WALL_ON_RIGHT;
        this.WALL_ON_DOWN = WALL_ON_DOWN;
        this.WALL_ON_LEFT = WALL_ON_LEFT;
    }

    public String toString() {
        return x + ":" + y;
    }
}