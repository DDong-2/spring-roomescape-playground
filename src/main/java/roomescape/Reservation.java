package roomescape;

public record Reservation(
        Long id,
        String name,
        String date,
        String time
) {
    public Reservation() {
        this(null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}