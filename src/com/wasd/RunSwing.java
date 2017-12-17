package com.wasd;

import com.wasd.graphics.GraphicsPanel;
import com.wasd.logic.World;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunSwing {

    public static void main(String[] args) {
        JFrame window = new JFrame("Triangle alignment game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setBounds(400, 100, 900, 800);
        window.setMinimumSize(new Dimension(300, 300));

        World world = new World(World.NUM_ENTITIES);
        GraphicsPanel graphics = new GraphicsPanel(world);
        graphics.setBackground(Color.WHITE);

        JSlider stepSizeSlider = new JSlider(1, 200);
        JButton playButton = new JButton("Play ►");
        JButton pauseButton = new JButton("Pause ||");
        JButton resetButton = new JButton("Reset");
        pauseButton.setEnabled(false);


        Timer timer = new Timer(17, ae -> {
            world.step(stepSizeSlider.getValue() / 1000f);
            graphics.repaint();
        });

        playButton.addActionListener(e -> {
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
            timer.start();
        });

        pauseButton.addActionListener(e -> {
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            timer.stop();
        });
        resetButton.addActionListener(e -> {
            world.regenerate();
            graphics.repaint();
        });

        JPanel lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.add(playButton);
        lowerPanel.add(pauseButton);
        lowerPanel.add(stepSizeSlider);
        lowerPanel.add(resetButton);

        window.add(graphics, BorderLayout.CENTER);
        window.add(lowerPanel, BorderLayout.SOUTH);

        window.setVisible(true);
    }
}
