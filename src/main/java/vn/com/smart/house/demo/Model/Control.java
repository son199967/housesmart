package vn.com.smart.house.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

public class Control {
   @Id
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private StatusLed statusLed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garden_id")
    private Garden garden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public StatusLed getStatusLed() {
       return statusLed;
   }

    public void setStatusLed(StatusLed statusLed) {
        this.statusLed = statusLed;
    }
    @JsonBackReference
    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }
}
