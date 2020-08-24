package hotels.repositories;

import hotels.models.Pricing;
import org.springframework.data.repository.CrudRepository;

public interface PricingRepository extends CrudRepository<Pricing, Long> {
}
