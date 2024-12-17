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
    boolean multiplayer = false;
    boolean back = false;
    boolean easy = false;
    boolean hard = false;

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

      if ((x >= 25 && x<= 172) && (y >= 244 && y <= 330) )//play button
      {
          Page = 5;
          play = true;
          username = JOptionPane.showInputDialog("Enter user name");
      }

      if ((x >= 171 && x<= 416) && (y >= 305 && y <= 454) )//easy button
      {
          Page = 5;
          play = true;
          easy=true;
      }

      if ((x >= 171 && x<= 416) && (y >= 497 && y <= 651) )//easy button
      {
          Page = 5;
          play = true;
          easy=true;
      }



    if (x >= 74 && x <= 219 && y >= 439 && y <= 524)//multiplayer button
    {
        Page=5;
        multiplayer=true;
        
    }

      if (x >= 468 && x <= 712 && y >= 403 && y <= 551)//Vs Player 2 button
      {

          username = JOptionPane.showInputDialog("Enter user 1 name");
          username = JOptionPane.showInputDialog("Enter user 2 name");

      }

    if (x >= 20 && x <= 170 && y >= 30 && y <= 180)//instructions button
    {
        System.out.println("Instructions button clicked at: " + x + ", " + y);

    }
      if (x >= 27&& x <= 168 && y >= 24 && y <= 162)//back button
      {
          Page = 5;
          back = true;
      }

      if (x >= 171 && x <= 317 && y >= 791 && y <= 877)//Exit button
      {
          System.out.println("Exit button clicked at: " + x + ", " + y);
          System.exit(0);
      }

}

}
