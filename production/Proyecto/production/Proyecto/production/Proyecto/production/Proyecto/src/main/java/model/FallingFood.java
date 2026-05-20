package main.java.model;

import java.awt.*;

public class FallingFood extends Entity implements Runnable {
    private String name;
    private boolean isPositive;
    private int speed = 22;
    private int[] boxes;
    private int initialDelay;

    public FallingFood(int[] boxes, String imagePath, String name, boolean isPositive, int initialDelay) {
        super(boxes[(int) (Math.random() * boxes.length)], -20, Entity.uploadImage(imagePath)); // convierte el String a BufferedImage
        this.boxes = boxes;
        this.name = name;
        this.isPositive = isPositive;
        this.initialDelay = initialDelay;

    }


    @Override
    public void run() {
        {
            try {
                Thread.sleep(initialDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                y += speed;
                if (y >= 560) update();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update() {
        y = -20; // desde donde cae la comida
        x = boxes[(int) (Math.random() * boxes.length)];
        setActive(true);


    }

    @Override
    public void toDraw(Graphics g) {
        g.drawImage(getSprite(), x, y, 150, 150, null);
    }

    public String getName() {
        return name;
    }

    public boolean isPositive() {
        return isPositive;
    }
}

