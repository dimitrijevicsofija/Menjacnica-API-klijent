package menjacnica.sistemskeoperacije;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

		String object = gson.toJson(k);
		JsonObject jsonk = gson.fromJson(object, JsonObject.class);

		JsonArray istorijaKonverzija = null;

		FileWriter writer;
		FileReader reader;

		try {
			reader = new FileReader("log.json");
			istorijaKonverzija = gson.fromJson(reader, JsonArray.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (istorijaKonverzija == null)
				istorijaKonverzija = new JsonArray();
			istorijaKonverzija.add(jsonk);

			writer = new FileWriter("log.json");
			writer.write(gson.toJson(istorijaKonverzija));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
