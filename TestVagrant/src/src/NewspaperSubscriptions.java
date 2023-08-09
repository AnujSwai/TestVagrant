package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewspaperSubscriptions {

    public static void main(String[] args) {
        // Define the newspaper data
        Map<String, double[]> newspapers = new HashMap<>();
        newspapers.put("TOI", new double[]{3, 3, 3, 3, 3, 5, 6});
        newspapers.put("Hindu", new double[]{2.5, 2.5, 2.5, 2.5, 2.5, 4, 4});
        newspapers.put("ET", new double[]{4, 4, 4, 4, 4, 4, 10});
        newspapers.put("BM", new double[]{1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5});
        newspapers.put("HT", new double[]{2, 2, 2, 2, 2, 4, 4});
        
        // Input the user's weekly budget
        double budget = getInput("Enter your weekly budget: ");

        // Generate combinations of newspapers within the budget
        List<List<String>> validCombinations = new ArrayList<>();
        for (int comboLength = 1; comboLength <= newspapers.size(); comboLength++) {
            generateCombinations(new ArrayList<>(newspapers.keySet()), comboLength, validCombinations);
        }

        // Display valid combinations within the budget
        for (List<String> combo : validCombinations) {
            double totalCost = getTotalCost(combo, newspapers);
            if (totalCost <= budget) {
                System.out.println(combo);  //+ ": Total Cost - " + totalCost);
            }
        }
    }

    private static double getInput(String prompt) {
        // Use a scanner or any other method to get user input
        // For simplicity, we'll use a hardcoded value here
        return 40;
    }

    private static void generateCombinations(List<String> items, int length, List<List<String>> result) {
        generateCombinationsHelper(items, length, 0, new ArrayList<>(), result);
    }

    private static void generateCombinationsHelper(List<String> items, int length, int start, List<String> current,
                                                   List<List<String>> result) {
        if (current.size() == length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < items.size(); i++) {
            current.add(items.get(i));
            generateCombinationsHelper(items, length, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    private static double getTotalCost(List<String> combo, Map<String, double[]> newspapers) {
        double totalCost = 0;
        for (String newspaper : combo) {
            double[] prices = newspapers.get(newspaper);
            totalCost += getMinPrice(prices);
        }
        return totalCost;
    }

    private static double getMinPrice(double[] prices) {
        double minPrice = Double.MAX_VALUE;
        for (double price : prices) {
            minPrice = Math.min(minPrice, price);
        }
        return minPrice;
    }
}

