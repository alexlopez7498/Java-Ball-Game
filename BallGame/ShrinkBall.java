import java.awt.Color;

public class ShrinkBall extends BasicBall 
{
    private final double mRadius; // variable for the originial radius of the ball

    public ShrinkBall(double r, Color c) 
    {
        super(r, c);
        mRadius = r; // initialize the initial radius to the radius when it was created
    }

    private void shrink() 
    {
        radius = (radius * 2) / 3; // make the radius of the ball 2/3 the orginial size
        if (radius <= 0.25 * mRadius) // if the radius is less than or equal to the 0.25 * from the original radius then we reset the ball
        {
            reset();
        }
    }

    public boolean isHit(double x, double y) 
    {
        if (super.isHit(x, y)) // check if the ball was hit
        {
            shrink();  // we shrink the ball if it was hit
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
        return 20; // we get the score of a shrink ball which is 20
    }

    public int reset() 
    {
        super.reset(); // call the reset from the main class
        radius = mRadius; // make the ball the orginial radius
        return 1;
    }
}
