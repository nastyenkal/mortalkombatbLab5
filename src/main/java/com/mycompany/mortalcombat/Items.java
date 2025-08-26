package com.mycompany.mortalcombat;

/**
 * Класс для представления предметов в игре.
 *
 */
public class Items {

    private String name;
    private int count;

    /**
     * Создает новый предмет.
     *
     * @param n название предмета
     * @param c количество предметов
     */
    public Items(String n, int c) {
        this.name = n;
        this.count = c;
    }

    /**
     * Устанавливает название предмета.
     *
     * @param s название предмета
     */
    public void setName(String s) {
        this.name = s;
    }

    /**
     * Устанавливает количество предметов.
     *
     * @param c количество предметов (может быть отрицательным для уменьшения)
     */
    public void setCount(int c) {
        this.count += c;
    }

    /**
     * Возвращает название предмета.
     *
     * @return название предмета
     */
    public String getName() {
        return this.name;
    }

    /**
     * Возвращает количество предметов.
     *
     * @return количество предметов
     */
    public int getCount() {
        return this.count;
    }
}
