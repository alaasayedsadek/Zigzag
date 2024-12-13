
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import java.awt.event.*;

public class ZigzagEventListener implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.11f, 0.76f, 0.99f, 1.0f);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(-500, 500, -300, 300, -1, 1);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {

        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

    }


    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}