package roomescape;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReservationController {

    private final List<Reservation> reservations = new ArrayList<>();

    private final AtomicLong index = new AtomicLong();

    @GetMapping("/reservations")
    public List<Reservation> findAll() {
        return reservations;
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> save(
            @RequestBody ReservationRequest request
    ) {

        Long id = index.incrementAndGet();

        Reservation reservation = new Reservation(
                id,
                request.name(),
                request.date(),
                request.time()
        );

        reservations.add(reservation);

        return ResponseEntity
                .created(URI.create("/reservations/" + id))
                .body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        reservations.removeIf(
                reservation -> reservation.id().equals(id)
        );

        return ResponseEntity.noContent().build();
    }
}