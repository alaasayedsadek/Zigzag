package zigzag;

import com.sun.opengl.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.media.opengl.*;
import javax.swing.*;

public class Zigzag extends JFrame {
    private JPanel gamePanel;
    private JPanel buttonPanel;
    private boolean gameStarted = false;
    private boolean gameEnded = false;
    private GLCanvas glcanvas;
    private Animator animator;
    private JButton startButton;
    private JButton endButton;
    private JButton playAgainButton;

    public static void main(String[] args) {

        new Zigzag();


        // Setupe the frame

    }
    private void setupMainComponents() {
        // Create the game panel that will contain both GLCanvas and buttons
        gamePanel = new JPanel(new BorderLayout());

        // Setup OpenGL canvas
        ZigzagGLEventListener listener = new ZigzagGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);

        // Setup animator
        animator = new FPSAnimator(15);
        animator.add(glcanvas);

        // Add GLCanvas to game panel
        gamePanel.add(glcanvas, BorderLayout.CENTER);
    }
    private void setupButtonPanel() {
        // Create button panel with FlowLayout
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(50, 50, 50));

        // Create buttons
        startButton = createStyledButton("Start Game", new Color(34, 139, 34));
        endButton = createStyledButton("stop", new Color(178, 34, 34));
        playAgainButton = createStyledButton("Start Game", new Color(34, 139, 34));

        // Initially show only start button
        buttonPanel.add(startButton);

        // Add button panel to bottom of game panel
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Setup button actions
        setupButtonActions();
    }
    private JButton createStyledButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(backgroundColor.brighter());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }
    private void setupButtonActions() {
        startButton.addActionListener(e -> {
            gameStarted = true;
            buttonPanel.removeAll();
            buttonPanel.add(endButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
            animator.start();
        });

        endButton.addActionListener(e -> {
            gameEnded = true;
            buttonPanel.removeAll();
            buttonPanel.add(playAgainButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
            animator.stop();
        });

        playAgainButton.addActionListener(e -> {
            gameStarted = true;
            gameEnded = false;
            buttonPanel.removeAll();
            buttonPanel.add(endButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
            animator.start();
        });
    }




    public Zigzag() {
        setupMainComponents();

        // Create and setupe the button panel
        setupButtonPanel();
        GLCanvas glcanvas;
        Animator animator;
        getContentPane().add(gamePanel);
        
        ZigzagListener listener = new ZigzagGLEventListener();
        glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addKeyListener(listener);
        glcanvas.addMouseListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        animator = new FPSAnimator(30);
        animator.add(glcanvas);
        animator.start();

        setTitle("Zigzag");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,   1000);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
    }
}