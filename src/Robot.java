import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Robot {
    Floor floor;

    public Robot(Floor floor) {
        this.floor = floor;
    }

    public ArrayList<Tile> search() throws Floor.FloorException {
        Node start = new Node(floor.start, new ArrayList<>(), 0 + floor.start.distanceFromGoal);
        HashSet<Node> fringe = new HashSet<>();
        fringe.add(start);
        HashSet<Node> closed = new HashSet<>();
        while (!fringe.isEmpty()) {
            System.out.print("Fringe");
            printSet(fringe);
            System.out.print("Closed");
            printSet(closed);
            System.out.println("******");
            Iterator<Node> iterator = fringe.iterator();
            Node toExpand = null;
            int minF = Integer.MAX_VALUE;
            while (iterator.hasNext()) {
                Node temp = iterator.next();
                if (temp.tile.distanceFromGoal == 0) {
                    return temp.path;
                }
                if (temp.cost + 1 < minF) {
                    toExpand = temp;
                    minF = temp.cost + 1;
                }
            }
            fringe.remove(toExpand);
            ArrayList<Tile> possibleTiles = floor.getMoves(toExpand.tile);
            for (int i = 0; i < possibleTiles.size(); i++) {
                Tile temp = possibleTiles.get(i);
                ArrayList<Tile> path = (ArrayList<Tile>) toExpand.path.clone();
                path.add(toExpand.tile);
                Node newSuccessor = new Node(temp, path, toExpand.cost + 1);
                Iterator<Node> fringeIteratorToCheck = fringe.iterator();
                Iterator<Node> closedIteratorToCheck = closed.iterator();
                boolean foundInFringe = false;
                while (fringeIteratorToCheck.hasNext()) {
                    Node fringeNode = fringeIteratorToCheck.next();
                    if (fringeNode.compareTo(newSuccessor) == 0) {
                        foundInFringe = true;
                        if (fringeNode.cost > newSuccessor.cost) {
                            fringe.remove(fringeNode);
                            fringe.add(newSuccessor);
                            break;
                        }
                    }
                }
                boolean foundInClosed = false;
                while (closedIteratorToCheck.hasNext()) {
                    Node closedNode = closedIteratorToCheck.next();
                    if (closedNode.compareTo(newSuccessor) == 0) {
                        foundInClosed = true;
                        if (closedNode.cost > newSuccessor.cost) {
                            closed.remove(closedNode);
                            fringe.add(newSuccessor);
                            break;
                        }
                    }
                }
                if (!foundInFringe && !foundInClosed) {
                    fringe.add(newSuccessor);
                }
            }
            closed.add(toExpand);
        }
        return null;
    }

    class Node implements Comparable<Node> {
        Tile tile;
        ArrayList<Tile> path;
        int cost;

        Node(Tile tile, ArrayList<Tile> path, int cost) {
            this.tile = tile;
            this.path = path;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return (o.tile.x == tile.x && o.tile.y == tile.y) ? 0 : 1;
        }

        public String toString() {
            return tile.toString();
        }
    }

    private static void printSet(HashSet set) {
        Object[] list = set.toArray();
        for (int i = 0; i < list.length; i++) {
            System.out.print("->" + list[i]);
        }
        System.out.println();
    }
}
