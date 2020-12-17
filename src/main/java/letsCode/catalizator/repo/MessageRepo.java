package letsCode.catalizator.repo;

import letsCode.catalizator.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


public interface MessageRepo extends ReactiveCrudRepository<Message, Long> {
}
