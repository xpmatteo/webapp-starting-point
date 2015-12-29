package it.xpug.toolkit.db;

import static java.util.Collections.*;

import java.util.*;

public class ListOfRows {
	private List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
	private HashMap<String, Object> currentRow;

	public void newRow() {
		currentRow = new HashMap<String, Object>();
		rows.add(currentRow);
	}

	public void put(String columnName, Object object) {
		currentRow.put(columnName.toLowerCase(), object);
	}

	public int size() {
		return rows.size();
	}

	public Map<String, Object> get(int rowIndex) {
		return rows.get(rowIndex);
	}

	public Collection<Map<String, Object>> toCollection() {
		return unmodifiableCollection(rows);
	}

}
