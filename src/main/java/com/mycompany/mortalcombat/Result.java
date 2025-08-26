package com.mycompany.mortalcombat;

/**
 * Класс для хранения результатов игры.
 *
 */
public class Result {

    private String name;
    private int points;

    /**
     * Создает новый результат.
     *
     * @param n имя игрока
     * @param p количество очков
     */
    public Result(String n, int p) {
        this.name = n;
        this.points = p;
    }

    /**
     * Устанавливает имя игрока.
     *
     * @param s имя игрока
     */
    public void setName(String s) {
        this.name = s;
    }

    /**
     * Устанавливает количество очков.
     *
     * @param p количество очков
     */
    public void setPoints(int p) {
        this.points = p;
    }

    /**
     * Возвращает имя игрока.
     *
     * @return имя игрока
     */
    public String getName() {
        return this.name;
    }

    /**
     * Возвращает количество очков.
     *
     * @return количество очков
     */
    public int getPoints() {
        return this.points;
    }
}
