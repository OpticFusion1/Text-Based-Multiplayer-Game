package tests;

import org.junit.runners.Suite;

import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    DirectionTest.class,
    ItemTest.class,
    CharacterTest.class,
    RoomManagerTest.class,
    RoomNodeTest.class,
    SkillSetTest.class,
    OutgoingAttackTest.class,
    SkillSetTest.class,
    StraightAttackTest.class
})

public class ModelTests {
    
}