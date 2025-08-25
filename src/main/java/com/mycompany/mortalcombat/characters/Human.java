package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;


/**
 * Класс, определяющий характеристики игрока.
 * 
 */
public class Human extends Player {

    private int points;
    private int experience;
    private int win;
    private int nextexperience;
    
    /**
     * Конструктор, определяющий параметры игрока.
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     *
     */

    public Human(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        this.points = 0;
        this.experience = 0;
        this.nextexperience = 40;
        this.win = 0;
        picture = new ImageIcon(this.getClass().getResource("/pictures/Human1.png"));

    }

    public int getPoints() {
        return this.points;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getNextExperience() {
        return this.nextexperience;
    }

    public int getWin() {
        return this.win;
    }

    public void setPoints(int p) {
        this.points += p;
    }

    public void setExperience(int e) {
        this.experience += e;
    }

    public void setNextExperience(int e) {
        this.nextexperience = e;
    }

    public void setWin() {
        this.win++;
    }

    @Override
    public String getName() {
        return "You";
    }

}
