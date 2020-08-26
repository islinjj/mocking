package parking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sales.EcmService;
import sales.SalesApp;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {InOrderParkingStrategy.class})
public class InOrderParkingStrategyTest {

    @Test
    public void should_return_receipt_when_park_given_1_car_and_1_parking_lot() {
        //given
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        when(parkingLot.getName()).thenReturn("OOCL");
        when(car.getName()).thenReturn("bmw");
        when(parkingLot.isFull()).thenReturn(false);
        parkingLots.add(parkingLot);
        //when
        Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);
        //then
        assertEquals("OOCL", receipt.getParkingLotName());
        assertEquals("bmw", receipt.getCarName());
    }

    @Test
    public void should_return_no_parking_lot_when_park_given_parking_lots_all_is_full() {
        //given
        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = mock(Car.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        when(parkingLot.getName()).thenReturn("OOCL");
        when(car.getName()).thenReturn("bmw");
        when(parkingLot.isFull()).thenReturn(true);
        parkingLots.add(parkingLot);
        //when
        Receipt receipt = inOrderParkingStrategy.park(parkingLots, car);
        //then
        assertEquals("No Parking Lot", receipt.getParkingLotName());
    }

    @Test
    public void should_execute_create_no_space_receipt_time_1_when_park_given_no_available_parking_lot_and_1_car() {
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("bmw");
        List<ParkingLot> parkingLots = new ArrayList<>();
        //when
        inOrderParkingStrategy.park(parkingLots, car);
        //then
        verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void should_exe_createReceipt_time_1_when_park_given_available_parking_lot_and_1_car() {
        //given
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Car car = mock(Car.class);
        when(car.getName()).thenReturn("bmw");
        ParkingLot parkingLot = mock(ParkingLot.class);
        List<ParkingLot> parkingLots = new ArrayList<>();
        when(parkingLot.getName()).thenReturn("OOCL");
        when(parkingLot.isFull()).thenReturn(false);
        parkingLots.add(parkingLot);
        //when
        inOrderParkingStrategy.park(parkingLots, car);
        //then
        verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

    }


}
