package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 * Класс, определяющий общие параметры персонажей.
 * 
 */

public class Player {

    private int level;
    private int health;
    private int maxhealth;
    private double damage;
    private int attack;
    public ImageIcon picture;
    private boolean weakness = false;
    private int timer = 0;
    private ActionType action;
    
    /**
     * Конструктор для формирования параметров персонажа
     *
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */

    public Player(int level, int health, double damage, int attack) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxhealth = health;
    }

    public void setLevel() {
        this.level++;
    }

    public void setHealth(double h) {
        if (h < 0 && weakness) {
            this.health += h * 1.25;
        } else {
            this.health += h;
        }
    }

    public void setNewHealth(int h) {
        this.health = h;
    }

    public void setDamage(double d) {
        this.damage += d;
    }

    public void setAttackType(int a) {
        this.attack = a;
    }

    public void setMaxHealth(int h) {
        this.maxhealth += h;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    public double getDamage() {
        if (weakness) {
            return this.damage * 0.5;
        }
        return this.damage;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getMaxHealth() {
        return this.maxhealth;
    }

    public String getName() {
        return "";
    }
    
    /**
     * геттер для картинки персонажа.
     * @return картинка
     */

    public ImageIcon getPicture() {
        return picture;
    }
    
    /**
     * сеттер для дебаффа.
     *
     */

    public void setDebuff(int level) {
        timer = level;
        weakness = true;
    }
    
    /**
     * метод для снятия дебаффа.
     *
     */
    public void deleteDebuff() {
        if (timer > 0) {
            timer--;
        } else {
            weakness = false;
        }
    }
    
    public void setAction(ActionType a) {
        this.action = a;
    }

    public ActionType getAction() {
        return this.action;
    }
    
    /**
     * Класс с перечислением возможных действий игроков.
     *
     */
    
    public enum ActionType {
        ATTACK,
        DEFEND,
        WEAKEN,
        REGENERATE,
        NONE
    }
}
