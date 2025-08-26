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
     *
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

    /**
     * Возвращает количество очков игрока.
     *
     * @return количество очков
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Возвращает количество опыта игрока.
     *
     * @return количество опыта
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * Возвращает количество опыта до следующего уровня.
     *
     * @return опыт до следующего уровня
     */
    public int getNextExperience() {
        return this.nextexperience;
    }

    /**
     * Возвращает количество побед игрока.
     *
     * @return количество побед
     */
    public int getWin() {
        return this.win;
    }

    /**
     * Устанавливает количество очков игрока.
     *
     * @param p количество очков для добавления
     */
    public void setPoints(int p) {
        this.points += p;
    }

    /**
     * Устанавливает количество опыта игрока.
     *
     * @param e количество опыта для добавления
     */
    public void setExperience(int e) {
        this.experience += e;
    }

    /**
     * Устанавливает количество опыта до следующего уровня.
     *
     * @param e количество опыта до следующего уровня
     */
    public void setNextExperience(int e) {
        this.nextexperience = e;
    }

    /**
     * Увеличивает счетчик побед игрока.
     */
    public void setWin() {
        this.win++;
    }

    /**
     * Возвращает имя игрока.
     *
     * @return имя игрока
     */
    @Override
    public String getName() {
        return "You";
    }

}
