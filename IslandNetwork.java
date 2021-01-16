/**
 * IslandNetwork class is used to that hold the graph.  
 * 
 *  @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.io.*;
import java.util.*;
import big.data.DataSource;

public class IslandNetwork {
	private HashMap<String, City> graph= new HashMap<String, City>();

	public void maxFlow(String from, String to) {
//		List<String> list=new ArrayList();
//		boolean bool=false;
//		int cost=0;
//		int total=0;
//		HashSet discovered=new HashSet();
//		maxFlow(from,to,list,discovered,cost,bool);
//		System.out.println("LIST: "+ list);
//		for(int i=0;i<list.size();i++) {
//			System.out.print(list.get(i));
//		}
	}
//	public void maxFlow(String from,String to,List<String> list,HashSet<String> discovered,int c,boolean bool) {
//		List<String> maxFlowList=list;
//		maxFlowList.add(from);
//		System.out.println(maxFlowList);
//		int cost=c;
//		Set<String> citySet= getGraph().keySet();
//		List<String> cityNames = new ArrayList<String> (citySet);
//		while(!bool) {
//		if(graph.containsKey(from)||graph.containsKey(to)) {
//			City temp= graph.get(from);
//			City temp2= graph.get(to);
//			temp.setVisited(true);
//					for(int i=0;i<cityNames.size();i++) {
//						if(temp.getNeighbor().containsKey(cityNames.get(i))) {
//							City temp3=graph.get(cityNames.get(i));
//							if(temp2.getName().equals(temp3.getName())) {
//								bool=true;
//							}
//						        if(!discovered.contains(temp3.getName())) {
//							     discovered.add(temp3.getName());
//							     maxFlow(temp3.getName(),to,maxFlowList,discovered,cost,bool);
//							     maxFlow(temp3.getName(),temp2.getName());
//						        }
//						}
//					}
//		}else {
//			maxFlowList.add("At least one of the cities does not exist.");
//		}
//		}
//	}
	/**
	 * returns a list of dfs with city from
	 * @param from
	 * @return
	 */
	public List<String> dfs(String from){
		List<String> list=new ArrayList();
		HashSet discovered=new HashSet();
		dfs(from,list,discovered);
		return list;
	}
	/**
	 * loads big data jar using url
	 * @param url
	 * @return
	 */
	public static IslandNetwork loadFromFile(String url) {
		IslandNetwork temp= new IslandNetwork();
			DataSource ds = DataSource.connectXML(url);
            ds.load();
			System.out.println("Map loaded.\n");
            String cityNamesStr=ds.fetchString("cities");
            String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
            String roadNamesStr=ds.fetchString("roads");
            roadNamesStr=roadNamesStr.substring(1,roadNamesStr.length()-1);
            String[] roadNames=roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");
            for(int i=0; i<cityNames.length;i++) {
            	temp.graph.put(cityNames[i],new City(cityNames[i]));
            }
	        for(int i=0;i<roadNames.length;i++) {
	        	try {
	        	String[] split= roadNames[i].split(",");
	        	temp.graph.get(split[0]).setNeighbors(split[1],Integer.parseInt(split[2])); 
	        	}catch(NullPointerException e) {
	        		
	        	}
	        }
		return temp;
	}
	/**
	 * helper method for dfs
	 * @param from
	 * modifies list of dfs method
	 * @param list
	 * hashset discoverd used to check if city has been discovered
	 * @param discovered
	 */
	public void dfs(String from,List<String>list,HashSet<String> discovered) {
		List<String> dfsList=list;
		dfsList.add(from);
		Set<String> citySet= getGraph().keySet();
		List<String> cityNames = new ArrayList<String> (citySet);
		if(graph.containsKey(from)) {
			City temp= graph.get(from);
			temp.setVisited(true);
					for(int i=0;i<cityNames.size();i++) {
						if(temp.getNeighbor().containsKey(cityNames.get(i))) {
							City temp2=graph.get(cityNames.get(i));
					        if(!discovered.contains(temp2.getName())) {
						     discovered.add(temp2.getName());
						     dfs(cityNames.get(i),list,discovered);
						     dfs(cityNames.get(i));
					        }
						}
					}
		}else {
			dfsList.add("City does not exist.");
		}
	}
	/**
	 * returns graph of IslandNetwork
	 * @return
	 */
	public HashMap<String, City>getGraph() {
		return this.graph;
	}
}
