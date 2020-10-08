import sun.security.ssl.Debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GamePlayManager {

    private static int hx, hy;

    public static void initMyHead(String snakeDesc) {
        String[] snakePoints = snakeDesc.split(" ");
        String[] headXY = snakePoints[3].split(",");

        hx = Integer.parseInt(headXY[0]);
        hy = Integer.parseInt(headXY[1]);
    }

    public static Node getHeadNode() {
        return new Node(hx, hy);

    }

    public static int getMove(Node next) {

        int move = 5;

        if (hy == next.getY()) {
            if (hx < next.getX()) {
                move = 3;
            } else {
                move = 2;
            }
        } else if (hx == next.getX()) {
            if (hy < next.getY()) {
                return 1;
            } else {
                return 0;
            }

        }

        return move;
    }

    public static int makeMove(Double appleHealth) {

        Node src = new Node(hx, hy);
        Node dest = GameBoardManager.getApplePoint();

        ArrayList<Node> path = AstarAlgorithmn.getPath(src, dest);

        if (!path.isEmpty()){
            return getMove(path.get(path.size()-1));
        }else{
            return 5;
        }
    }
}
