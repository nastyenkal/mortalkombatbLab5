package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 * Класс персонажа Sonya Blade.
 *
 */
public class SonyaBlade extends Player {

    /**
     * Конструктор персонажа SonyaBlade.
     *
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */
    public SonyaBlade(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        java.net.URL picURL = getClass().getResource("/pictures/SonyaBlade.png");
        picture = new ImageIcon(picURL);
    }

    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    @Override
    public String getName() {
        return "Sonya Blade";
    }
}
