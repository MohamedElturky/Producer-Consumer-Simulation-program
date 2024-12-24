package com.producer_consumer.simulation.components;

import java.awt.Color;
import java.util.Random;

public class Product {
    private static final Random random = new Random();
    private final Color color;

    public Product() {
        this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public Color getColor() {
        return color;
    }
}
