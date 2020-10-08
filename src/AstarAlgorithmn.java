import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class AstarAlgorithmn {

    public static ArrayList<Node> getPath(Node src, Node dest) {

        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        openList.add(src);

        while (!openList.isEmpty()) {

            Node parent = openList.remove(0);
            closedList.add(parent);

            ArrayList<Node> children = parent.getChildren();

            for (Node child : children) {
                if (closedList.contains(child)) continue;
                if (openList.contains(child)) {
                    if (child.getG() < parent.getG() + 1) {
                        child.setParent(parent);
                        child.setG(parent.getG() + 1);
                    }

                } else {
                    child.setParent(parent);
                    child.setG(parent.getG() + 1);
                    child.setH(getDistance(child, dest));
                    openList.add(child);
                }

                if (child.equals(dest)) {
                    ArrayList<Node> path = new ArrayList<>();

                    path.add(child);
                    child = child.getParent();

                    while (child.getParent() != null && !child.equals(src)) {
                        path.add(child);
                        child = child.getParent();

                    }
                    return path;

                }
            }

            Collections.sort(openList);

        }

        return new ArrayList<>(); // since it's possible for there to be no path
    }

    public static int getDistance(Node n1, Node n2) {
        return Math.abs(n1.getX() - n2.getX()) + Math.abs(n1.getY() - n2.getY());

    }

    public static int getNodeNumber(Node src) {
        int u = -1;
        for (int i = 0; i < GameBoardManager.height; i++) {
            for (int j = 0; j < GameBoardManager.width; j++) {
                Node check = new Node(i, j);
                if (!check.equals(src)) {
                    u = u + 1;
                } else {
                    return u;
                }
            }
        }

        return u;
    }

    public static Node getNodeFromIdx(int idx) {
        int u = 0;
        for (int i = 0; i < GameBoardManager.height; i++) {
            for (int j = 0; j < GameBoardManager.width; j++) {
                Node check = new Node(j, i);
                if (u != idx) {
                    u = u + 1;
                } else {
                    return check;
                }
            }
        }

        return null;

    }

}
