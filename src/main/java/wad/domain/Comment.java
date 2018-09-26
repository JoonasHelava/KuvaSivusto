
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
//Comment entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment extends AbstractPersistable<Long>{
    
    //Comment contains title,content and ID of FileObject that it´s associated with
    private String title;
    private String content;
    //For later use to see username under the comment
//    private Account commenter;
    private Long ob;
    
}
