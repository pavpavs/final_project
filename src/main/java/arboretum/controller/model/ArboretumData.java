package arboretum.controller.model;

import java.util.HashSet;
import java.util.Set;

import arboretum.entity.Arboretum;
import arboretum.entity.Donor;
import arboretum.entity.Tree;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArboretumData {

	private Long arboretumId;
	private String arboretumName;
	private String arboretumAddress;
	private String arboretumCity;
	private String arboretumState;
	private Integer arboretumZip;
	
	private Set<ArboretumTree> trees = new HashSet<>();
	private Set<ArboretumDonor> donors = new HashSet<>();
		
	public ArboretumData(Arboretum arboretum) {
		arboretumId = arboretum.getArboretumId();
		arboretumName = arboretum.getArboretumName();
		arboretumAddress = arboretum.getArboretumAddress();
		arboretumCity = arboretum.getArboretumCity();
		arboretumState = arboretum.getArboretumState();
		arboretumZip = arboretum.getArboretumZip();
		
		for (Tree tree : arboretum.getTrees()) {
			trees.add(new ArboretumTree(tree));
		}
		for (Donor donor : arboretum.getDonors()) {
			donors.add(new ArboretumDonor(donor));
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class ArboretumTree{
		private Long treeId;
		private String treeName;
		private String treeNameLatin;
		private Integer yearPlanted;
		
		public ArboretumTree(Tree tree) {
			treeId =  tree.getTreeId();
			treeName = tree.getTreeName();
			treeNameLatin = tree.getTreeNameLatin();
			yearPlanted = tree.getYearPlanted();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class ArboretumDonor{
		private Long donorId;
		private String donorFirstName;
		private String donorLastName;
		private Integer yearEnrolled;
		
		public ArboretumDonor(Donor donor) {
			donorId = donor.getDonorId();
			donorFirstName = donor.getDonorFirstName();
			donorLastName = donor.getDonorLastName();
			yearEnrolled = donor.getYearEnrolled();
		}
	}
}
