
package wad.domain;

/**
 *
 * @author Joonas
 */
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

//FileObject entity
@Entity
@Data
@NoArgsConstructor
public class FileObject extends AbstractPersistable<Long> {
    
    //File data
    @Lob
    private byte[] content;
    //Class stores info about votes and comments.
    private int upvote;
    private int downvote;
    private int score;
    @OneToMany
    private List<Comment> comments;
    
    //Methods for up and down voting
    public void upVo(){
        this.upvote++;
    }
    public void downVo(){
        this.downvote++;
    }
    //Count score for valueting the popularity of FileObject
    public void score(){
        int com = this.comments.size();
        score = upvote + downvote + (com * 2);
    }
}
