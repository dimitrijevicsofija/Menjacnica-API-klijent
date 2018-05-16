package menjacnica.gui.kontroler;

import java.awt.EventQueue;
import java.awt.TextField;
import java.io.IOException;
import java.util.Map.Entry;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import menjacnica.Valuta;
import menjacnica.Zemlja;
import menjacnica.gui.Menjacnica;
import menjacnica.sistemskeoperacije.SOSacuvajKonverziju;
import menjacnica.util.URLConnectionUtil;

public class GUIKontroler {

	public static Menjacnica mg;
	public static final String URL2 = "http://free.currencyconverterapi.com/api/v3/convert?q=";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menjacnica frame = new Menjacnica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void konvertuj(Zemlja zemljaIz, Zemlja zemljaU, JTextField textFieldIznos1,
			JTextField textFieldIznos2) {

		String q = zemljaIz.getCurrencyId() + "_" + zemljaU.getCurrencyId();
		

		try {
			String content = URLConnectionUtil.getContent(URL2 + q);

			Gson gson = new GsonBuilder().create();
			JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
			JsonObject konverzija = jsonObject.get("results").getAsJsonObject();
			Valuta item = null;

			for (Entry<String, JsonElement> entry : konverzija.entrySet()) {
				item = gson.fromJson(entry.getValue().getAsJsonObject(), Valuta.class);
			}

			if (item == null) {
				JOptionPane j = new JOptionPane();
				j.showMessageDialog(j, "Ne moze se izvrsiti trazena konverzija.", "Greska!", j.ERROR_MESSAGE);
			} else {
				if (textFieldIznos1.getText().equals("")) {
					JOptionPane j = new JOptionPane();
					j.showMessageDialog(j, "Unesite iznos!", "Greska!", j.ERROR_MESSAGE);
				} else {
					try {
						double iznos = Double.parseDouble(textFieldIznos1.getText());

						textFieldIznos2.setText(Double.toString(item.getVal() * iznos));
						SOSacuvajKonverziju.izvrsi(item.getFr(), item.getTo(), item.getVal());
					} catch (NumberFormatException e) {
						JOptionPane j = new JOptionPane();
						j.showMessageDialog(j, "Uneti iznos mora biti broj!", "Greska!", j.ERROR_MESSAGE);
					}
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
