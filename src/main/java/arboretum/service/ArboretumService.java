package arboretum.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import arboretum.controller.model.ArboretumData;
import arboretum.controller.model.ArboretumData.ArboretumDonor;
import arboretum.controller.model.ArboretumData.ArboretumTree;
import arboretum.dao.ArboretumDao;
import arboretum.dao.DonorDao;
import arboretum.dao.TreeDao;
import arboretum.entity.Arboretum;
import arboretum.entity.Donor;
import arboretum.entity.Tree;

@Service
public class ArboretumService {
	
	@Autowired
	private ArboretumDao arboretumDao;
	
	@Autowired
	private TreeDao treeDao;
	
	@Autowired
	private DonorDao donorDao;
	
	@Transactional(readOnly = false)
	public ArboretumData saveArboretum(ArboretumData arboretumData) {
		Long arboretumId = arboretumData.getArboretumId();
		Arboretum arboretum = findOrCreateArboretum(arboretumId);
		copyArboretumFields(arboretum, arboretumData);
		return new ArboretumData(arboretumDao.save(arboretum));
	}

	private void copyArboretumFields(Arboretum arboretum, ArboretumData arboretumData) {
		arboretum.setArboretumName(arboretumData.getArboretumName());
		arboretum.setArboretumAddress(arboretumData.getArboretumAddress());
		arboretum.setArboretumCity(arboretumData.getArboretumCity());
		arboretum.setArboretumState(arboretumData.getArboretumState());
		arboretum.setArboretumZip(arboretumData.getArboretumZip());
	}

	private Arboretum findOrCreateArboretum(Long arboretumId) {
		Arboretum arboretum;
		
		if (Objects.isNull(arboretumId)) {
			arboretum = new Arboretum();
		}else {
			arboretum = findArboretumById(arboretumId);
		}
		return arboretum;
	}
	
	private Arboretum findArboretumById(Long arboretumId) {
		return arboretumDao.findById(arboretumId).orElseThrow(
				()-> new NoSuchElementException("Arboretum with ID="+arboretumId+" was not found"));
	}

	@Transactional
	public List<ArboretumData> retrieveAllArboretums() {
		List<ArboretumData> result = new LinkedList<>();
		for (Arboretum arboretum : arboretumDao.findAll()) {
			ArboretumData rslt = new ArboretumData(arboretum);
			
			rslt.getTrees().clear();
			rslt.getDonors().clear();
			
			result.add(rslt);
		}
		return result;
	}

	@Transactional
	public ArboretumData retrieveArboretumById(Long arboretumId) {
		return new ArboretumData(findArboretumById(arboretumId));
	}

	public void deleteArboretumById(Long arboretumId) {
		arboretumDao.delete(findArboretumById(arboretumId));		
	}

	public List<ArboretumTree> retrieveAllTreesByArboretumId(Long arboretumId) {
		List<ArboretumTree> result = new LinkedList<>();
		for (Tree tree : treeDao.findAllByArboretumArboretumId(arboretumId)) {
			result.add(new ArboretumTree(tree));
		}
		return result;
		
	}

	public ArboretumTree saveTree(Long arboretumId, ArboretumTree arboretumTree) {
		Arboretum arboretum = findArboretumById(arboretumId);
		Tree tree = new Tree();
		copyTreeFields(tree, arboretumTree);
		tree.setArboretum(arboretum);
		arboretum.getTrees().add(tree);
		return new ArboretumTree(treeDao.save(tree));

	}

	private void copyTreeFields(Tree tree, ArboretumTree arboretumTree) {
		tree.setTreeName(arboretumTree.getTreeName());
		tree.setTreeNameLatin(arboretumTree.getTreeNameLatin());
		tree.setYearPlanted(arboretumTree.getYearPlanted());
		
	}

	public ArboretumDonor saveDonor(Long arboretumId, ArboretumDonor arboretumDonor) {
		Arboretum arboretum = findArboretumById(arboretumId);
		Donor donor = new Donor();
		copyDonorFields(donor, arboretumDonor);
		donor.getArboretums().add(arboretum);
		arboretum.getDonors().add(donor);
		return new ArboretumDonor(donorDao.save(donor));
	}

	private void copyDonorFields(Donor donor, ArboretumDonor arboretumDonor) {
		donor.setDonorFirstName(arboretumDonor.getDonorFirstName());
		donor.setDonorLastName(arboretumDonor.getDonorLastName());
		donor.setYearEnrolled(arboretumDonor.getYearEnrolled());
		
	}



	
	
}
