package arboretum.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import arboretum.controller.model.ArboretumData;
import arboretum.controller.model.ArboretumData.ArboretumDonor;
import arboretum.controller.model.ArboretumData.ArboretumTree;
import arboretum.service.ArboretumService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/arboretum")
@Slf4j
public class ArboretumController {
	
	@Autowired
	private ArboretumService arboretumService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArboretumData insertArboretum(@RequestBody ArboretumData arboretumData) {
		log.info("Creating an arboretum {}", arboretumData);
		return arboretumService.saveArboretum(arboretumData);
	}
	
	@PutMapping("/{arboretumId}")
	public ArboretumData modifyArboretum(@PathVariable Long arboretumId, @RequestBody ArboretumData arboretumData) {
		log.info("Modifying information for arboretumID = {} to {}", arboretumId, arboretumData);
		arboretumData.setArboretumId(arboretumId);
		return arboretumService.saveArboretum(arboretumData);
	}
	
	@GetMapping
	public List<ArboretumData> listArboretum(){
		log.info("Listing all arboretum");
		return arboretumService.retrieveAllArboretums();
	}
	
	@GetMapping("/{arboretumId}")
	public ArboretumData listArboretum(@PathVariable Long arboretumId){
		log.info("Showing details for aboretumId={}", arboretumId);
		return arboretumService.retrieveArboretumById(arboretumId);
	}
	
	@DeleteMapping("/{arboretumId}")
	public Map<String,String> deleteArboretumById(@PathVariable Long arboretumId){
		log.info("Deleting arboretum with ID={}", arboretumId);
		arboretumService.deleteArboretumById(arboretumId);
		Map<String, String> result = new HashMap<>();
		result.put("message", "Successfully deleted arboretum with ID="+arboretumId);
		return result;
	}

	@GetMapping("/{arboretumId}/tree")
	public List<ArboretumTree> listTrees(@PathVariable Long arboretumId){
		log.info("Listing all trees with arboretumId={}", arboretumId);
		return arboretumService.retrieveAllTreesByArboretumId(arboretumId);
	}
	
	@PostMapping("/{arboretumId}/tree")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArboretumTree insertTree(@PathVariable Long arboretumId, @RequestBody ArboretumTree arboretumTree) {
		log.info("Creating tree {} with arboretumID={}", arboretumTree, arboretumId);
		return arboretumService.saveTree(arboretumId, arboretumTree);
	}
	
	@PostMapping("/{arboretumId}/donor")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArboretumDonor insertDonor(@PathVariable Long arboretumId, @RequestBody ArboretumDonor arboretumDonor) {
		log.info("Creating donor {} with arboretumID={}", arboretumDonor, arboretumId);
		return arboretumService.saveDonor(arboretumId, arboretumDonor);
	}
	
}
