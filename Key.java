import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

public class Key extends Actor
{
    private boolean isDown = false;
    private String key;
    private String sound; 
    private String notPressed;
    private String pressed;
    
    public Key( String keyName, String soundFile, String keyNotPressed, String keyPressed)
    {
        key = keyName;
        sound = soundFile;
        notPressed = keyNotPressed;
        pressed = keyPressed;
        
        setImage( notPressed + ".png");
    }

    /**
     * Do the action for this key.
     */
    public void act()
    {
        if( isDown == false && Greenfoot.isKeyDown(key) )
        {
            setImage(pressed  + ".png");
            isDown = true;
            play();
        }
        
        if( isDown == true && !Greenfoot.isKeyDown(key))
        {
            setImage(notPressed + ".png");
            isDown = false;
        }
        
    }
    
    /**
     * play plays the sound for a key
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void play()
    {
        Greenfoot.playSound( sound + ".wav");
    }
    
    /**
     * checkDown returns the isDown variable
     * 
     * @param There are no parameters
     * @return isDown is returned 
     */
    public boolean checkDown()
    {
        return isDown;
    }
}

