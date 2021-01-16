/**
 * IslandNetwork class is used to that hold the graph.  
 * 
 *  @author Willie Xu
 *	email: xuwillie1@gmail.com
 *	Class CSE 214-R10
 */

import java.io.*;
import java.util.*;


public class IslandDesigner {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		boolean quit=true;
		City temp= new City();
		IslandNetwork network= new IslandNetwork();
		System.out.println("Welcome to the Island Designer, because, when you're trying to stay above water, Seas get degrees!\n");
		System.out.print("Please enter an url: ");
		String url=sc.nextLine();
		network=IslandNetwork.loadFromFile(url);
		//Print Cities
		System.out.println("Cities:\n---------------------");
		Collection<City> values = network.getGraph().values();
		ArrayList<City> listOfValues = new ArrayList<City>(values);
		 for (int i = 0; i < listOfValues.size(); i++) {
	            for (int j = i + 1; j < listOfValues.size(); j++) {
	            	if (listOfValues.get(i).compareTo(listOfValues.get(j))>0){
	                    temp = listOfValues.get(i);
	                    listOfValues.set(i, listOfValues.get(j));
	                    listOfValues.set(j, temp);
	                }
	            }
		 }
		 for (int i = 0; i < listOfValues.size(); i++) {
		 System.out.println(listOfValues.get(i).getName());
		 }
		ArrayList<City> roadMap = new ArrayList<City>(values);
		Set<String> citySet= network.getGraph().keySet();
		List<String> cityNames = new ArrayList<String> (citySet);
//
		 //Print Roads and Capacity 
		 System.out.println("\nRoad\t\t\t\t\tCapacity\n------------------------------------------------");
		 for(int i=0;i<roadMap.size();i++) {
				 for(int j=0;j<cityNames.size();j++) {
					 if(roadMap.get(i).getNeighbor().containsKey(cityNames.get(j))) {
						 System.out.printf("%-18s to %-15s\t%3d\n",roadMap.get(i).getName(),cityNames.get(j),roadMap.get(i).getNeighbor().get(cityNames.get(j)));
					 }
				 }
		 }
		 System.out.println();
		System.out.println("Menu:\n\tD) Destinations reachable (Depth First Search)\n" + 
				"\tF) Maximum Flow\n\tS) Shortest Path (Extra Credit)\n\tQ) Quit");
		while(quit) {
			System.out.print("Please select an option: ");
			char choice=sc.nextLine().charAt(0);
			choice= Character.toLowerCase(choice);
			//dfs
			if(choice=='d') {
				List<String> tempList= new ArrayList<String>();
				System.out.print("Please enter a starting city: ");
				String from= sc.nextLine();
				tempList=network.dfs(from);
				System.out.println("DFS starting From "+from+":");
				for(int i=1;i<tempList.size();i++) {
					System.out.print(tempList.get(i));
					if(i<tempList.size()-1) {
						System.out.print(", ");
					}else {
						System.out.print("\n");
					}
				}
				for(int i=0;i<roadMap.size();i++) {
					roadMap.get(i).setVisited(false);
				}
			}
			//maxflow
			if(choice=='f') {
				System.out.print("Please enter a starting city: ");
				String from = sc.nextLine();
				System.out.print("Please enter a destination: ");
				String to = sc.nextLine();
				network.maxFlow(from, to);
				for(int i=0;i<roadMap.size();i++) {
					roadMap.get(i).setVisited(false);
				}
			}
			//extra credit
			if(choice=='s') {
				System.out.println("Function does not work right now, please check back later.");
			}
			//quit
			if(choice=='q') {
				System.out.println("You can go your own way! Goodbye!");
				quit=false;
			}
		}
	}
}
