package model;

/**
 * A class to describe a "straight forward attack". That applys xp and damage in a simple manner.
 *
 * @author Zachary Chandler
 */
public class StraightAttack implements Attack {

    /** The type of damage for this skill. */
    private DamageType damageType;
    
    /** The skill used for damage. */
    private Skill damageSkill;
    
    /** The skill paying for the attack. */
    private Skill castPool;
    
    /** The amount of damage for this attack. */
    private int damageAmount;
    
    /** The cost to use the skill. */
    private int castCost;

    /** The amount to heal if applicable. */
    private int healAmount;

    /**
     * Creates a new straight forward attack with the given values.
     * 
     * @param damageType the type of damage this attack causes.
     * @param damageSkill the skill used to damage others.
     * @param castPool the skill to have the cost taken from.
     * @param damageAmount the amount of damage this skill causes.
     * @param castCost the cost to use the skill.
     * @throws NullPointerException if damageType, damageSkill, or castPool are null.
     * @throws IllegalArgumentException if castPool is not consumable.
     */
    public StraightAttack(DamageType damageType, Skill damageSkill, Skill castPool, int damageAmount, int castCost) {
        if (damageType == null || damageSkill == null || castPool == null) {
            throw new NullPointerException();
        }
        
        if (!castPool.consumable || castCost < 0) {
            throw new IllegalArgumentException();
        }

        this.damageType = damageType;
        this.damageSkill = damageSkill;
        this.castPool = castPool;

        this.damageAmount = damageAmount;
        this.healAmount = -damageAmount;
        this.castCost = castCost;
    }

    /**
     * Causes harm to every other in others if damageAmount is positive or zero. Otherwise it heals every other in 
     * others. If it is harming, the harm will be that described by getDamageType() of the amount given by 
     * getDamageAmount() caused by the caster.
     * 
     * Iff the attack does not encounter any exceptions, the damageSkill will get the amount of xp healed/harmed each
     * other added together. And the damageCost consumed will be given as xp to the castPool skill.
     * 
     * @param caster the one causing the attack.
     * @param others the ones affected by this attack.
     * @throws NullPointerException if any other in others is null.
     * @throws IllegalArgumentException if caster does not have enough in their skill pool to cast the attack. 
     */
    @Override
    public void attack(Character caster, Character... others) {
        for (Character other : others) {
            if (other == null) {
                throw new NullPointerException();
            }
        }
        
        caster.skills.consume(castPool, castCost);
        
        int damageDealt = 0;
        
        if (damageAmount < 0) {
            for (Character other : others) {
                damageDealt += other.heal(damageType, caster, healAmount);
            }
        } else {
            for (Character other : others) {
                damageDealt += other.harm(damageType, caster, damageAmount);
            }            
        }
        
        caster.skills.addXP(castPool, castCost);
        caster.skills.addXP(damageSkill, damageDealt);
    }

    /**
     * @return the damage type of this attack.
     */
    public DamageType getDamageType() {
        return damageType;
    }

    /**
     * @return the skill associated with the damage of this attack.
     */
    public Skill getDamageSkill() {
        return damageSkill;
    }
    
    /**
     * @return the skill paying for the damage.
     */
    public Skill getDamagePool() {
        return castPool;
    }

    /**
     * @return the cost of the damage.
     */
    public int getDamageCost() {
        return castCost;
    }

    /**
     * @return the amount of damage this skill causes.
     */
    public int getDamageAmount() {
        return damageAmount;
    }

}
