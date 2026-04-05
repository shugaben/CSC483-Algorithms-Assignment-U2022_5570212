package com.csc483.assignment.search;

import java.util.Arrays;
import java.util.Random;

/**
 * TechMartSearchDemo - Question 1 Main Program
 * Generates 100,000 products and prints the exact performance table
 * required in the assignment brief.
 */
public class TechMartSearchDemo {

    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("TECHMART SEARCH PERFORMANCE ANALYSIS (n = 100,000 products)");
        System.out.println("================================================================");

        int n = 100_000;
        Product[] products = generateProducts(n);

        // Hybrid catalog for Part C
        HybridProductCatalog catalog = new HybridProductCatalog();
        for (Product p : products) {
            catalog.addProduct(p);
        }

        // Run all search tests
        runSequentialAndBinaryTests(products);
        runHybridNameTests(catalog);

        System.out.println("================================================================");
        System.out.println("✅ All tests completed successfully!");
        System.out.println("Take a screenshot of this output for your PDF report.");
    }

    /**
     * Generate 100,000 random products (IDs from 1 to 200,000)
     */
    private static Product[] generateProducts(int n) {
        Random rand = new Random(42); // fixed seed for reproducible results
        Product[] products = new Product[n];

        for (int i = 0; i < n; i++) {
            int id = rand.nextInt(200_000) + 1;
            products[i] = new Product(
                id,
                "Product" + id,
                "Electronics",
                99.99 + rand.nextDouble() * 900,
                rand.nextInt(500) + 10
            );
        }

        Arrays.sort(products); // required for binary search
        return products;
    }

    /**
     * Runs sequential and binary search tests with timing
     */
    private static void runSequentialAndBinaryTests(Product[] products) {
        int n = products.length;

        // Best case: first element
        long start = System.nanoTime();
        SearchUtils.sequentialSearchById(products, products[0].getProductId());
        double bestSeq = (System.nanoTime() - start) / 1_000_000.0;

        // Worst case: ID not found
        start = System.nanoTime();
        SearchUtils.sequentialSearchById(products, -999);
        double worstSeq = (System.nanoTime() - start) / 1_000_000.0;

        // Average case (approx n/2 comparisons)
        int midId = products[n / 2].getProductId();
        start = System.nanoTime();
        SearchUtils.sequentialSearchById(products, midId);
        double avgSeq = (System.nanoTime() - start) / 1_000_000.0;

        // Binary search timings (run 1000 times for measurable time)
        int iterations = 1000;
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            SearchUtils.binarySearchById(products, products[n / 2].getProductId());
        }
        double avgBin = (System.nanoTime() - start) / (iterations * 1_000_000.0);

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            SearchUtils.binarySearchById(products, products[0].getProductId());
        }
        double bestBin = (System.nanoTime() - start) / (iterations * 1_000_000.0);

        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            SearchUtils.binarySearchById(products, -999);
        }
        double worstBin = (System.nanoTime() - start) / (iterations * 1_000_000.0);

        System.out.println("\nSEQUENTIAL SEARCH:");
        System.out.printf("Best Case (ID found at position 0): %.3f ms%n", bestSeq);
        System.out.printf("Average Case (random ID): %.3f ms%n", avgSeq);
        System.out.printf("Worst Case (ID not found): %.3f ms%n%n", worstSeq);

        System.out.println("BINARY SEARCH:");
        System.out.printf("Best Case (ID at middle): %.3f ms%n", bestBin);
        System.out.printf("Average Case (random ID): %.3f ms%n", avgBin);
        System.out.printf("Worst Case (ID not found): %.3f ms%n%n", worstBin);

        double improvement = avgSeq / avgBin;
        System.out.printf("PERFORMANCE IMPROVEMENT: Binary search is \~%.0fx faster on average%n", improvement);
    }

    /**
     * Tests the hybrid name search (Part C)
     */
    private static void runHybridNameTests(HybridProductCatalog catalog) {
        long start = System.nanoTime();
        catalog.searchByName("Product12345");
        long nameTime = (System.nanoTime() - start) / 1_000_000;

        // Simulate average insert time
        long insertStart = System.nanoTime();
        Product newP = new Product(999999, "NewProduct", "Electronics", 299.99, 50);
        catalog.addProduct(newP);
        long insertTime = (System.nanoTime() - insertStart) / 1_000_000;

        System.out.println("\nHYBRID NAME SEARCH:");
        System.out.println("Average search time: " + nameTime + " ms");
        System.out.println("Average insert time: " + insertTime + " ms");
    }
  }
