package menjacnica.sistemskeoperacije;

import java.util.LinkedList;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import menjacnica.Zemlja;
import menjacnica.util.URLConnectionUtil;

public class SOGetZemlje {
	
	public static final String URL1 = "http://free.currencyconverterapi.com/api/v3/countries";

	public static LinkedList<Zemlja> izvrsi() {
		try {
			String content = URLConnectionUtil.getContent(URL1);
			Gson gson = new GsonBuilder().create();
			JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
			JsonObject zemljeJson = jsonObject.get("results").getAsJsonObject();

			LinkedList<Zemlja> zemlje = new LinkedList<>();
			for (Entry<String, JsonElement> entry : zemljeJson.entrySet()) {
				Zemlja item = gson.fromJson(entry.getValue().getAsJsonObject(), Zemlja.class);
				zemlje.add(item);
			}

			return zemlje;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
