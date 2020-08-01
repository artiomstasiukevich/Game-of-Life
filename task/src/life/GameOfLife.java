package life;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameOfLife extends JFrame {
    ArrayList<JLabel> cells = new ArrayList<>();
    JToggleButton PlayToggleButton;
    JButton ResetButton;
    JLabel GenerationLabel;
    JLabel AliveLabel;
    Universe ouruniverse;
    boolean needToReset = false;
    boolean stoped = false;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setVisible(true);

        setLayout(new BorderLayout());
        JPanel notes = new JPanel();
        add(notes, BorderLayout.NORTH);
        notes.setLayout(new FlowLayout());
        notes.setSize(100, 300);


        PlayToggleButton = new JToggleButton();
        PlayToggleButton.setName("PlayToggleButton");
        PlayToggleButton.setText("Stop");
        notes.add(PlayToggleButton);

        ResetButton = new JButton();
        ResetButton.setName("ResetButton");
        ResetButton.setText("Reset");
        notes.add(ResetButton);

        ResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                needToReset = true;
            }
        });

        PlayToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(stoped) {
                    stoped = false;
                } else {
                    stoped = true;
                }
            }
        });

        GenerationLabel = new JLabel("Generation #1");
        GenerationLabel.setName("GenerationLabel");
        notes.add(GenerationLabel);

        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        AliveLabel.setText(String.format("Alive: %d", 0));
        notes.add(AliveLabel);
    }

    public void DrawWindow(Universe ouruniverse) {

        this.ouruniverse = ouruniverse;
        JPanel uni = new JPanel();
        add(uni, BorderLayout.CENTER);
        AliveLabel.setText(String.format("Alive: %d", ouruniverse.getAlive()));

        for (int i = 0; i < ouruniverse.size * ouruniverse.size;
             i++) {
            JLabel lable = new JLabel();
            lable.setSize(300 / ouruniverse.size * ouruniverse.size,
                    300 / ouruniverse.size * ouruniverse.size);
            lable.setOpaque(true);
            if (ouruniverse.universe[i / ouruniverse.size]
                    [i % ouruniverse.size]
                    .isAlive) {
                lable.setBackground(Color.BLACK);
            }
            lable.setVisible(true);
            lable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cells.add(lable);
        }

        for (JLabel cell : cells) {
            uni.add(cell, BorderLayout.CENTER);
        }
        uni.setLayout(new GridLayout(ouruniverse.size,ouruniverse.size,0,0));
        setVisible(true);
    }

    public void UpdateWindow() {
        for(int i = 0; i < ouruniverse.size * ouruniverse.size;
            i++) {
            if (ouruniverse.universe[i / ouruniverse.size]
                    [i % ouruniverse.size]
                    .isAlive) {
                cells.get(i).setBackground(Color.BLACK);
            } else {
                cells.get(i).setBackground(Color.WHITE);
            }
        }
        GenerationLabel.setText(String.format("Generation #%d", ouruniverse.generation));
        AliveLabel.setText(String.format("Alive: %d", ouruniverse.getAlive()));
    }


}
