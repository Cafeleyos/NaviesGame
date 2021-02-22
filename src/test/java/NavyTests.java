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
        Navy n1 = new Navy("LA FLOTA DEFINITIVA", 1, board);
        boolean wasadded;
        wasadded = n1.addAircraftCarrier(new AircraftCarrier(1,50));
        Assertions.assertTrue(wasadded, "Allowed");
        wasadded = n1.addAircraftCarrier(new AircraftCarrier(2,30));
        Assertions.assertTrue(wasadded, "Allowed");
        wasadded = n1.addAircraftCarrier(new AircraftCarrier(1,30));
        Assertions.assertFalse(wasadded, "Should not be allowed");

    }

    @Test
    @DisplayName("GIVEN a navy and Aircraft WHEN adding a new Aircraft with equal licence plate THEN shouldn´t allow")
    public void addAircraftWithEqualLicencePlateShoulntAllow() {

        ArrayList<Navy> navies = new ArrayList<>();
        Board board = new Board(navies);
        Navy n1 = new Navy("LA FLOTA DEFINITIVA", 1, board);
        AircraftCarrier c1 =new AircraftCarrier(1,30);
        n1.addAircraftCarrier(c1);

        boolean wasadded;
        Aircraft a1 = new Aircraft("AAA",c1);
        wasadded = n1.addAircraft(a1);
        Assertions.assertTrue(wasadded, "Allowed");
        Aircraft a2 = new Aircraft("AAB",c1);
        wasadded = n1.addAircraft(a2);
        Assertions.assertTrue(wasadded, "Allowed");
        Aircraft a3 = new Aircraft("AAA",c1);
        wasadded= n1.addAircraft(a3);
        Assertions.assertFalse(wasadded, "Not Allowed");




    }

        @Test
        @DisplayName("GIVEN a navy with different Aircraftcarriers WHEN calling for the total capacity THEN return capacity of AircraftCarriers")
        public void getCountOfTotalCapacityinAircraftcarriers() {

            ArrayList<Navy> navies = new ArrayList<>();
            Board board = new Board(navies);
            Navy n1 = new Navy("LA FLOTA DEFINITIVA", 1, board);

            AircraftCarrier c1 =new AircraftCarrier(1,30);
            n1.addAircraftCarrier(c1);
            Aircraft a1 = new Aircraft("FDB", c1);
            Aircraft a2 = new Aircraft("FDZ", c1);
            c1.addAircraftToCarrier(a1);
            c1.addAircraftToCarrier(a2);

            AircraftCarrier c2 =new AircraftCarrier(2,10);
            n1.addAircraftCarrier(c2);
            Aircraft a3 = new Aircraft("FSW", c2);
            Aircraft a4 = new Aircraft("KZZ", c2);
            c2.addAircraftToCarrier(a3);
            c2.addAircraftToCarrier(a4);

            int result =n1.availabilityOnAricraftCarriers();
            Assertions.assertEquals(36,result);
        }



    }
