package com.producer_consumer.simulation.domain.entities;

import java.util.Random;

public class Product {
    private final String color;

    public Product() {
        this.color = generateRandomHexColor();
    }

    public Product(String color) {
        this.color = validateColor(color);
    }

    private String generateRandomHexColor() {
        Random random = new Random();

        int red = random.nextInt(230);
        int green = random.nextInt(230);
        int blue = random.nextInt(230);

        int variation = 100;
        red = clamp(red + random.nextInt(variation * 2 + 1) - variation);
        green = clamp(green + random.nextInt(variation * 2 + 1) - variation);
        blue = clamp(blue + random.nextInt(variation * 2 + 1) - variation);

        return String.format("#%02X%02X%02X", red, green, blue);
    }

    private int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    private String validateColor(String color) {
        if (color == null || !color.matches("#[0-9A-Fa-f]{6}")) {
            throw new IllegalArgumentException("Invalid color format. Must be a hex code in the format #RRGGBB.");
        }
        return color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Product{" +
                "color='" + color + '\'' +
                '}';
    }
}
