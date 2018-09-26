
package wad.repository;

/**
 *
 * @author Joonas
 */
import wad.domain.FileObject;
import org.springframework.data.jpa.repository.JpaRepository;

//JPA repository for FileObject
public interface FileObjectRepository extends JpaRepository<FileObject,Long>{
}
