
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Account;

/**
 *
 * @author Joonas
 */
//JPA Repository for Account class NOT_IN_USE
public interface AccountRepository extends JpaRepository<Account,Long>{
    //Find account with username
    Account findByUsername(String username);
    
}
