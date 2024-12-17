package zigzag;

import Texture.TextureReader;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.BitSet;
import com.sun.opengl.util.GLUT;
import javax.swing.JOptionPane;

public class ZigzagGLEventListener extends ZigzagListener implements Variables,MouseListener {

   MainMenu menus = new MainMenu();
     static SoundPlayer GameSound = new SoundPlayer();
    static SoundPlayer AccidentSound  = new SoundPlayer();

    static boolean flagPause = false;

    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);
        GameSound.loadSound("/resources/sounds/alex-productions-get-going.wav");
        GameSound.MainMusic();

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);

                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA,
                        texture[i].getWidth(), texture[i].getHeight(),
                        GL.GL_RGBA,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels()
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    public void display(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();


        DrawBackground(gl);

        float scale = 1.5f;
        DrawSprite(gl, 10, 70, 1, scale);
        DrawSprite(gl, 15, 50, 2, scale);
        DrawSprite(gl, 20, 30, 3, scale);
        DrawSprite(gl, 25, 13, 4, scale);

        if(menus.play==true) {
            menus.DrawMainMenu(gl);
            DrawSprite(gl, 10, 90, 7, scale);
            DrawSprite(gl, 30, 60, 8,2.5f );
            DrawSprite(gl, 30, 40, 9, 2.5f);
        }

        if(menus.easy==true|| menus.hard==true) {
            menus.DrawMainMenu(gl);

        }

         if(menus.multiplayer==true) {
            menus.DrawMainMenu(gl);
            DrawSprite(gl, 10, 90, 7, scale);
           DrawSprite(gl, 60, 50, 10, 2.5f);
        }
        if(menus.back==true){
            menus.DrawMainMenu(gl);
            DrawSprite(gl, 10, 70, 1, scale);
            DrawSprite(gl, 15, 50, 2, scale);
            DrawSprite(gl, 20, 30, 3, scale);
            DrawSprite(gl, 25, 13, 4, scale);
        }

    }

    public double sqrdDistance(int x, int y, int x1, int y1) {
        return Math.pow(x - x1, 2) + Math.pow(y - y1, 2);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void DrawSprite(GL gl, int x, int y, int index, float scale) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 1.0, y / (maxHeight / 2.0) - 1.0, 0);
        gl.glScaled(0.1 * scale, 0.1 * scale, 1);
        gl.glBegin(GL.GL_QUADS);
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

    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[0]); // رسم الخلفية (Back.png)

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);
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

    public BitSet keyBits = new BitSet(256);

    @Override
    public void keyPressed(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.set(keyCode);
    }

    @Override
    public void keyReleased(final KeyEvent event) {
        int keyCode = event.getKeyCode();
        keyBits.clear(keyCode);
    }

    @Override
    public void keyTyped(final KeyEvent event) {
    }

    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
 

    @Override
    
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
 
        menus.positions(x, y);
      System.out.println(x+" "+y);
    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }


    @Override
    public void mouseExited(MouseEvent e) {

    }
}
