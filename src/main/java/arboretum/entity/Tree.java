package arboretum.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Tree {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long treeId;
	private String treeName;
	private String treeNameLatin;
	private Integer yearPlanted;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "arboretum_id")
	private Arboretum arboretum;
}
