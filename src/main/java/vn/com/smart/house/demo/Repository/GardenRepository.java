package vn.com.smart.house.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.smart.house.demo.Model.Garden;
@Repository
public interface GardenRepository extends JpaRepository<Garden,Long> {

}
