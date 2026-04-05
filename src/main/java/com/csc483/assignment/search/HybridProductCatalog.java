package com.csc483.assignment.search;

import java.util.*;

/**
 * HybridProductCatalog - Question 1 Part C
 * Hybrid search strategy:
 * - O(log n) search by ID using binary search on sorted list
 * - O(1) average search by name using HashMap index
 * - Efficient addProduct that maintains sorted order
 */
public class HybridProductCatalog {

    private final List<Product> idSortedList = new ArrayList<>();
    private final Map<String, List<Product>> nameIndex = new HashMap<>();

    /**
     * Adds a new product while maintaining sorted order by ID
     * @param newProduct the product to add
     */
    public void addProduct(Product newProduct) {
        // Binary insertion to keep the list sorted by productId
        int pos = Collections.binarySearch(idSortedList, newProduct);
        if (pos < 0) {
            pos = -pos - 1;
        }
        idSortedList.add(pos, newProduct);

        // Update name index for fast name searches
        String key = newProduct.getProductName().toLowerCase();
        nameIndex.computeIfAbsent(key, k -> new ArrayList<>()).add(newProduct);
    }

    /**
     * Search by ID using binary search - O(log n)
     * @param targetId the product ID to find
     * @return Product if found, null otherwise
     */
    public Product searchById(int targetId) {
        // Create a dummy product for binary search
        Product dummy = new Product(targetId, "", "", 0.0, 0);
        int pos = Collections.binarySearch(idSortedList, dummy);
        return (pos >= 0) ? idSortedList.get(pos) : null;
    }

    /**
     * Search by name using HashMap index - O(1) average case
     * @param targetName the product name to find (case-insensitive)
     * @return list of matching products (usually 1)
     */
    public List<Product> searchByName(String targetName) {
        return nameIndex.getOrDefault(targetName.toLowerCase(), Collections.emptyList());
    }

    /**
     * Returns all products as array (for compatibility with other methods)
     */
    public Product[] getAllProductsAsArray() {
        return idSortedList.toArray(new Product[0]);
    }

    public int size() {
        return idSortedList.size();
    }
}
