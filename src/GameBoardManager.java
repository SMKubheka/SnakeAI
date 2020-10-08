import sun.security.ssl.Debug;

public class GameBoardManager {

    public static int height, width;
    public static int[][] board;
    private static int ax, ay;

    public static Node getApplePoint() {
        return new Node(ax, ay);

    }

    public static void initBoard(int height, int width) {
        GameBoardManager.height = height;
        GameBoardManager.width = width;
        board = new int[height][width];
    }


    public static void initAppleCoord(String[] apple) {
        ax = Integer.parseInt(apple[0]);
        ay = Integer.parseInt(apple[1]);

    }

    public static void drawObstacle(String obs) { //2,3 1,5 3,2
        String[] obsPoint = obs.split(" ");
        for (int i = 1; i < obsPoint.length; i++) {
            drawLine(obsPoint[i - 1], obsPoint[i]);
        }
    }

    public static void drawSnake(String snakeLine) { // alive 26 2 10,12 15,12 15,7 5,7 5,2
        String[] snakePoints = snakeLine.split(" ");
        if (snakePoints[0].equals("alive")) {
            for (int i = 4; i < snakePoints.length; i++) { // snake head at pos 3
                drawLine(snakePoints[i - 1], snakePoints[i]);
            }
        }
    }

    public static void drawLine(String point1, String point2) {
        String[] point1XY = point1.split(",");
        String[] point2XY = point2.split(",");

        int p1X = Integer.parseInt(point1XY[0]);
        int p1Y = Integer.parseInt(point1XY[1]);

        int p2X = Integer.parseInt(point2XY[0]);
        int p2Y = Integer.parseInt(point2XY[1]);

        if (p1X == p2X) {
            int i = Integer.min(p1Y, p2Y);
            int j = Integer.max(p1Y, p2Y);

            for (; i <= j; i++) {
                board[i][p1X] = 1;
                addColliders(GamePlayManager.getHeadNode(), p1X, i);
            }
        } else {
            int i = Integer.min(p1X, p2X);
            int j = Integer.max(p1X, p2X);

            for (; i <= j; i++) {
                board[p1Y][i] = 1;
                addColliders(GamePlayManager.getHeadNode(), i, p1Y);

            }

        }
    }

    public static void addColliders(Node head, int x, int y) {

        Node check = new Node(x, y);

        if (check.equals(head)) return;

        if (y - 1 >= 0) board[y - 1][x] = 1;

        if (y + 1 < 50) board[y + 1][x] = 1;

        if (x - 1 >= 0) board[y][x - 1] = 1;

        if (x + 1 < 50) board[y][x + 1] = 1;


    }

    public static Node getNode(int x, int y) {

        if (x >= 0 && x < width && y >= 0 && y < height && board[y][x] == 0) { // board[y][x] == 0 is how we avoid obstacles
            return new Node(x, y);
        } else {
            return null;
        }

    }


}
