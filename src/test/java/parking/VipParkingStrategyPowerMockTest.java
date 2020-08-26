package parking;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Test;

import java.util.Calendar;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {ParkingLot.class})
public class VipParkingStrategyPowerMockTest {

    @Test
    public void should_return_40_when_calculate_hourly_price_given_basic_hourly_price_20() {
        //given
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(20);
        //when
        int hourlyPrice = vipParkingStrategy.calculateHourlyPrice();
        //then
        assertEquals(40, hourlyPrice);
    }

    @Test
    public void should_return_50_when_calculate_hourly_price_given_not_sunday() {
        //given
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class);
        when(ParkingLot.getBasicHourlyPrice()).thenReturn(25);
        //when
        int hourlyPrice = vipParkingStrategy.calculateHourlyPrice();
        //then
        assertEquals(50, hourlyPrice);
    }
}
