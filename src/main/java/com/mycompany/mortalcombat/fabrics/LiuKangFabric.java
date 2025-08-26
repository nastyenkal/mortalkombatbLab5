package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.LiuKang;
import com.mycompany.mortalcombat.characters.Player;

/**
 * Фабрика для создания персонажа LiuKang.
 *
 * @author Настя
 */
public class LiuKangFabric implements EnemyFabricInterface {

    /**
     * Создает экземпляр персонажа LiuKang с заданным уровнем.
     *
     * @param i уровень создаваемого персонажа
     * @return новый экземпляр персонажа LiuKang
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new LiuKang(1, 70, 20, 1);
        return enemy;
    }
}
