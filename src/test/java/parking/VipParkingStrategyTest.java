package parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VipParkingStrategyTest {

    @Test
    public void should_return_receipt_and_execute_create_receipt_when_park_given_a_full_parking_lot_and_a_vip() {
        //given
        String carName = "bmw";
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("OOCL");
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(any());
        ///when
        Receipt receipt = vipParkingStrategy
            .park(Collections.singletonList(parkingLot), createMockCar(carName));
        //then
        verify(vipParkingStrategy).createReceipt(any(ParkingLot.class), any(Car.class));
        assertEquals("OOCL", receipt.getParkingLotName());
    }

    @Test
    public void should_return_no_parking_lot_when_park_given_full_parking_lot() {
        //given
        String carName = "bmw";
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = Mockito.mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("OOCL");
        when(parkingLot.isFull()).thenReturn(true);
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(any());
        ///when
        Receipt receipt = vipParkingStrategy
            .park(Collections.singletonList(parkingLot), createMockCar(carName));
        //then
        verify(vipParkingStrategy).createNoSpaceReceipt(any(Car.class));
        assertEquals("No Parking Lot", receipt.getParkingLotName());
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
