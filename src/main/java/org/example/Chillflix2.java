package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chillflix2 extends JFrame{
    private JPanel Chillflix;
    private JTextField tf_titel;
    private JComboBox combo_genre;
    private JComboBox combo_fsk;
    private JCheckBox check_titel;
    private JCheckBox check_genre;
    private JCheckBox check_fsk;
    private JTextField tf_dauer;
    private JCheckBox check_dauer;
    private JRadioButton rb_1stern;
    private JRadioButton rb_2stern;
    private JRadioButton rb_3stern;
    private JCheckBox check_bewertug;
    private JTextField tf_regesseur;
    private JCheckBox check_regeseur;
    private JComboBox combo_streambar;
    private JCheckBox check_streamabr;
    private JComboBox combo_filtern;
    private JCheckBox check_filtern;
    private JButton b_herz;
    private JButton b_speichern;
    private JButton b_ausgeben;
    private JTextArea textArea1;

    public Chillflix2(){

        setTitle("Chillflix");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setContentPane(Chillflix);
        setVisible(true);
        setResizable(false);
        ButtonGroup gruppierung = new ButtonGroup();
        gruppierung.add(rb_1stern);
        gruppierung.add(rb_2stern);
        gruppierung.add(rb_3stern);
        check_titel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_titel.setEnabled(!check_titel.isSelected());
            }
        });
        check_dauer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_dauer.setEnabled(!check_dauer.isSelected());
            }
        });
        check_streamabr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo_streambar.setEnabled(!check_streamabr.isSelected());
            }
        });
        check_genre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo_genre.setEnabled(!check_genre.isSelected());
            }
        });
        check_bewertug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rb_1stern.setEnabled(!check_bewertug.isSelected());
            }
        });
        check_bewertug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rb_2stern.setEnabled(!check_bewertug.isSelected());
            }
        });
        check_bewertug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rb_3stern.setEnabled(!check_bewertug.isSelected());
            }
        });
        check_filtern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo_filtern.setEnabled(!check_filtern.isSelected());
            }
        });
        check_fsk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combo_fsk.setEnabled(!check_fsk.isSelected());
            }
        });
        check_regeseur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_regesseur.setEnabled(!check_regeseur.isSelected());
            }
        });
    }

    public static void main(String[] args) {
        new Chillflix2();
    }
}
