package com.example.web44.model;


import jakarta.persistence.*;

@Entity
@Table(name="entries")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="x")
    private double x;

    @Column(name="y")
    private double y;

    @Column(name="r")
    private double r;

    @Column(name="hit")
    private boolean hit;

    @Column(name="response_time")
    private double responseTime;

    public Entry() {

    }
    public Entry(double x, double y, double r, boolean hit, double responseTime, User user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.responseTime = responseTime;
        this.user = user;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }
}
