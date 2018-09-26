
package wad.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Joonas
 */
//Comment entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment implements Serializable {
    
    //Using Serializable instead of AbstractPersistable, the IDs where mixing up and made browsing impossible
    @Id
    private String identifier;
    //Comment contains title,content and ID of FileObject that it´s associated with
    private String title;
    private String content;
    //For later use to see username under the comment
//    private Account commenter;
    private Long ob;
    public Comment(String title,String content,Long ob){
        this.identifier = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.ob = ob;
    }
}
