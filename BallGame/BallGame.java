/******************************************************************************
 *  Compilation:  javac BallGame.java
 *  Execution:    java BallGame n
 *  Dependencies: BasicBall.java StdDraw.java
 *
 *  Creates a BasicBall and animates it
 *
 *  Part of the animation code is adapted from Computer Science:   An Interdisciplinary Approach Book
 *  
 *  Run the skeleton code with arguments : 1  basic  0.08
 *******************************************************************************/
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class BallGame {

    public static void main(String[] args) 
    {
        int numOfHits = 0; // variable for the number of hits the player has for any ball
        boolean checkMousePress = false; // variable to check if the mouse was pressed or not
        
        // number of bouncing balls
        int numBalls = Integer.parseInt(args[0]);
        // ball types
        String ballTypes[] = new String[numBalls];
        // sizes of balls
        double ballSizes[] = new double[numBalls];

        // retrieve ball types
        int index = 1;
        for (int i = 0; i < numBalls; i++) 
        {
            ballTypes[i] = args[index];
            index += 2; // Increment by 2 to skip ball sizes
        }

        // retrieve ball sizes
        index = 2;
        for (int i = 0; i < numBalls; i++) 
        {
            ballSizes[i] = Double.parseDouble(args[index]);
            index += 2; // Increment by 2 to skip ball types
        }

        // TO DO: create a Player object and initialize the player game stats.
        Player player = new Player(); // created a player object to have the stats of the game
        // number of active balls
        int numBallsinGame = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800, 800);
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create colored balls
        // TO DO: Create "numBalls" balls (of types given in "ballTypes" with sizes given in "ballSizes") and store
        // them in an Arraylist
        ArrayList<BasicBall> balls = new ArrayList<>(); // created a ArrayList to hold BasicBall objects
        for (int i = 0; i < numBalls; i++) // loop through the number of balls and check which ball we are creating
        {
            BasicBall ball;
            switch (ballTypes[i].toLowerCase()) // switch to lower case to avoid upperCase letters in the spelling 
            {
                case "bounce":
                    ball = new BounceBall(ballSizes[i], Color.GREEN); // if ball is a bounce ball then we make it green
                    break;
                case "shrink":
                    ball = new ShrinkBall(ballSizes[i], Color.RED); // if ball is a shrink ball then we make it red
                    break;
                case "split":
                    ball = new SplitBall(ballSizes[i], Color.YELLOW); // if ball is a split ball then we make it yellow
                    break;
                default:
                    ball = new BasicBall(ballSizes[i], Color.BLUE); // if ball is a basic ball then we make it blue
                    break;
            }
            balls.add(ball); // then we add the ball to the array List
            //TO DO: initialize the numBallsinGame
            numBallsinGame++; // and increment the number of balls in the game
        }


        // do the animation loop
        StdDraw.enableDoubleBuffering();
        while (numBallsinGame > 0) 
        {

            // TODO: move all balls
            for (BasicBall ball : balls) // allows us to loop through all the balls and move them
            {
                ball.move();
            }

            // Check if the mouse is clicked
            if (StdDraw.isMousePressed() && !checkMousePress) // added a check for when the mouse is pressed so that the player can't just hold down the mouse
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                ArrayList<BasicBall> newBalls = new ArrayList<>(); // made a new array list for the split balls that will be added later into the original array list
                //TODO: check whether a ball is hit. Check each ball.
                for (BasicBall ball : balls) // loop through all the balls
                {
                    if (ball.isHit(x, y)) // check if the ball is hit
                    {
                        // Check the type of the ball
                        if (ball instanceof BounceBall) // if the ball is a bounce ball
                        {
                            player.updateBounce(1); // update the bounce ball hits for the player stats
                            player.updateScore(ball.getScore()); // update the players score from the bounce ball score
                            numOfHits++; // update the number of total hits 
                        } 
                        else if (ball instanceof ShrinkBall) 
                        {
                            player.updateShrink(1); // update the shrink ball hits for the player stats
                            player.updateScore(ball.getScore()); // update the players score from the shrink ball score
                            numOfHits++; // update the number of total hits 
                        } 
                        else if (ball instanceof SplitBall) 
                        {
                            player.updateSplit(1); // update the split ball hits for the player stats
                            player.updateScore(ball.getScore()); // update the players score from the split ball score
                            numOfHits++; // update the number of total hits
                            ((SplitBall) ball).split(newBalls); // splits the ball which just resets the ball and makes a new split ball as well
                        } 
                        else if (ball instanceof BasicBall) 
                        {
                            player.updateBasic(1); // update the basic ball hits for the player stats
                            player.updateScore(ball.getScore()); // update the players score from the basic ball score
                            numOfHits++; // update the number of total hits
                        }
                        if(!(ball instanceof ShrinkBall)) // if the ball wasn't a shrink ball then we reset the ball
                        {
                            ball.reset(); // resetting the ball
                        }
                    }
                }
                balls.addAll(newBalls); // we add the split balls into the balls arraylist
            }
            checkMousePress = StdDraw.isMousePressed(); // returns true or false if mouse is pressed

            numBallsinGame = 0;
            // draw the n balls
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);

            // TO DO: check each ball and see if they are still visible. numBallsinGame should hold the number of
            // visible balls in the game.
            for (BasicBall ball : balls) // checking each ball that is visible or not
            {
                if (!ball.isOut) 
                {
                    ball.draw();
                    numBallsinGame++;
                }
            }

            // Print the game progress
            StdDraw.setPenColor(StdDraw.YELLOW);
            Font font = new Font("Arial", Font.BOLD, 20);
            StdDraw.setFont(font);
            StdDraw.text(-0.65, 0.90, "Number of balls in game: " + String.valueOf(numBallsinGame));
            //TO DO: print the rest of the player statistics
            StdDraw.text(-0.70, 0.85, "Number of hits: " + String.valueOf(numOfHits)); // get the number of hits that the player has
            StdDraw.text(-0.65, 0.80, "Total Score: " + String.valueOf(player.getScore())); // get the score of the player and display its
            StdDraw.show();
            StdDraw.pause(20);
        }

        while (true) 
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            Font font = new Font("Arial", Font.BOLD, 60);
            StdDraw.setFont(font);
            StdDraw.text(0, 0, "GAME OVER");
            // TO DO: print the rest of the player statistics
            StdDraw.setPenColor(StdDraw.YELLOW); // change the text color to yellow
            font = new Font("Arial", Font.BOLD, 20); // change font back to 20
            StdDraw.setFont(font); // settting the font
            StdDraw.text(0, -0.10, "Score: " + String.valueOf(player.getScore())); // display the score of the player
            StdDraw.text(0, -0.15, "Basic hits: " + String.valueOf(player.getBasicHits())); // display the basic hits of the player
            StdDraw.text(0, -0.20, "Split hits: " + String.valueOf(player.getSplitHits())); // display the split hits of the player
            StdDraw.text(0, -0.25, "Shrink hits: " + String.valueOf(player.getShrinkHits())); // display the shrink hits of the player
            StdDraw.text(0, -0.30, "Bounce hits: " + String.valueOf(player.getBounceHits())); // display the bounce hits of the player
            StdDraw.show();
            StdDraw.pause(10);
        }

    }
}
