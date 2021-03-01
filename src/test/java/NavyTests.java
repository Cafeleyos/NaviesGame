import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class NavyTests {

    @Test
    @DisplayName("GIVEN different navies WHEN calling alias names THEN return number of equal names")
    public void getCountOfTheAliasNames() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);
        Navy n1 = new Navy("WOW", 1, board);
        Navy n2 = new Navy("WOW", 2, board);
        Navy n3 = new Navy("WOW", 3, board);

        Navy n4 = new Navy("No WOW", 3, board);

        navies.add(n1);
        navies.add(n2);
        navies.add(n3);
        navies.add(n4);

        int number = n1.alias();
        Assertions.assertEquals(2, number);

        number = n4.alias();
        Assertions.assertEquals(0, number);

    }

    @Test
    @DisplayName("GIVEN a navy  WHEN adding an Airncrafrcarrier with equal id THEN shouldn´t allow")
    public void addAircraftcarrierWithEqualIDShouldntAllow() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);
        Navy n1 = new Navy("The Navy", 1, board);
        navies.add(n1);

        boolean wasadded;
        wasadded = n1.addAircraftCarrier(new AircraftCarrier(1,50,1,1));
        Assertions.assertTrue(wasadded, "Allowed");
        wasadded = n1.addAircraftCarrier(new AircraftCarrier(2,30,2,2));
        Assertions.assertTrue(wasadded, "Allowed");
        wasadded = n1.addAircraftCarrier(new AircraftCarrier(1,30,3,3));
        Assertions.assertFalse(wasadded, "Should not be allowed");

    }

    @Test
    @DisplayName("GIVEN a navy  WHEN adding a ship with equal id THEN shouldn´t allow")
    public void addShipWithEqualIDShouldntAllow() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);
        Navy n1 = new Navy("The Navy", 1, board);
        navies.add(n1);

        boolean wasadded;
        wasadded = n1.addShip(new Ship(10,1,0,0));
        Assertions.assertTrue(wasadded, "Allowed");
        wasadded = n1.addShip(new Ship(30,2,1,1));
        Assertions.assertTrue(wasadded, "Allowed");
        wasadded = n1.addShip(new Ship(20,1,3,3));
        Assertions.assertFalse(wasadded, "Not allowed");

    }

    @Test
    @DisplayName("GIVEN a navy and Aircraft WHEN adding a new Aircraft with equal licence plate THEN shouldn´t allow")
    public void addAircraftWithEqualLicencePlateShoulntAllow() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);
        Navy n1 = new Navy("The Navy", 1, board);
        navies.add(n1);
        AircraftCarrier c1 =new AircraftCarrier(1,30,1,1);
        n1.addAircraftCarrier(c1);

        boolean wasadded;
        Aircraft a1 = new Aircraft("AAA",c1,2,2);
        wasadded = n1.addAircraft(a1);
        Assertions.assertTrue(wasadded, "Allowed");
        Aircraft a2 = new Aircraft("AAB",c1,12,32);
        wasadded = n1.addAircraft(a2);
        Assertions.assertTrue(wasadded, "Allowed");
        Aircraft a3 = new Aircraft("AAA",c1,45,23);
        wasadded= n1.addAircraft(a3);
        Assertions.assertFalse(wasadded, "Not Allowed");
    }

    @Test
    @DisplayName("GIVEN a navy with different Aircraftcarriers WHEN calling for the total capacity THEN return capacity of AircraftCarriers")
    public void getCountOfTotalCapacityinAircraftcarriers() {

            ArrayList<Navy> navies = new ArrayList<>();
            Board board = new Board(navies);
            Navy n1 = new Navy("LA FLOTA DEFINITIVA", 1, board);

            AircraftCarrier c1 =new AircraftCarrier(1,30,1,1);
            n1.addAircraftCarrier(c1);
            Aircraft a1 = new Aircraft("FDB", c1,87,13);
            Aircraft a2 = new Aircraft("FDZ", c1,54,-1);
            n1.addAircraft(a1);
            n1.addAircraft(a2);

            AircraftCarrier c2 =new AircraftCarrier(2,10,2,2);
            n1.addAircraftCarrier(c2);
            Aircraft a3 = new Aircraft("FSW", c2,63,12);
            Aircraft a4 = new Aircraft("KZZ", c2,-3,1);
            n1.addAircraft(a3);
            n1.addAircraft(a4);

            int result =n1.availabilityOnAricraftCarriers();
            Assertions.assertEquals(36,result);
        }

    @Test
    @DisplayName("GIVEN 2 navies with their respective aircrafts WHEN calling for enemy aircrafts in air THEN licence plates of them")
    public void getTotalEnemyAircraftsInAirLicencePlates() {

            ArrayList<Navy> navies = new ArrayList<>();
            Board board = new Board(navies);

            Navy n1 = new Navy("ALLY", 1, board);
            navies.add(n1);

            AircraftCarrier c1 =new AircraftCarrier(1,30,1,1);
            n1.addAircraftCarrier(c1);

            Aircraft a1 = new Aircraft("FDB", c1,1,-1);
            a1.setInAir(true);
            Aircraft a2 = new Aircraft("FDZ", c1,-2,-5);
            a2.setInAir(true);
            n1.addAircraft(a1);
            n1.addAircraft(a2);

            AircraftCarrier c2 =new AircraftCarrier(2,10,2,2);
            n1.addAircraftCarrier(c2);
            Aircraft a3 = new Aircraft("RTB", c2,-3,-3);
            a3.setInAir(true);
            n1.addAircraft(a3);

            Navy n2 = new Navy("ENEMY", 2, board);
            navies.add(n2);

            AircraftCarrier c3 =new AircraftCarrier(1,20,3,3);
            n2.addAircraftCarrier(c1);

            Aircraft a5 = new Aircraft("STV", c3,-4,-5);
            a5.setInAir(true);
            Aircraft a6 = new Aircraft("SYW", c3,-5,-5);
            a6.setInAir(true);
            n2.addAircraft(a5);
            n2.addAircraft(a6);

            AircraftCarrier c4 =new AircraftCarrier(2,10,4,4);
            n2.addAircraftCarrier(c2);
            Aircraft a7 = new Aircraft("SRT", c4,-6,-6);
            a7.setInAir(true);
            Aircraft a8 = new Aircraft("SFE", c4,-7,-7);
            a8.setInAir(false);
            n2.addAircraft(a7);
            n2.addAircraft(a8);

            Assertions.assertEquals("STV",n1.EnemiesInAir().get(0));
            Assertions.assertEquals("SYW",n1.EnemiesInAir().get(1));
            Assertions.assertEquals("SRT",n1.EnemiesInAir().get(2));

    }

    @Test
    @DisplayName("GIVEN navies with their machines WHEN calling an attack to a location THEN return if its good or not depending if there is an ally, enemy or nothing")
    public void itsAgoodAttackOrNot() {

            ArrayList<Navy> navies = new ArrayList<>();
            Board board = new Board(navies);

            Navy n1 = new Navy("ALLY", 1, board);
            navies.add(n1);

            n1.addShip(new Ship(10,1,1,1));
            n1.addShip(new Ship(5,2,2,2));

            AircraftCarrier c1 =new AircraftCarrier(1,30,3,3);
            n1.addAircraftCarrier(c1);
            n1.addAircraft(new Aircraft("FDB", c1,4,4));
            n1.addAircraft(new Aircraft("FYS", c1,5,5));

            AircraftCarrier c2 =new AircraftCarrier(2,10,6,6);
            n1.addAircraftCarrier(c2);
            n1.addAircraft(new Aircraft("AKU", c2,7,7));

            Navy n2 = new Navy("ENEMY", 2, board);
            navies.add(n2);

            n2.addShip(new Ship(10,1,8,8));
            n2.addShip(new Ship(5,2,9,9));

            AircraftCarrier c3 =new AircraftCarrier(1,30,10,10);
            n2.addAircraftCarrier(c3);
            n2.addAircraft(new Aircraft("FYC", c3,11,11));

            AircraftCarrier c4 =new AircraftCarrier(2,10,12,12);
            n2.addAircraftCarrier(c4);
            n2.addAircraft(new Aircraft("KSW", c4,13,13));

            boolean result =n1.itsAGoodAttack(1,1);
            Assertions.assertFalse(result, "Its not a good attack, ally ship there");

            result = n1.itsAGoodAttack(2,2);
            Assertions.assertFalse(result, "Its not a good attack, ally ship there");

            result = n1.itsAGoodAttack(3,3);
            Assertions.assertFalse(result, "Its not a good attack, ally AircraftCarrier there");

            result = n1.itsAGoodAttack(5,5);
            Assertions.assertFalse(result, "Its not a good attack, ally Aircraft there");

            result = n1.itsAGoodAttack(7,7);
            Assertions.assertFalse(result, "Its not a good attack, ally Aircraft there");

            result = n1.itsAGoodAttack(8,8);
            Assertions.assertTrue(result, "Its a good attack, enemy ship there");

            result = n1.itsAGoodAttack(10,10);
            Assertions.assertTrue(result, "Its a good attack, enemy AircraftCarrier there");

            result = n1.itsAGoodAttack(11,11);
            Assertions.assertTrue(result, "Its a good attack, enemy Aircraft there");

            result = n1.itsAGoodAttack(13,13);
            Assertions.assertTrue(result, "Its a good attack, enemy Aircraft there");

            result = n1.itsAGoodAttack(30,-10);
            Assertions.assertFalse(result, "Its not a good attack, just water");

            result = n1.itsAGoodAttack(40,10);
            Assertions.assertFalse(result, "Its not a good attack, just water");


    }

    @Test
    @DisplayName("GIVEN navies with ships THEN Move all ally ships without interference and in bounds THEN Should allow")
    public void MoveAllShipsInBoundsAndWithoutCrashingShouldAllow() {
        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        n1.addShip(new Ship(10,1,1,1));
        n1.addShip(new Ship(5,2,2,2));
        n1.addShip(new Ship(5,3,3,3));


        AircraftCarrier c1 =new AircraftCarrier(1,30,4,4);
        n1.addAircraftCarrier(c1);
        AircraftCarrier c2 =new AircraftCarrier(2,10,6,6);
        n1.addAircraftCarrier(c2);
        n1.addAircraft(new Aircraft("AKU", c2,7,7));


        Navy n2 = new Navy("ENEMY", 2, board);
        navies.add(n2);

        n2.addShip(new Ship(10,1,-8,-8));
        n2.addShip(new Ship(5,2,-9,-9));
        n2.addShip(new Ship(5,2,-10,-10));


        AircraftCarrier c3 =new AircraftCarrier(1,30,-10,-10);
        n2.addAircraftCarrier(c3);
        AircraftCarrier c4 =new AircraftCarrier(2,10,-12,-12);
        n2.addAircraftCarrier(c4);

        boolean result = n1.moveOn(10,10);
        Assertions.assertTrue(result, "should Allow");
    }

    @Test
    @DisplayName("GIVEN navies with ships THEN Move all ally ships out of bounds THEN Shouldn´t allow")
    public void MoveAllShipsOutOfBoundsShouldntAllow() {
        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        n1.addShip(new Ship(10,1,1,1));
        n1.addShip(new Ship(5,2,2,2));
        n1.addShip(new Ship(5,3,99,99));


        AircraftCarrier c1 =new AircraftCarrier(1,30,4,4);
        n1.addAircraftCarrier(c1);
        AircraftCarrier c2 =new AircraftCarrier(2,10,6,6);
        n1.addAircraftCarrier(c2);
        n1.addAircraft(new Aircraft("AKU", c2,7,7));


        boolean result = n1.moveOn(2,1);
        Assertions.assertFalse(result, "Shouldnt Allow");
    }

    @Test
    @DisplayName("GIVEN navies with ships THEN Move all ally ships crashing with an enemy one THEN Shouldn´t allow")
    public void MoveAllShipsAndIfAnyCanCrashShouldntAllow() {
        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        n1.addShip(new Ship(10,1,0,0));
        n1.addShip(new Ship(5,2,2,2));
        n1.addShip(new Ship(5,3,3,3));


        AircraftCarrier c1 =new AircraftCarrier(1,30,4,4);
        n1.addAircraftCarrier(c1);
        AircraftCarrier c2 =new AircraftCarrier(2,10,6,6);
        n1.addAircraftCarrier(c2);
        n1.addAircraft(new Aircraft("AKU", c2,7,7));


        Navy n2 = new Navy("ENEMY", 2, board);
        navies.add(n2);

        n2.addShip(new Ship(10,1,13,13));
        n2.addShip(new Ship(5,2,-60,-70));
        n2.addShip(new Ship(5,2,-99,-99));


        AircraftCarrier c3 =new AircraftCarrier(1,30,-10,-10);
        n2.addAircraftCarrier(c3);
        AircraftCarrier c4 =new AircraftCarrier(2,10,-12,12);
        n2.addAircraftCarrier(c4);

        boolean result = n1.moveOn(10,10);
        Assertions.assertFalse(result, "shouldnt Allow, crash with an enemy ship");

        result = n1.moveOn(-10,-10);
        Assertions.assertFalse(result, "shouldnt Allow, crash with an enemy AircraftCarrier");
    }

    @Test
    @DisplayName("GIVEN a navy with different machines THEN ask for the count of all them THEN get the total number of machines")
    public void AskForNumberOfMachines() {
        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        n1.addShip(new Ship(10,1,0,0));
        n1.addShip(new Ship(5,2,2,2));
        n1.addShip(new Ship(5,3,3,3));

        AircraftCarrier c1 =new AircraftCarrier(1,30,4,4);
        n1.addAircraftCarrier(c1);
        n1.addAircraft(new Aircraft("FDB", c1,87,13));
        n1.addAircraft(new Aircraft("FDZ", c1,54,-1));

        AircraftCarrier c2 =new AircraftCarrier(2,10,6,6);
        n1.addAircraftCarrier(c2);
        n1.addAircraft(new Aircraft("AKU", c2,7,7));

        int result = n1.numberOfMachines();
        Assertions.assertEquals(8, result);
    }


    @Test
    @DisplayName("GIVEN navies with their respective aircrafts WHEN equal licencePlates are in Air THEN return true")
    public void equalLicencePlatesAreInAirThenReturnTrue() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        AircraftCarrier c1 =new AircraftCarrier(1,30,1,1);
        n1.addAircraftCarrier(c1);

        Aircraft a1 = new Aircraft("FDB", c1,1,-1);
        a1.setInAir(true);
        Aircraft a2 = new Aircraft("STV", c1,-2,-5);
        a2.setInAir(true);
        n1.addAircraft(a1);
        n1.addAircraft(a2);

        Navy n2 = new Navy("ENEMY", 2, board);
        navies.add(n2);

        AircraftCarrier c3 =new AircraftCarrier(1,20,3,3);
        n2.addAircraftCarrier(c1);

        Aircraft a5 = new Aircraft("STV", c3,-4,-5);
        a5.setInAir(true);
        Aircraft a6 = new Aircraft("SYW", c3,-5,-5);
        a6.setInAir(true);
        n2.addAircraft(a5);
        n2.addAircraft(a6);

        Assertions.assertTrue(n1.problemInAir(), "There is an enemy aircraft with the same LicencePlates");
    }

    @Test
    @DisplayName("GIVEN navies with their respective aircrafts WHEN equal licencePlates arent in Air THEN return false")
    public void equalLicencePlatesArentInAirThenReturnFalse() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        AircraftCarrier c1 =new AircraftCarrier(1,30,1,1);
        n1.addAircraftCarrier(c1);

        Aircraft a1 = new Aircraft("FDB", c1,1,-1);
        a1.setInAir(true);
        Aircraft a2 = new Aircraft("STV", c1,-2,-5);
        a2.setInAir(true);
        n1.addAircraft(a1);
        n1.addAircraft(a2);

        Navy n2 = new Navy("ENEMY", 2, board);
        navies.add(n2);

        AircraftCarrier c3 =new AircraftCarrier(1,20,3,3);
        n2.addAircraftCarrier(c1);

        Aircraft a5 = new Aircraft("STV", c3,-4,-5);
        a5.setInAir(false);
        Aircraft a6 = new Aircraft("SYW", c3,-5,-5);
        a6.setInAir(true);
        n2.addAircraft(a5);
        n2.addAircraft(a6);

        Assertions.assertFalse(n1.problemInAir(), "There isnt an enemy aircraft with the same LicencePlates in air");
    }

    @Test
    @DisplayName("GIVEN a navy with their machines WHEN there is equal or more staff required for them THEN confirm")
    public void TheRequiredStaffForTheMachinesIsEnough (){
        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);

        Navy n1 = new Navy("ALLY", 1, board);
        navies.add(n1);

        n1.addShip(new Ship(10,1,0,0));
        n1.addShip(new Ship(5,2,2,2));
        n1.addShip(new Ship(5,3,3,3));

        AircraftCarrier c1 =new AircraftCarrier(1,30,4,4);
        n1.addAircraftCarrier(c1);
        n1.addAircraft(new Aircraft("FDB", c1,87,13));
        n1.addAircraft(new Aircraft("FDZ", c1,54,-1));

        AircraftCarrier c2 =new AircraftCarrier(2,10,6,6);
        n1.addAircraftCarrier(c2);
        n1.addAircraft(new Aircraft("AKU", c2,7,7));

        for (int i = 0; i < 28; i++) {
            n1.addMarine(new Marine("m"+i,i)) ;
        }
        Assertions.assertTrue(n1.enoughMarines(), "Enough marines");
        n1.addAircraft(new Aircraft("AJG", c2,8,9));

        Assertions.assertFalse(n1.enoughMarines(),"Not enough marines");
    }

}
