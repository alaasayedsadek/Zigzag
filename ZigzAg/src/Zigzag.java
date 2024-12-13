



import com.sun.opengl.util.*;
import java.awt.*;
import javax.media.opengl.*;
import javax.swing.*;

public class Zigzag extends JFrame {

    public static void main(String[] args) {

        new Zigzag();
    }


    public Zigzag() {
        GLCanvas glcanvas;
        Animator animator;

        ZigzagEventListener listener = new ZigzagEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(15);
        animator.add(glcanvas);
        animator.start();

        setTitle("Anim Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}
