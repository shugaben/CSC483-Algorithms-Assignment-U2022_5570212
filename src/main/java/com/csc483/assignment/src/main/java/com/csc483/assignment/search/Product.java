package com.csc483.assignment.search;

/**
 * Standard search implementations for Question 1 Part B
 */
public class SearchUtils {

    public static Product sequentialSearchById(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.getProductId() == targetId) return p;
        }
        return null;
    }

    public static Product binarySearchById(Product[] products, int targetId) {
        int left = 0, right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == targetId) return products[mid];
            if (products[mid].getProductId() < targetId) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static Product searchByName(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) return p;
        }
        return null;
    }
}
