package arboretum.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Arboretum {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long arboretumId;
	private String arboretumName;
	private String arboretumAddress;
	private String arboretumCity;
	private String arboretumState;
	private Integer arboretumZip;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "arboretum_donor", 
			joinColumns = @JoinColumn(name = 
			"arboretum_id"), inverseJoinColumns = @JoinColumn(name = "donor_id"))
	private Set<Donor> donors = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "arboretum", cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<Tree> trees = new HashSet<>();
}
