package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.ShaoKahn;

/**
 * Фабрика для создания персонажа ShaoKahn.
 *
 * @author Настя
 */
public class ShaoKahnFabric implements EnemyFabricInterface {

    /**
     * Создает экземпляр персонажа ShaoKahn с заданным уровнем.
     *
     * @param i уровень создаваемого персонажа (0 - обычный, другие значения -
     * усиленный)
     * @return новый экземпляр персонажа ShaoKahn
     */
    @Override
    public Player create(int i) {
        Player enemy;
        if (i == 0) {
            enemy = new ShaoKahn(3, 100, 30, 1);
        } else {
            enemy = new ShaoKahn(3, 145, 44, 1);
        }
        return enemy;
    }
}
