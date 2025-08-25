package com.mycompany.mortalcombat.characters;

import javax.swing.ImageIcon;

/**
 *
 * @author Мария
 */
public class LiuKang extends Player {
    
    /**
     * Конструктор персонажа LiuKang
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

    @Override
    public String getName() {
        return "Liu Kang";
    }
}
