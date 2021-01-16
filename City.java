/**
 * City class is used to represent each vertex/node of the graph.  
 * 
 *  @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class City implements Comparable<City>{
	public HashMap<String,Integer> neighbors=new HashMap<String,Integer>(); //the key is the name of the city, the Integer is the cost of the road
	public String name;
	boolean visited;
	boolean discovered;
	/**
	 * Default constructor
	 */
	public City() {
		this.name="";
		this.visited=false;
		this.discovered=false;
	}
	/**
	 * Constructor makes city with user input name
	 * @param name
	 */
	public City(String name) {
		this.name=name;
		this.visited=false;
		this.discovered=false;
	}
	/**
	 * compares two cities and returns -1,0,1 depending on the precedence of the name
	 */
	public int compareTo(City o1) {
		return this.getName().compareTo(o1.getName());
	}
	/**
	 * returns neighbor hashmap
	 * @return
	 */
	public HashMap<String,Integer> getNeighbor() {
		return this.neighbors;
	}
	/**
	 * returns name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * returns boolean visisted
	 * @return
	 */
	public boolean getVisited() {
		return this.visited;
	}
	/**
	 * returns boolean discovered
	 * @return
	 */
	public boolean getDiscovered() {
		return this.discovered;
	}
	/**
	 * sets neighbor hashmap with city key and cost value
	 * @param city
	 * @param cost
	 */
	public void setNeighbors(String city, int cost) {
		this.neighbors.put(city, cost);
	}
	/**
	 * sets visited boolean
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited= visited;
	}
	/**
	 * sets discovered boolean
	 * @param discovered
	 */
	public void setDiscovered(boolean discovered) {
		this.discovered=discovered;
	}
}
