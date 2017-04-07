/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author samjays
 */
public class TicTacToe extends JFrame
{

    int rows = 3;
    int cols = 3;
    int square = 100;
    String user = "X";
    String computer = "O";
    boolean userTurn = true;
    int counter = 0;
    Boolean winner = false;
    JLabel[][] labels = new JLabel[3][3];

    public TicTacToe()
    {
        JPanel panel = new JPanel(new GridLayout(rows, cols));

        for (int r = 0; r < labels.length; r++)
        {
            for (int c = 0; c < labels[r].length; c++)
            {
                //not sure why this doesn't need to be final
                int row = r;
                int col = c;
                JLabel label = new JLabel("", SwingConstants.CENTER);
                //labels[r][c] = new JLabel();
                labels[r][c] = label;
                label.setBackground(Color.BLACK);
                label.setOpaque(true);
                label.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
                label.setFont(new Font("Times New Roman", Font.BOLD, 100));
                panel.add(label);

                label.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        //very simple version zev's
                        //label.setText(userTurn ? user : computer)
                        //userTurn = !userTurn;
                        //JLabel jl = (JLabel) e.getSource();
//                        if (label.getText().isEmpty())
//                        {
                        //first way simple
                        //label.setText(counter % 2 == 0 ? "X" : "O");

                        //2nd way couldn't get it to fully work may have worked
                        //well and just got caught in the repaint loop
//                            if (counter % 2 == 0)
//                            {
//                                label.setForeground(Color.CYAN);
//                                
//                                label.setText("X");
//                                counter++;
//                                isWinner("X");
//                            }
//                            else if (counter % 2 != 0)
//                            {
//
//                                cpuTurn();
//                                counter++;
//                                isWinner("O");
//                            }
                        //zev's way
                        if (label.getText().isEmpty())
                        {

                            if (doTurn(row, col, user))
                            {
                                computerTurn();
                                //label.setForeground(Color.red);
                            }
                        }

                    }
                });
            }
        }

        add(panel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 75, 5));
        JButton restart = new JButton("Restart");
        JButton quit = new JButton("Quit");
        buttonPanel.add(restart);
        buttonPanel.add(quit);
        Dimension buttonSize = restart.getPreferredSize();
        quit.setPreferredSize(buttonSize);
        add(buttonPanel, BorderLayout.PAGE_END);
        restart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                counter=0;
                for (int i = 0; i < labels.length; i++)
                {
                    for (int j = 0; j < labels[i].length; j++)
                    {
                        labels[i][j].setText("");
                    }
                }
            }
        }
        );
        quit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // System.exit(0);
                dispose();

            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

    }

    //zev's way
    private void computerTurn()
    {
        while (true)
        {
            int row = (int) (Math.random() * 3);
            int col = (int) (Math.random() * 3);
            if (isEmptyCell(row, col))
            {
                //labels[row][col].setText(computer);
                doTurn(row, col, computer);
                break;
            }

        }

    }

    private boolean doTurn(int row, int col, String symbol)
    {
        counter++;
        labels[row][col].setText(symbol);
        isWinner(symbol);

        if (counter >= 9)
        {
            JOptionPane.showMessageDialog(this, "It's a draw", "Tic Tac Toe", JOptionPane.INFORMATION_MESSAGE);
            return false;
            //System.exit(0);
        }
        return true;

    }

    //use method so user can also use not to overwrite himself
    private boolean isEmptyCell(int row, int col)
    {
        return labels[row][col].getText().isEmpty();

    }

    public void cpuTurn()
    {
        int x = 0;
        int y = 0;

        while (true)
        {
            x = (int) (Math.random() * 3);
            y = (int) (Math.random() * 3);
            System.out.println(x);
            System.out.println(y);
            if (labels[x][y].getText().isEmpty())
            {
                labels[x][y].setText("O");
                labels[x][y].setForeground(Color.RED);

                break;
            } else
            {
                continue;
            }
        }

    }

    public void isWinner(String symbol)
    {

        //my way is missing columns winners 0,0 1,0 2,0 and ...
//        if (labels[0][0].getText().equals(symbol)
//                && labels[0][1].getText().equals(symbol)
//                && labels[0][2].getText().equals(symbol))
//        {
//
//            // System.out.println("You Won!");
//            JOptionPane.showMessageDialog(null, "You Won!");
//            winner = true;
//        } else if (labels[1][0].getText().equals(symbol)
//                && labels[1][1].getText().equals(symbol)
//                && labels[1][2].getText().equals(symbol))
//        {
//
//            //System.out.println("You Won!");
//            JOptionPane.showMessageDialog(null, "You Won!");
//            winner = true;
//        } else if (labels[2][0].getText().equals(symbol)
//                && labels[2][1].getText().equals(symbol)
//                && labels[2][2].getText().equals(symbol))
//        {
//
//            // System.out.println("You Won!");
//            JOptionPane.showMessageDialog(null, "You Won!");
//            winner = true;
//        } else if (labels[0][0].getText().equals(symbol)
//                && labels[1][1].getText().equals(symbol)
//                && labels[2][2].getText().equals(symbol))
//        {
//
//            // System.out.println("You Won!");
//            JOptionPane.showMessageDialog(null, "You Won!");
//            winner = true;
//        } else if (labels[0][2].getText().equals(symbol)
//                && labels[1][1].getText().equals(symbol)
//                && labels[2][0].getText().equals(symbol))
//        {
//
//            //System.out.println("You Won!");
//            JOptionPane.showMessageDialog(null, "You Won!");
//            winner = true;
//        }
            

            for (int i = 0; i < 3; i++)
            {
                if ((labels[i][0].getText().equals(symbol)
                        && labels[i][1].getText().equals(symbol)
                        && labels[i][2].getText().equals(symbol))
                        || (labels[0][i].getText().equals(symbol)
                        && labels[1][i].getText().equals(symbol)
                        && labels[2][i].getText().equals(symbol)))

                {
                    JOptionPane.showMessageDialog(null, "You Won!");
                } else if (labels[0][0].getText().equals(symbol)
                        && labels[1][1].getText().equals(symbol)
                        && labels[2][2].getText().equals(symbol))
                {

                    // System.out.println("You Won!");
                    JOptionPane.showMessageDialog(null, "You Won!");

                } else if (labels[0][2].getText().equals(symbol)
                        && labels[1][1].getText().equals(symbol)
                        && labels[2][0].getText().equals(symbol))
                {
                    JOptionPane.showMessageDialog(null, "You Won!");
                }

            }
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        new TicTacToe().setVisible(true);
    }

}
