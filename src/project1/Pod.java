/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;


/**
 *
 * @author Garrett
 */
public class Pod {
    // Pod class is used to control each individual pod on the screen...
    
    private int xPos            = 0;     // Pod's current X position
    private int yPos            = 0;     // Pod's current Y position
    
    private int width           = 0;     // Amount of usable space on x cord
    private int height          = 0;     // Amount of usable space on y cord
    
    private boolean visible     = true;  // tells whether or not pod is visable                      // used if we want to make pod temp invisable...
    private boolean caught      = false; // tells whether or not pod has been caught by player
    private boolean evasiveUsed = false; // tells whether or not pod has used to its evasion tech...
    
    private String dir          = "";    // holds the pods current direction
    
    public static void main(String[] args) {
        // main driver for testing
        Pod p = new Pod(1, 5, "NE", 15, 9);
        
        System.out.println(0 + ".) " + p.toString());     // shows initial status
        
        for(int x=1;x<=100;x++) {
            p.move();
            p.playerAt(8,4);
            System.out.println(x + ".) " + p.toString()); // shows tests status
        }
    }
    
    public String toString(){
        // used with main driver to output current stats on the tested pod
        String out;
  
        out = "xPos: " + xPos + " yPos: " + yPos;
        
        if(visible)
            out = out + " visible: true";
        else
            out = out + " visible: false";
        
        if(caught)
            out = out + " caught: true";
        else
            out = out + " caught: false";
        
        if (evasiveUsed)
            out = out + " evasiveUsed: true";
        else
            out = out + " evasiveUsed: false";
        
        out = out + " direction: " + dir + " height: " + height + " width: " + width;
        
        
        return out;
    }
    
    public Pod(int initX, int initY, String direction, int w, int h) {
        // constructor
        // initiates each of the pods needed values.
        xPos   = initX;
        yPos   = initY;
        dir    = direction;
        height = h;
        width  = w;
    }
    
    public boolean isVisible(){
        // visibility does not determine if pod was caught...
        if(caught)
            return false;
        
        return visible;
    }
    
    public int getX(){
        // tells current xPos
        return xPos;
    }
    
    public int getY(){
        // tells current yPos
        return yPos;
    }
    
    public void move(){
        // This method is used to determine how the pod will move 
        // Basically breaks down the String dir by each letter which tells the pod how to handle positioning.
        // This also checks whether or not the pod has hit the wall... If it has then change the appropriate cardinal direction.
        
        // UP DOWN
        if(dir.contains("N")){
            if(yPos < height-1) {
                yPos++;
            }
            else
                dir = dir.replace("N","S");   // hit wall, so lets change direction as needed.
        }
        else if (dir.contains("S")) {
            if(yPos > 0) {
                yPos--;
            }
            else
                dir = dir.replace("S","N");   // hit wall, so lets change direction as needed.
        }
        
        // RIGHT LEFT
        if(dir.contains("E")){
            if(xPos < width-1) {
                xPos++;
            }
            else
                dir = dir.replace("E","W");   // hit wall, so lets change direction as needed.
        }
        else if (dir.contains("W")) {
            if(xPos > 0) {
                xPos--;
            }
            else 
                dir = dir.replace("W","E");   // hit wall, so lets change direction as needed.
        }
    }
    
    public void playerAt(int xPlayer, int yPlayer) {
        // tells where the player current is
        
        if(getX() == xPlayer && getY() == yPlayer) {
            caught = true;
        }
        // when node is close to the player, use evasive maneuver
        else if (isClose(xPlayer, yPlayer, 2)) {
            // use evasive as long as we havnt before...
            if(!evasiveUsed) {
                // evasive maneuver
                evasive();
            }
        }
    }
    
    public boolean caught() {
        // tells if pod has been caught
        return caught;
    }
    
    public void evasive() {
         // evasive maneuver
                
         // our evasive manauver changes the pods current x direction, while still keeping its y direction.
         if(dir.contains("E")){          // was going right
            dir = dir.replace("E","W");  // now going left
         }
         else if (dir.contains("W")){    // was going left
            dir = dir.replace("W","E");  // now going right
         }
         evasiveUsed = true;
    }
    
    public boolean isClose(int xPlayer, int yPlayer, int radius) {
        // returns true if pod is within a radius of player.
        return Math.abs(yPlayer - getY()) <= radius && Math.abs(xPlayer - getX()) <= radius;
    }
    
}
