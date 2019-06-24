package reservationmanager.application.service;

import reservationmanager.domain.reserve.Reserve;

public class Factory {
    public static Reserve reserve(){
        return new Reserve();
    }
}
