
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Comment;

/**
 *
 * @author Joonas
 */
//JPA Repository for Comment class
public interface CommentRepository extends JpaRepository<Comment,String>{
    //Find comments that are connnected to FileObject
    List<Comment> findByOb(Long id);
}
