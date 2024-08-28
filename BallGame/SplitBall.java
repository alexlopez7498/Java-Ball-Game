import java.awt.Color;
import java.util.ArrayList;

public class SplitBall extends BasicBall 
{

    public SplitBall(double r, Color c) 
    {
        super(r, c);
    }

    // Method to handle splitting the ball
    public void split(ArrayList<BasicBall> balls) 
    {
        SplitBall newBall1 = new SplitBall(radius, color); // we created a new split ball

        newBall1.rx = 0.0; // starting x and y positions
        newBall1.ry = 0.0;
   
        newBall1.vx = StdRandom.uniform(-0.01, 0.01); // give it random x and y velocity
        newBall1.vy = StdRandom.uniform(-0.01, 0.01);
 
        balls.add(newBall1); // then we add it to the arrayList that is passed through
    }

    public boolean isHit(double x, double y) 
    {
        if (super.isHit(x, y)) // check if the ball was hit
        {
            return true;
        }
        return false;
    }

    public void draw() 
    {
        super.draw(); // we can just call the original draw function from the main class
    }

    public int getScore() 
    {
        return 10; // we get the score of a split ball which is 20
    }

    public int reset() 
    {
        super.reset(); // call the reset from the main class
        return 1;
    }
}
