import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * A piano that can be played with the computer keyboard.
 * 
 * Name: Emmanuel Olaniyanu
 * Teacher: Mr Hardman
 * Lab #5, Piano 
 * Date Last Modified: 5/5/2017
 * 
 * @author: M. Kölling
 * @version: 0.1
 */
public class Piano extends World
{
    private String[] whiteKeys = {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "[", "]" };
    private String[] whiteNotes = {"3c", "3d", "3e", "3f", "3g", "3a", "3b", "4c", "4d", "4e", "4f", "4g" } ;
    private String[] blackKeys = {"2", "3", "", "5", "6", "7", "", "9", "0", "", "="};
    private String[] blackNotes = {"3c#", "3d#", "", "3f#", "3g#", "3a#", "", "4c#", "4d#", "", "4f#"};

    private Key[] whiteKeyObjects = new Key[whiteKeys.length];
    private Key[] blackKeyObjects = new Key[blackKeys.length];
    private Key[] allKeyObjects = new Key[whiteKeys.length + blackKeys.length];

    
    /**
     * Make the piano.
     */
    public Piano() 
    {
        super(800, 340, 1);
        makeKeys();

    }

    /**
     * makeKeys creates the keys with there various sounds and images and spawns it into the world
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void makeKeys()
    {
        Key currentKey;

        for( int i = 0; i < whiteKeys.length; i++)
        {
            currentKey = new Key ( whiteKeys[i], whiteNotes[i] , "white-key", "white-key-down");
            addObject (currentKey, (i * 67) + 30, 250);
            whiteKeyObjects[i] = currentKey;
        }

        for( int i = 0; i < blackKeys.length; i++)
        {
            if( (blackKeys[i] != ""))
            {
                currentKey = new Key ( blackKeys[i], blackNotes[i], "black-key", "black-key-down");
                addObject (currentKey, (i * 67) + 65, 195);
                blackKeyObjects[i] = currentKey;
            }
            else
            {
                blackKeyObjects[i] = null;
            }
        }

        makeAllKeysArray();
    }

    /** 
     * makeAllKeysArray stores all keys in order into the allKeyObjects
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void makeAllKeysArray()
    {
        for( int i = 0; i < allKeyObjects.length; i++)
        {
            if( i % 2 == 0 ) 
            {
                allKeyObjects[i] = whiteKeyObjects[i/2];
            }
            else
            {
                allKeyObjects[i] = blackKeyObjects[i/2];
            }
        }

        allKeyObjects[allKeyObjects.length - 1] = whiteKeyObjects[whiteKeyObjects.length -1];

    }

    public void act()
    {
        int numAllDown = 0;
        int numNulls = 0;

        int[] keyDownLocations = new int[20];

        for( int i = 0; i < allKeyObjects.length; i++)
        {
            if( allKeyObjects[i] == null )
            {
                numNulls++;
            }
            else
            {
                if( allKeyObjects[i].checkDown() == true )
                {
                    keyDownLocations[numAllDown] = i - numNulls;
                    numAllDown++;
                }
            }
        }

        if( numAllDown == 2)
        {
            checkForSeconds(keyDownLocations);
        }
        else if( numAllDown == 3 )
        {
            checkForTriads(keyDownLocations);
            checkForInvertedTriads(keyDownLocations);
        }
        else if(  numAllDown == 4 )
        {
            checkForSevenths(keyDownLocations);
        }
        else
        {
            showText("", getWidth()/2, 50);
            showText("", getWidth()/2, 75);
        }
    }

    /**
     * checkForSeconds checks if a second is being played on the piano
     * 
     * @param downKeys[] is the array of keys that are being pressed
     * @return Nothing is returned
     */
    public void checkForSeconds( int downKeys[] )
    {
        if ( (downKeys[0] + 1) == downKeys[1] || ( downKeys[0] + 2 ) == downKeys[1])
        {
            showText( "You have made a second", getWidth()/2, 50);
        }
    }

    /**
     * checkForTriads checks if a triad is being played on the piano
     * 
     * @param downKeys[] is the array of keys that are being pressed
     * @return Nothing is returned
     */
    public void checkForTriads(int downKeys[])
    {
        if ( (downKeys[0] + 3) == downKeys[1] && ( downKeys[1] + 4 ) == downKeys[2]  )
        {
            showText( "You have made a Triad", getWidth()/2, 50);
            if( downKeys[0] < downKeys[1] && downKeys[0] < downKeys[2] )
            {
                
            }

        }
        else if ( ( downKeys[0] + 4) == downKeys[1] && ( downKeys[1] + 3 ) == downKeys[2] )
        {
            showText( "You have made a Triad", getWidth()/2, 50);
        }
        else if ( (downKeys[0] + 3) == downKeys[1] && ( downKeys[1] + 3 ) == downKeys[2] )
        {
            showText( "You have made a Triad", getWidth()/2, 50);
        }
    }

    /**
     * checkForSevenths checks if a seventh is being played on the piano
     * 
     * @param downKeys[] is the array of keys that are being pressed
     * @return Nothing is returned
     */
    public void checkForSevenths(int downKeys[])
    {
        if( (downKeys[0] + 3) == downKeys[1] && ( downKeys[1] + 4 ) == downKeys[2]  && ( downKeys[2] + 3) == downKeys[3] )
        {
            showText( "You have made a Seventh", getWidth()/2, 50);
        }
        else if( (downKeys[0] + 4) == downKeys[1] && ( downKeys[1] + 3 ) == downKeys[2]  && ( downKeys[2] + 4) == downKeys[3] )
        {
            showText( "You have made a Seventh", getWidth()/2, 50);
        }
        else if( (downKeys[0] + 3) == downKeys[1] && ( downKeys[1] + 3 ) == downKeys[2]  && ( downKeys[2] + 3) == downKeys[3] )
        {
            showText( "You have made a Seventh", getWidth()/2, 50);
        }
    }
    
    /**
     * checkForInvertedTriads checks fo inverted triads on the piano 
     * 
     * @param downKeys[] is the array of keys that are being pressed
     * @return Nothing is returned
     */
    public void checkForInvertedTriads(int downKeys[])
    {
        if ( ( downKeys[0] + 3) == downKeys[1] && ( downKeys[1] + 5) == downKeys[2])
        {
            showText( "You have made an Inverted Triad", getWidth()/2, 75);
        }
        else if ( ( downKeys[0] + 3) == downKeys[1] && ( downKeys[1] + 4) == downKeys[2] )
        {
            showText( "You have made an Inverted Triad", getWidth()/2, 75);
        }
        else if ( ( downKeys[0] + 4) == downKeys[1] && ( downKeys[1] + 5) == downKeys[2] )
        {
            showText( "You have made an Inverted Triad", getWidth()/2, 75);
        }
    }
}
