package vn.com.smart.house.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.smart.house.demo.Model.Control;
@Repository
public interface ControlRepository extends JpaRepository<Control,Long> {
//    @Query("select * from Control c inner join Garden  g on c.garden.id = g.id where c.id= ?1 and g.id =?2")
//    Control getControlbyId(Long idControl, Long)

}
