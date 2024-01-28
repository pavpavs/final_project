package arboretum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import arboretum.entity.Arboretum;

public interface ArboretumDao extends JpaRepository<Arboretum, Long> {

}
