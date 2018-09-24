/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.repository;

/**
 *
 * @author Joonas
 */
import wad.domain.FileObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Joonas
 */
public interface FileObjectRepository extends JpaRepository<FileObject,Long>{
}
