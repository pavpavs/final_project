package arboretum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import arboretum.entity.Tree;

public interface TreeDao extends JpaRepository<Tree, Long> {
	List<Tree> findAllByArboretumArboretumId(Long arboretumId);
}
