package reservationmanager.application.service;

import reservationmanager.domain.reserve.Reserve;
import reservationmanager.domain.reserve.ReserveRepository;

public class Persist {
    private ReserveRepository reserveRepository;

    public int create(Reserve reserve){
        return reserveRepository.create(reserve);
    }
}
