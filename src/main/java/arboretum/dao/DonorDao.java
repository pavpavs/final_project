package arboretum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import arboretum.entity.Donor;

public interface DonorDao extends JpaRepository<Donor, Long> {

}
