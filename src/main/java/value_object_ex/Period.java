package value_object_ex;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    public Period() {
    }

    public Period(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
