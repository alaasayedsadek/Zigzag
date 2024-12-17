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
import java.util.Random;

import com.sun.opengl.util.GLUT;
import javax.swing.JOptionPane;

public class ZigzagGLEventListener extends ZigzagListener implements Variables,MouseListener {

   MainMenu menus = new MainMenu();
     static SoundPlayer GameSound = new SoundPlayer();



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
        gl.glLoadIdentity();
        gl.glOrtho(-maxxWidth / 2.0, maxxWidth / 2.0, -maxxHeight / 2.0, maxxHeight / 2.0, -1, 1);
    }

    int maxxWidth = 500;
    int maxxHeight = 500;
    int tileType = 1;
    int flag = -1;
    int levelCounter = 0;
    boolean gameOver = false;
    boolean paused = false;
    double x1 = 0;
    double y1 = 0;
    double speed = 3;
    double xBall = -70;
    double yBall = -70;
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

         if (menus.play==true){
             menus.DrawMainMenu(gl);
             gl.glPushMatrix();
             gl.glScaled(0.0025,0.0025,1);
             createMap();
             drawMap(gl);
             drawBall(gl, xBall, yBall, 50, 50, 8);
             gl.glPopMatrix();
             if (flag == 1 || flag == 0) {
                 //           score.updateScore((float) speed);
             }
             levelCounter++;
             if (levelCounter % 100 == 0) {
                 levelCounter = 0;
                 speed += 0.25;
             }
             /*
             *
             * */
         }
         if(menus.key=="instruction") {
             menus.DrawMainMenu(gl);
             DrawSprite(gl , 5 , 95 , 7 , 1.0f);
         }
        if(menus.key=="BackToHome") {
            menus.DrawMainMenu(gl);
            DrawSprite(gl, 10, 70, 1, scale);
            DrawSprite(gl, 15, 50, 2, scale);
            DrawSprite(gl, 20, 30, 3, scale);
            DrawSprite(gl, 25, 13, 4, scale);
        }
    //    if (paused) {
      //      try {
        //        Thread.sleep(100);
          //  } catch (InterruptedException ex) {
           //     ex.printStackTrace();
      //      }
      //  }

      //      createMap();
        //    drawMap(gl);
          //  drawBall(gl, xBall, yBall, 50, 50, 1);
          //  if (flag == 1 || flag == 0) {
     //           score.updateScore((float) speed);
           // }
      //      levelCounter++;

       // if (gameOver) {
       //     score.storeSessionScore();
          //  gameover.setVisible(gameOver);
        //    ChoseLevel.zigzag.setVisible(false);
       // }



//        handleKeyPress();
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
    public void createMap() {

        tiles.add(new Tile(x1, y1, tileType));
        Random random = new Random();
        double randomNumber = random.nextDouble();

        if (randomNumber < 0.5) {
            tileType = 0;
        } else {
            tileType = 1;
        }

        if (flag == 1 || flag == 0) {
            if (x1 < -250) {
                tileType = 1;
                x1 += 70;
                y1 += 69 - speed;

            } else if (x1 > 250) {
                tileType = 0;
                x1 -= 70;
                y1 += 69 - speed;

            } else if (x1 >= -250 && x1 < 250) {
                if (tileType == 1) {
                    x1 += 70;

                } else {
                    x1 -= 70;

                }
                y1 += 69 - speed;
            }
        } else {
            if (x1 < -250) {
                tileType = 1;
                x1 += 70;
                y1 += 69;

            } else if (x1 > 250) {
                tileType = 0;
                x1 -= 70;
                y1 += 69;

            } else if (x1 >= -250 && x1 < 250) {
                if (tileType == 1) {
                    x1 += 70;

                } else {
                    x1 -= 70;

                }
                y1 += 69;
            }
        }

    }

    public void drawMap(GL gl) {
        for (Tile tile : tiles) {
            drawTile(gl, tile.x, tile.y, tile.angle, 100, 100, 9);
            if (flag == 1 || flag == 0) {
                tile.y -= speed;
            }

            tile.invalidate();
            if ((0 <= Math.abs(yBall - tile.y)) && Math.abs(yBall - tile.y) <= 20) {
                if (!(0 <= Math.abs(xBall - tile.x) && Math.abs(xBall - tile.x) <= 85)) {
                    gameOver = true;
                }
            }
        }

        tiles.removeIf(b -> b.invisible);

    }

    public void drawTile(GL gl, double x, double y, int angle, int width, int height, int texture) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture]);    // Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glRotated(angle, 0, 0, 1);
        gl.glScaled(width / 2.0, height / 2.0, 1);
        gl.glBegin(GL.GL_QUADS);

        Vertex(gl);

        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }

    public void drawBall(GL gl, double x, double y, int width, int height, int texture) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[texture]);    // Turn Blending On
        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        if (flag == 1) {
            xBall += speed;
        } else if (flag == 0) {
            xBall -= speed;
        }
        if(xBall>=380){
            //page=0
            xBall-=speed;
        }
        if(xBall<=-380){
            xBall+=speed;

        }
        gl.glRotated(-45, 0, 0, 1);
        gl.glScaled(width / 2.0, height / 2.0, 1);
        gl.glBegin(GL.GL_QUADS);

        Vertex(gl);

        gl.glEnd();
        gl.glPopMatrix();
        gl.glDisable(GL.GL_BLEND);
    }

    private void Vertex(GL gl) {

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);

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

        if (event.getKeyChar() == KeyEvent.VK_SPACE) {
            if (flag == -1 || flag == 0) {
                flag = 1;
            } else if (flag == 1) {
                flag = 0;
            }
        }
        if (event.getKeyChar() == KeyEvent.VK_ESCAPE) {
            paused = !paused;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (flag == -1 || flag == 0) {
            flag = 1;
        } else if (flag == 1) {
            flag = 0;
        }
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
