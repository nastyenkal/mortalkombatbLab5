package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 * Класс персонажа Liu Kang.
 * 
 */
public class LiuKang extends Player {
    
    /**
     * Конструктор персонажа LiuKang.
     *
     * @param level уровень персонажа
     * @param health здоровье
     * @param damage урон
     * @param attack атака
     */

    public LiuKang(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        java.net.URL picURL = getClass().getResource("/pictures/Liu_Kang.png");
        picture = new ImageIcon(picURL);
    }
    
    /**
     * Возвращает имя персонажа.
     *
     * @return имя персонажа
     */
    @Override
    public String getName() {
        return "Liu Kang";
    }
}
