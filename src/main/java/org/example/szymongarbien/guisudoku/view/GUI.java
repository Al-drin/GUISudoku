package org.example.szymongarbien.guisudoku.view;

import org.example.szymongarbien.guisudoku.Sudoku;
import org.example.szymongarbien.guisudoku.domain.Position;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

@Component
public class GUI extends Frame implements ActionListener {

    private Sudoku controller;

    public void init(ArrayList<Position> positions)
    {
        Font boldFont = new Font("Courier", Font.BOLD,12);
        Font normalFont = new Font("Courier", Font.PLAIN,12);

        setLayout(new GridLayout(9,9));

        for (Position position : positions) {
            add(position);

            position.setLabel("" + position.getVal());

            if (position.getVal() != 0) {
                position.setFont(boldFont);
            } else {
                position.setFont(normalFont);
            }

            position.addActionListener(this);
        }

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                System.exit(0);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Position position = (Position) e.getSource();

        if (position.isBlocked()) {
            return;
        }

        new DialogBox(position);
    }

    public void setController(Sudoku controller) {
        this.controller = controller;
    }

    public class DialogBox extends Frame
    {
        public DialogBox(Position position)
        {
            Choice choice = new Choice();
            for(int i=1; i<=9; i++) {
                choice.add(""+i);
            }

            Panel panel = new Panel();
            panel.add(new Label("New value:"));
            panel.add(choice);

            Button button = new Button("Set");
            button.addActionListener(event -> {
                position.setVal(choice.getSelectedIndex()+1);
                position.setLabel(choice.getSelectedIndex()+1+"");
                dispose();
                controller.solve();
            });

            panel.add(button);
            add(panel);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);

            addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent event)
                {
                    dispose();
                }
            });
        }
    }

    public void announceSolved() {
        Frame frame = new Frame();
        Label label = new Label("PUZZLE SOLVED!");
        label.setFont(new Font("Courier", Font.BOLD,24));


        frame.add(label);

        frame.pack();
        setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                System.exit(0);
            }
        });
    }
}
