package digitalMonyHouse.cuenta_service.application.port;

import digitalMonyHouse.cuenta_service.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId(Long userId);

    Optional<Account> findByCvu(String cvu);

    Optional<Account> findByAlias(String alias);
}
