import java.awt.Color;

public class BounceBall extends BasicBall 
{
    private int mNumOfBounces; // give the bounce ball the number of bounces variable

    public BounceBall(double r, Color c) 
    {
        super(r, c);
        mNumOfBounces = 3; // initialize the number of bounces for the bounce ball
    }

    public void move() 
    {
        super.move(); // we call the move function from the main class

        if (mNumOfBounces > 0) // if the bounces is greater than 0 then we bounce the ball off the wall
        {
            if (rx + radius >= 1.0 || rx - radius <= -1.0) // checking if the x position is in the wall and if its not then we enter
            {
                vx = -vx; // we reverse direction the direction of the ball
                mNumOfBounces--; // decrement the number of bounces
            }
            if (ry + radius >= 1.0 || ry - radius <= -1.0) // checking if the y position is in the wall and if its not then we enter
            {
                vy = -vy; // we reverse direction the direction of the ball
                mNumOfBounces--; // decrement the number of bounces
            }
        } 
        else // if it has 0 bounces left and it goes out then we know we can make it true that is out
        {
            isOut = true; 
        }
    }

    public void draw() 
    {
        super.draw(); // we can just call the original draw function from the main class
    }

    public int reset() 
    {
        super.reset(); // we call the main reset function
        mNumOfBounces = 3; // make the bounces go back to 3
        return 1;
    }

    public int getScore() 
    {
        return 15; // the score of a bounce ball is 15
    }
}
