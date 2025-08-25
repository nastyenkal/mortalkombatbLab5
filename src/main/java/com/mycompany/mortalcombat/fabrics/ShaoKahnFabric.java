package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.ShaoKahn;

/**
 *
 * @author lihac
 */
public class ShaoKahnFabric implements EnemyFabricInterface {
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
