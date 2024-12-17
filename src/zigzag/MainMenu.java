/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zigzag;

import javax.media.opengl.GL;
import javax.swing.*;
import static zigzag.Variables.textures;

public class MainMenu implements Variables {
    static int Page = 0;
    boolean play = false;
    static String key ;
    
    static boolean isEasy;
    static boolean isMulti;
    static String username;
    static String username1;
    static String username2;
  
    static int gameMode = 0; 

    public void DrawMainMenu(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[Page]);

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }

 public void positions(int x, int y) {
    // Check for Play button
    if (x >= 25 && x <= 172 && y >= 241 && y <= 331) {
        System.out.println("Play button clicked at: " + x + ", " + y);
        Page = 0;
        play = true;
        username = JOptionPane.showInputDialog("Enter user name");
    } 
    // Check for Easy button
    else if (x >= 298 && x <= 685 && y >= 310 && y <= 419) {
        System.out.println("Easy button clicked at: " + x + ", " + y);
        play = true;
        isEasy = true;
        gameMode = 1;
    }


    
    
    if (x >= 15 && x <= 165 && y >= 50 && y <= 200) {
        System.out.println("MultiPlayer button clicked at: " + x + ", " + y);
        
    }
    
    
    if (x >= 123 && x <= 270 && y >= 626 && y <= 715) {
        System.out.println("Instructions button clicked at: " + x + ", " + y);
        key = "instruction" ;
        Page=6 ;
    }


      if (x >= 171 && x <= 317 && y >= 791 && y <= 877) {
          System.out.println("Exit button clicked at: " + x + ", " + y);
          System.exit(0);
      }

      if ( (x >= 2 && x <= 83 && y >=0 && y <= 63) && Page==6 ) {
          System.out.println("Back button clicked at: " + x + ", " + y);
          key="BackToHome" ;
          Page=0 ;
      } // fatma
}

}
