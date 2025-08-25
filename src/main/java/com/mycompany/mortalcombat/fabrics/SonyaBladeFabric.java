package com.mycompany.mortalcombat.fabrics;

import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.SonyaBlade;

/**
 *
 * @author lihac
 */
public class SonyaBladeFabric implements EnemyFabricInterface {
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }
}
