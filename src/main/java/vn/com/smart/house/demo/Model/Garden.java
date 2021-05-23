package vn.com.smart.house.demo.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "garden",cascade = CascadeType.ALL)
    private List<WeatherInfo> weatherInfos;
    @OneToMany(mappedBy = "garden",cascade = CascadeType.ALL)
    private List<Control> control;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @JsonManagedReference
    public List<WeatherInfo> getWeatherInfos() {
        return weatherInfos;
    }

    public void setWeatherInfos(List<WeatherInfo> weatherInfos) {
        this.weatherInfos = weatherInfos;
    }

    public List<Control> getControl() {
        return control;
    }
    @JsonManagedReference
    public void setControl(List<Control> control) {
        this.control = control;
    }
}
