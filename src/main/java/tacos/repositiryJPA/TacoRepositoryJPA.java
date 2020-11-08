package tacos.repositiryJPA;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

public interface TacoRepositoryJPA extends CrudRepository<Taco, Long> {
}
