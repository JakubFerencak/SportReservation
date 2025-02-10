package sk.kasv.ferencak.SportReservation.DAO;

import jakarta.transaction.Transactional;
import sk.kasv.ferencak.SportReservation.Entity.User;

public interface AppDAO {
    void create(User user);

}
