package com.some_domain.www.multithreadingproblem_2;

public class Ball {
    private String id;
    private BallColor color;
    private String desc;

    public Ball() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BallColor getColor() {
        return color;
    }

    public void setColor(BallColor color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "id= " + id + ", color=" + color + ", desc='" + desc;
    }
}
