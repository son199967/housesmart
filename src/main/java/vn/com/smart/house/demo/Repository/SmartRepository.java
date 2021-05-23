package vn.com.smart.house.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.smart.house.demo.Model.WeatherInfo;
@Repository
public interface SmartRepository extends JpaRepository< WeatherInfo,Long> {
    WeatherInfo findTopByGardenIdOrderByTimeDesc(Long id);
}
