/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.domain;

/**
 *
 * @author Joonas
 */
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Data
@NoArgsConstructor
public class FileObject extends AbstractPersistable<Long> {

    @Lob
    private byte[] content;
    
    private int upvote;
    private int downvote;
    
    @OneToMany
    private List<Comment> comments;
    
    public void upVo(){
        this.upvote++;
    }
    public void downVo(){
        this.downvote++;
    }
}
