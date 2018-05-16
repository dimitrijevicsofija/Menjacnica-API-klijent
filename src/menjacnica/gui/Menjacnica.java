package menjacnica.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.Konverzija;
import menjacnica.Valuta;
import menjacnica.Zemlja;
import menjacnica.gui.kontroler.GUIKontroler;
import menjacnica.sistemskeoperacije.SOGetZemlje;
import menjacnica.util.URLConnectionUtil;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class Menjacnica extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JButton btnKonvertuj;
	private JComboBox comboBoxIzZemlje;
	private JComboBox comboBoxUZemlju;
	private JLabel lblIznos1;
	private JLabel lblIznos2;
	private JTextField textFieldIznos1;
	private JTextField textFieldIznos2;

	/**
	 * Create the frame.
	 */
	public Menjacnica() {
		setTitle("Menjacnica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getBtnKonvertuj());
		contentPane.add(getComboBoxIzZemlje());
		contentPane.add(getComboBoxUZemlju());
		contentPane.add(getLblIznos1());
		contentPane.add(getLblIznos2());
		contentPane.add(getTextFieldIznos1());
		contentPane.add(getTextFieldIznos2());
	}

	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(47, 48, 118, 29);
		}
		return lblIzValuteZemlje;
	}

	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(277, 48, 118, 29);
		}
		return lblUValutuZemlje;
	}

	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUIKontroler.konvertuj((Zemlja) comboBoxIzZemlje.getSelectedItem(),
							(Zemlja) comboBoxUZemlju.getSelectedItem(), textFieldIznos1, textFieldIznos2);
				}
			});
			btnKonvertuj.setBounds(163, 200, 97, 25);
		}
		return btnKonvertuj;
	}

	private JComboBox getComboBoxIzZemlje() {
		if (comboBoxIzZemlje == null) {
			comboBoxIzZemlje = new JComboBox();
			comboBoxIzZemlje.setBounds(47, 101, 118, 22);

			LinkedList<Zemlja> zemlje = SOGetZemlje.izvrsi();
			for (int i = 0; i < zemlje.size(); i++) {
				comboBoxIzZemlje.addItem(zemlje.get(i));
			}
		}
		return comboBoxIzZemlje;
	}

	private JComboBox getComboBoxUZemlju() {
		if (comboBoxUZemlju == null) {
			comboBoxUZemlju = new JComboBox();
			comboBoxUZemlju.setBounds(277, 101, 118, 22);

			LinkedList<Zemlja> zemlje = SOGetZemlje.izvrsi();
			for (int i = 0; i < zemlje.size(); i++) {
				comboBoxUZemlju.addItem(zemlje.get(i));
			}
		}
		return comboBoxUZemlju;
	}

	private JLabel getLblIznos1() {
		if (lblIznos1 == null) {
			lblIznos1 = new JLabel("Iznos:");
			lblIznos1.setBounds(47, 140, 56, 16);
		}
		return lblIznos1;
	}

	private JLabel getLblIznos2() {
		if (lblIznos2 == null) {
			lblIznos2 = new JLabel("Iznos:");
			lblIznos2.setBounds(277, 140, 56, 16);
		}
		return lblIznos2;
	}

	private JTextField getTextFieldIznos1() {
		if (textFieldIznos1 == null) {
			textFieldIznos1 = new JTextField();
			textFieldIznos1.setBounds(49, 166, 116, 22);
			textFieldIznos1.setColumns(10);
		}
		return textFieldIznos1;
	}

	private JTextField getTextFieldIznos2() {
		if (textFieldIznos2 == null) {
			textFieldIznos2 = new JTextField();
			textFieldIznos2.setText("");
			textFieldIznos2.setBounds(279, 166, 116, 22);
			textFieldIznos2.setColumns(10);
		}
		return textFieldIznos2;
	}	
}
