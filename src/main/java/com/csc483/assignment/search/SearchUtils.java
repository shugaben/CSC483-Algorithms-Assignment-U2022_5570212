package com.csc483.assignment.search;

/**
 * Search utility methods for Question 1 Part B
 * Contains sequential search, binary search, and name search.
 */
public class SearchUtils {

    /**
     * Sequential search by product ID - O(n) time
     * @param products array of products
     * @param targetId the ID to find
     * @return the Product if found, null otherwise
     */
    public static Product sequentialSearchById(Product[] products, int targetId) {
        for (Product p : products) {
            if (p.getProductId() == targetId) {
                return p;
            }
        }
        return null;
    }

    /**
     * Binary search by product ID - O(log n) time
     * Precondition: products array must be sorted by productId
     * @param products sorted array of products
     * @param targetId the ID to find
     * @return the Product if found, null otherwise
     */
    public static Product binarySearchById(Product[] products, int targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (products[mid].getProductId() == targetId) {
                return products[mid];
            } else if (products[mid].getProductId() < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    /**
     * Sequential search by product name (names are not sorted)
     * @param products array of products
     * @param targetName the name to find (case-insensitive)
     * @return the Product if found, null otherwise
     */
    public static Product searchByName(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) {
                return p;
            }
        }
        return null;
    }
}
