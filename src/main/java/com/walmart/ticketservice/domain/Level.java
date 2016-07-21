/**
 * 
 */
package com.walmart.ticketservice.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.walmart.ticketservice.util.CommonUtils;
import com.walmart.ticketservice.util.Predicate;

/**
 * @author Gopal 
 * Domain object to store level details and row list
 */
@XmlRootElement(name = "Level")
@XmlAccessorType(XmlAccessType.FIELD)
public class Level implements Comparable<Level> {
	private int levelId;
	private String levelIdentifier;
	private String levelName;
	private String price;
	private int rows;
	private int totalSeats;
	private int availableSeats;
	private Map<Integer, Row> rowMap;
	private List<Row> availableRowList;

	public Level() {

	}

	public Level(int levelId, String levelIdentifier, String levelName,
			String price, int rows, int totalSeats, int availableSeats,
			Map<Integer, Row> rowMap) {
		this.levelId = levelId;
		this.levelIdentifier = levelIdentifier;
		this.levelName = levelName;
		this.price = price;
		this.rows = rows;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.rowMap = rowMap;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public String getLevelIdentifier() {
		return levelIdentifier;
	}

	public void setLevelIdentifier(String levelIdentifier) {
		this.levelIdentifier = levelIdentifier;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvailableSeats() {
		List<Row> availRows = getAvailableRowList();
		availableSeats = 0;
		for (Row availRow : availRows) {
			availableSeats = availableSeats + availRow.getAvailableSeats();
		}
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Map<Integer, Row> getRowMap() {
		return rowMap;
	}

	public void setRowMap(Map<Integer, Row> rowMap) {
		this.rowMap = rowMap;
	}

	public List<Row> getAvailableRowList() {
		this.availableRowList = new ArrayList<Row>();
		Predicate<Row> availableRowsPredicate = new Predicate<Row>() {
			public boolean apply(Row row) {
				if (row.getAvailableSeats() > 0)
					return true;
				else
					return false;
			}
		};
		Collection<Row> availRows = CommonUtils.filter(getRowMap().values(),
				availableRowsPredicate);
		Collections.sort(this.availableRowList);
		setAvailableRowList((List<Row>) availRows);
		return this.availableRowList;
	}

	public void setAvailableRowList(List<Row> availableRowList) {
		this.availableRowList = availableRowList;
	}

	@Override
	public String toString() {
		return "Level [ levelId=" + levelId + ", levelIdentifier="
				+ levelIdentifier + ", levelName=" + levelName + ", price="
				+ price + ", rows=" + rows + ", totalSeats=" + totalSeats
				+ ", availableSeats=" + availableSeats + ", rowMap =" + rowMap
				+ " ]";
	}

	public int compareTo(Level level2) {
		return this.getLevelId() - level2.getLevelId();
	}
}
