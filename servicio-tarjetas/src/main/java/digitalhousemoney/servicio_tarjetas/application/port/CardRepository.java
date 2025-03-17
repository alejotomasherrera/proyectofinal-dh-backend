package digitalhousemoney.servicio_tarjetas.application.port;

import digitalhousemoney.servicio_tarjetas.domain.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findAllByUserId(Long userId);

    Optional<Card> findByIdAndUserId(Long cardId, Long userId);

    Optional<Card> findByNumber(String number);

    Optional<Card> findByNumberEndingWith(String cardNumberSuffix);


    boolean existsByIdAndUserId(Long cardId, Long userId);
}
