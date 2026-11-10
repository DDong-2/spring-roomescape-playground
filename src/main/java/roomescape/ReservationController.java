package roomescape;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ReservationController {

    private final ReservationDao reservationDao;

    public ReservationController(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    // 6단계: 예약 조회
    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        return reservationDao.findAll();
    }

    // 7단계: 예약 추가
    @PostMapping("/reservations")
    public ResponseEntity<Reservation> save(@RequestBody ReservationRequest request) {
        if (request.name() == null || request.name().isBlank() ||
                request.date() == null || request.date().isBlank() ||
                request.time() == null || request.time().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Reservation reservation = new Reservation(null, request.name(), request.date(), request.time());
        Reservation saved = reservationDao.save(reservation);

        return ResponseEntity
                .created(URI.create("/reservations/" + saved.getId()))
                .body(saved);
    }

    // 7단계: 예약 삭제
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        int deletedRows = reservationDao.delete(id);

        if (deletedRows == 0) {
            throw new NotFoundReservationException();
        }

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundReservationException.class)
    public ResponseEntity<Void> handleNotFound(NotFoundReservationException e) {
        return ResponseEntity.notFound().build();
    }
}