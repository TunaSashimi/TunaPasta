package history;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomTest {
    //
    public static final int RED_BALL_TOTAL = 33, BLUE_BALL_TOTAL = 16;
    public static int redBallLength = 8, blueBallLength = 2;

    //
    public static List redBallList = new ArrayList<Integer>(), blueBallList = new ArrayList<Integer>(),
            redBallExclude = new ArrayList<Integer>(), blueBallExclude = new ArrayList<Integer>();

    public static int redBallRandom, buleBallRandom;

    public static void main(String[] args) {

        //
        blueBallExclude.add(2);
        blueBallExclude.add(3);
        blueBallExclude.add(8);
        blueBallExclude.add(9);
        blueBallExclude.add(14);

        //
        redBallExclude.add(6);
        redBallExclude.add(7);
        redBallExclude.add(8);
        redBallExclude.add(13);
        redBallExclude.add(15);
        redBallExclude.add(16);
        redBallExclude.add(19);
        redBallExclude.add(20);

        //
        while (true) {
            redBallRandom = (int) (Math.random() * RED_BALL_TOTAL + 1);
            if (!redBallExclude.contains(redBallRandom) && !redBallList.contains(redBallRandom)) {
                redBallList.add(redBallRandom);
                if (redBallList.size() == redBallLength) {
                    break;
                }
            }
        }

        //
        while (true) {
            buleBallRandom = (int) (Math.random() * BLUE_BALL_TOTAL + 1);
            if (!blueBallExclude.contains(buleBallRandom) && !blueBallList.contains(buleBallRandom)) {
                blueBallList.add(buleBallRandom);
                if (blueBallList.size() == blueBallLength) {
                    break;
                }
            }
        }

        //
        Collections.sort(redBallList);
        Collections.sort(blueBallList);


        //
        System.out.print(redBallList + "|" + blueBallList);
    }
}
