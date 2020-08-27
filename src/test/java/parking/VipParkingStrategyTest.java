package parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    CarDao carDao;
    @InjectMocks
    VipParkingStrategy vipParkingStrategy;

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
    public void should_return_no_parking_lot_when_park_given_full_parking_lot_and_not_vip() {
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
    public void should_return_true_when_check_over_park_given_1_car_with_name_contain_A_and_1_vip() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @InjectMocks
        CarDao carDao;@Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = createMockCar("A");
        when(carDao.isVip(any())).thenReturn(true);
        //when
        boolean isAllowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertTrue(isAllowOverPark);
    }

    @Test
    public void should_return_false_when_check_over_park_given_1_car_with_name_contain_B_and_1_vip() {

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = createMockCar("B");
        when(carDao.isVip(any())).thenReturn(true);
        //when
        boolean isAllowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(isAllowOverPark);
    }

    @Test
    public void should_return_false_when_check_over_park_given_1_car_with_name_contain_B_and_1_no_vip() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
        //given
        Car car = createMockCar("A");
        when(carDao.isVip(any())).thenReturn(false);
        //when
        boolean isAllowOverPark = vipParkingStrategy.isAllowOverPark(car);
        //then
        assertFalse(isAllowOverPark);
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
