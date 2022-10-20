package com.example.hw03;

import java.util.HashMap;

public class DB {
    private static HashMap<String, Product> map = new HashMap<>();

    static {
        Product iPhone = new Product("1", "iPhone 12 Pro", "A new generation of iPhone", 899, 100);
        Product huawei = new Product("2", "Huawei Mate 40 Pro", "A new generation of Huawei", 999, 100);
        Product samsung = new Product("3", "Samsung Galaxy S20", "A new generation of Samsung", 799, 100);

        map.put("1", iPhone);
        map.put("2", huawei);
        map.put("3", samsung);
    }

    public static Product getProduct(String PID) {
        return map.get(PID);
    }
}
