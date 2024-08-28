public class Player 
{
    private int mScore; // score variable
    private int mBasicHits; // basic hits variable 
    private int mShrinkHits; // shrink hits variable 
    private int mBounceHits; // bounce hits variable 
    private int mSplitHits; // split hits variable     

    public Player()
	{
		mScore = 0; // initialize all variables to 0
        mBasicHits = 0;
        mBounceHits = 0;
        mShrinkHits = 0;
        mSplitHits = 0;
	}

    public int getScore () // getter to get the score
	{
		return mScore;
	}
    public int getBasicHits () // getter to get the basic hits
	{
		return mBasicHits;
	}

    public int getBounceHits () // getter to get the bounce hits
	{
		return mBounceHits;
	}

    public int getShrinkHits () // getter to get the shrink hits
	{
		return mShrinkHits;
	}

    public int getSplitHits () // getter to get the split hits
	{
		return mSplitHits;
	}

    public void updateScore (int score) // function to update the score
	{
		mScore = mScore + score;
	}

    public void updateBasic (int score) // function to update basic hits
	{
		mBasicHits = mBasicHits + score;
	}

    public void updateShrink (int score) // function to update shrink hits
	{
		mShrinkHits = mShrinkHits + score;
	}

    public void updateBounce (int score) // function to update bounce hits
	{
		mBounceHits = mBounceHits + score;
	}

    public void updateSplit (int score) // function to update split hits
	{
		mSplitHits = mSplitHits + score;
	}
}
