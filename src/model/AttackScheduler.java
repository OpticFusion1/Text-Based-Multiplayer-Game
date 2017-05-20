package model;

import java.util.TimerTask;

public class AttackScheduler extends TimerTask {

    private Entities entities;

    public AttackScheduler(Entities e) {
        if (e == null) {
            throw new NullPointerException();
        }
        
        this.entities = e;
    }
    
    @Override
    public void run() {
        long time = System.currentTimeMillis();
        System.out.println(time);
        for (Player p : entities.getPlayers()) {
            if (p.shouldAttack(time)) {
                p.consumeOutgoingAttack();
            }
        }
        
        for (NonPlayerCharacter c : entities.getNPCs()) {
            if (c.shouldAttack(time)) {
                c.consumeOutgoingAttack();
            }
        }
    }

}
