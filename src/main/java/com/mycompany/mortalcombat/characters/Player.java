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
     * Конструктор для формирования параметров персонажа.
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

    /**
     * Повышает уровень персонажа.
     */
    public void setLevel() {
        this.level++;
    }

    /**
     * Изменяет здоровье персонажа.
     *
     * @param h количество здоровья для добавления/уменьшения
     */
    public void setHealth(double h) {
        if (h < 0 && weakness) {
            this.health += h * 1.25;
        } else {
            this.health += h;
        }
    }

    /**
     * Устанавливает здоровье персонажа.
     *
     * @param h значение здоровья
     */
    public void setNewHealth(int h) {
        this.health = h;
    }

    /**
     * Изменяет урон персонажа.
     *
     * @param d количество урона для добавления/уменьшения
     */
    public void setDamage(double d) {
        this.damage += d;
    }

    /**
     * Устанавливает тип атаки персонажа.
     *
     * @param a тип атаки
     */
    public void setAttackType(int a) {
        this.attack = a;
    }

    /**
     * Изменяет максимальное здоровье персонажа.
     *
     * @param h количество здоровья для добавления/уменьшения
     */
    public void setMaxHealth(int h) {
        this.maxhealth += h;
    }

    /**
     * Возвращает уровень персонажа.
     *
     * @return уровень персонажа
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Возвращает текущее здоровье персонажа.
     *
     * @return текущее здоровье
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Возвращает урон персонажа.
     *
     * @return урон персонажа
     */
    public double getDamage() {
        if (weakness) {
            return this.damage * 0.5;
        }
        return this.damage;
    }

    /**
     * Возвращает тип атаки персонажа.
     *
     * @return тип атаки
     */
    public int getAttack() {
        return this.attack;
    }

    /**
     * Возвращает максимальное здоровье персонажа.
     *
     * @return максимальное здоровье
     */
    public int getMaxHealth() {
        return this.maxhealth;
    }

    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    public String getName() {
        return "";
    }

    /**
     * Возвращает изображение персонажа.
     *
     * @return изображение персонажа
     */
    public ImageIcon getPicture() {
        return picture;
    }

    /**
     * Устанавливает дебафф на персонажа.
     *
     * @param level уровень дебаффа
     */
    public void setDebuff(int level) {
        timer = level;
        weakness = true;
    }

    /**
     * Снимает дебафф с персонажа.
     */
    public void deleteDebuff() {
        if (timer > 0) {
            timer--;
        } else {
            weakness = false;
        }
    }

    /**
     * Устанавливает действие персонажа.
     *
     * @param a действие персонажа
     */
    public void setAction(ActionType a) {
        this.action = a;
    }

    /**
     * Возвращает действие персонажа.
     *
     * @return действие персонажа
     */
    public ActionType getAction() {
        return this.action;
    }

    /**
     * Перечисление возможных действий игроков.
     */
    public enum ActionType {
        ATTACK,
        DEFEND,
        WEAKEN,
        REGENERATE,
        NONE
    }
}
