package chartmaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class SearchWord {

	Hashtable<Integer, String> ChartTable = new Hashtable<Integer, String>();

	public void searchPattern(String blipText, String start, String end) {
		int i = 0;
		while (blipText.indexOf(start, i) != -1) {
			i = blipText.indexOf(start, i);

			int j = blipText.indexOf(end, i + 1);
			if (j == -1) {
				i++;
				continue;
			}
			String wrd = blipText.substring(i + start.length(), j);

			if (wrd.length() > 0)
				ChartTable.put(i, wrd);
			i++;
		}
	}

	public Hashtable<Integer, String> getWords() {
		return ChartTable;
	}

	public ArrayList<Integer> getIndex() {
		Enumeration<Integer> Ckeys = ChartTable.keys();
		List<Integer> list = new ArrayList<Integer>();
		while (Ckeys.hasMoreElements()) {
			list.add(Ckeys.nextElement());
		}
		Collections.sort(list);
		Collections.reverse(list);
		return (ArrayList<Integer>) list;
	}
}
