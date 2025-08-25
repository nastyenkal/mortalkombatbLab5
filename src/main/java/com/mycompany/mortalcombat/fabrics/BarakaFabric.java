package com.mycompany.mortalcombat.fabrics;


import com.mycompany.mortalcombat.characters.Player;
import com.mycompany.mortalcombat.characters.Baraka;

/**
 *
 * @author lihac
 */
public class BarakaFabric implements EnemyFabricInterface {
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}
