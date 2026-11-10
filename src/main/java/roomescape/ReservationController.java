package roomescape;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @GetMapping("/reservations")
    public List<Reservation> findAll() {
        return List.of(
                new Reservation(1L, "브라운", "2023-01-01", "10:00"),
                new Reservation(2L, "브라운", "2023-01-02", "11:00"),
                new Reservation(3L, "브라운", "2023-01-03", "12:00")
        );
    }
}