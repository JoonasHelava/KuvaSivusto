
package wad.domain;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Joonas
 */
//Account entity NOT_IN_USE
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Account extends AbstractPersistable<Long>{
    //Contains username and password
    private String username;
    private String password;
    
}
