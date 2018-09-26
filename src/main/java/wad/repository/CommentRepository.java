
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Comment;
import wad.domain.FileObject;

/**
 *
 * @author Joonas
 */
//JPA Repository for Comment class
public interface CommentRepository extends JpaRepository<Comment,Long>{
    //Find comments that are connnected to FileObject
    List<Comment> findByOb(Long id);
}
