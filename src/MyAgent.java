import sun.security.ssl.Debug;
import za.ac.wits.snake.DevelopmentAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MyAgent extends DevelopmentAgent {

    public static void main(String[] args) throws IOException {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);
            int width = Integer.parseInt(temp[1]);
            int height = Integer.parseInt(temp[2]);

            //Apple Health
            double appleHealth = 5.0;
            String applePrev = "0 0";

            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                GameBoardManager.initBoard(height, width);
                String[] apple1 = line.split(" ");
                GameBoardManager.initAppleCoord(apple1);

                // check positioning of apple
                if (line.equals(applePrev)) {
                    appleHealth = appleHealth - 0.1;
                } else {
                    applePrev = line;
                    appleHealth = 5.0;
                }


                // read in obstacles and do something with them!
                int nObstacles = 3;
                int[][][] obstacles = new int[3][5][2];
                for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
                    String obs = br.readLine();
                    GameBoardManager.drawObstacle(obs);
                }

                int mySnakeNum = Integer.parseInt(br.readLine());
                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    GameBoardManager.drawSnake(snakeLine);

                    if (i == mySnakeNum) {
                        GamePlayManager.initMyHead(snakeLine);
                    }
                }

                //finished reading, calculate move:
                int move = GamePlayManager.makeMove(appleHealth);
                System.out.println(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

