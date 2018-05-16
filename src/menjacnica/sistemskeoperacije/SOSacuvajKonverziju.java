package menjacnica.sistemskeoperacije;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import menjacnica.Konverzija;

public class SOSacuvajKonverziju {
	
	public static void izvrsi(String izValuta, String uValuta, double kurs) {
		Date datumVreme = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String sDate = sdf.format(datumVreme);

		Konverzija k = new Konverzija();
		k.setDatumVreme(sDate);
		k.setIzValuta(izValuta);
		k.setuValuta(uValuta);
		k.setKurs(kurs);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer;
		try {
			writer = new FileWriter("log.json", true);
			writer.write(gson.toJson(k));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
