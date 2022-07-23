package com.arturk.entity;

public class Alien {
    private String planet;
    private Integer age;
    private String spaceShip;

    public Alien() {
    }

    public Alien(String planet, Integer age, String spaceShip) {
        this.planet = planet;
        this.age = age;
        this.spaceShip = spaceShip;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpaceShip() {
        return spaceShip;
    }

    public void setSpaceShip(String spaceShip) {
        this.spaceShip = spaceShip;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "planet='" + planet + '\'' +
                ", age='" + age + '\'' +
                ", spaceShip='" + spaceShip + '\'' +
                '}';
    }
}
