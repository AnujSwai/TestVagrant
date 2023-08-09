package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NewspaperSubscriptions {

	public static List<List<String>> findCombinationsWithinBudget(Map<String, Double> weeklyCosts, Double budget) {
		  Map<Double, List<String>> dp = new HashMap<>();
		  dp.put(0.0, new ArrayList<String>());

		  for (Map.Entry<String, Double> entry : weeklyCosts.entrySet()) {
		   String key = entry.getKey();
		   double cost = entry.getValue();
		   Map<Double, List<String>> newCombinations = new HashMap<>();

		   for (Map.Entry<Double, List<String>> dpEntry : dp.entrySet()) {
		    double currentBudget = dpEntry.getKey();
		    List<String> combination = dpEntry.getValue();
		    double newBudget = currentBudget + cost;

		    if (newBudget <= budget) {
		     List<String> newCombination = new ArrayList<>(combination);
		     newCombination.add(key);
		     newCombinations.put(newBudget, newCombination);
		    }
		   }

		   dp.putAll(newCombinations);
		  }

		  List<List<String>> validCombinations = new ArrayList<>();
		  for (List<String> combination : dp.values()) {
		   if (combination.size() >= 2) {
		    validCombinations.add(combination);
		   }
		  }

		  return validCombinations;
		 }

		 public static void main(String[] args) {

		  //Adding all newspapers data
		  Map<String, List<Double>> weeklyCosts = new HashMap<>();
		  weeklyCosts.put("\"TOI\"", Arrays.asList(3.0, 3.0, 3.0, 3.0, 3.0, 5.0, 6.0));
		  weeklyCosts.put("\"Hindu\"", Arrays.asList(2.5, 2.5, 2.5, 2.5, 2.5, 4.0, 4.0));
		  weeklyCosts.put("\"ET\"", Arrays.asList(4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 10.0));
		  weeklyCosts.put("\"BM\"", Arrays.asList(1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5));
		  weeklyCosts.put("\"HT\"", Arrays.asList(2.0, 2.0, 2.0, 2.0, 2.0, 4.0, 4.0));

		  //creating new HashMap and storing total count(Rs.) of all newspapers
		  Map<String, Double> weeklySums = new HashMap<>();
		  for (Map.Entry<String, List<Double>> entry : weeklyCosts.entrySet()) {
		   double su = 0;
		   for (Double x : entry.getValue()) {
		    su += x;
		   }
		   weeklySums.put(entry.getKey(), su);
		  }
		   
		  System.out.println("Enter weekly budget");
		  try (Scanner scanner = new Scanner(System.in)) {
			double weeklyBudget = scanner.nextDouble();
			  
			  
			  

			  List<List<String>> validCombinations = findCombinationsWithinBudget(weeklySums, weeklyBudget);

			  System.out.println("Valid Combinations:");
			  System.out.print(weeklyBudget+"=");
			  for (List<String> combination : validCombinations) {
			    String val =combination.toString().replace("[", "{").replace("]", "}");
			    
			    System.out.print(val +",");
			  }
		}}
		 }