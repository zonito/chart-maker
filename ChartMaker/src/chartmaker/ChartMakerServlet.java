package chartmaker;

import java.util.ArrayList;
import java.util.Hashtable;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Element;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.Image;
import com.google.wave.api.Range;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

@SuppressWarnings("serial")
public class ChartMakerServlet extends AbstractRobotServlet {

	Wavelet wavelet;

	@Override
	public void processEvents(RobotMessageBundle bundle) {

		wavelet = bundle.getWavelet();
		if (bundle.wasSelfAdded()) {
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			textView
					.appendMarkup("Thank you for adding Chart-Maker. \n\r" +
							" Now you can add charts in your wave. \n\r" +
							" To know more about chart-maker: http://u.voizle.com/chartgooglecode");
		}

		for (Event e : bundle.getEvents()) {
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				makeChart(e);
			}
		}
	}

	public void makeChart(Event e) {
		try {
			SearchWord searchWord = new SearchWord();
			TextView cont = e.getBlip().getDocument();
			String blipText = cont.getText();
			searchWord.searchPattern(blipText, "{", "}");
			Hashtable<Integer, String> ChartTable = searchWord.getWords();
			ArrayList<Integer> list = searchWord.getIndex();

			for (Integer i : list) {
				String pattern = ChartTable.get(i);
				String[] filter = pattern.trim().split(" ");
				String makeQuery = "http://chart.apis.google.com/chart?chs=250x100";
				if (filter.length == 2) {
					String code = filter[0];
					if (!validateValue(filter[1]))
						return;
					String label = filter[1].replace(",", "|");

					if (code.equals("p3") || code.equals("p")
							|| code.equals("r")) {
						makeQuery += "&chl=" + label;
					}
					if (code.equals("bhs") || code.equals("bvs")) {
						makeQuery += "&chbh=a";
					}
					if (code.equals("v") || code.equals("p3")
							|| code.equals("p") || code.equals("bhs")
							|| code.equals("bvs")) {
						makeQuery += "&chdl=" + label;
					}
					if (code.equals("ls") || code.equals("lc")) {
						makeQuery += "&chtt=" + pattern;
					}

					if (code.equals("ls") || code.equals("lc")
							|| code.equals("bhs") || code.equals("bvs")
							|| code.equals("p3") || code.equals("p")
							|| code.equals("v") || code.equals("r")) {
						makeQuery += "&cht=" + code;
					} else
						return;

					makeQuery += "&chd=t:" + filter[1];
				}
				cont.delete(new Range(i, (2 + i + pattern.length())));

				Element img = new Image();
				img.setProperty("url", makeQuery);
				cont.insertElement(i, img);
			}
		} catch (Exception ex) {
			return;
		}
	}

	public boolean validateValue(String value) {
		String[] vals = value.split(",");
		for (String str : vals) {
			try {
				@SuppressWarnings("unused")
				int val = Integer.valueOf(str);
				return true;
			} catch (Exception ex) {
				return false;
			}
		}
		return false;
	}
}
