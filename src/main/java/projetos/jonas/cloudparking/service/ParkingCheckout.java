package projetos.jonas.cloudparking.service;

import projetos.jonas.cloudparking.model.Parking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckout {

    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;
    public static final double ONE_HOUR_VALUE = 5.00;
    public static final double ADTIONAL_PER_HOUR = 2.00;
    public static final double DAY_VALUE = 20.00;

    public static Double getBil(Parking parking){
        return getBil(parking.getEntryDate(),parking.getExitDate());

    }
    public static Double getBil(LocalDateTime entryDate, LocalDateTime exitDate){
        Long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        double bill = 0.00;
        if(minutes < ONE_HOUR)
            bill = ONE_HOUR_VALUE;
        else if(minutes < TWENTY_FOUR_HOUR){
            if(minutes<= TWENTY_FOUR_HOUR){
                int hours =(int) (minutes /ONE_HOUR);
                int remainder = (int) (minutes % ONE_HOUR);
                if(remainder > 0 )
                    hours+= 1 ;

                if( hours == 24)
                    bill= DAY_VALUE;
                else
                    bill= ADTIONAL_PER_HOUR * hours + ONE_HOUR_VALUE;
            }
        }
        else {
            bill= DAY_VALUE;
        }
        return bill;
    }
}
