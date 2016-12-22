package org.opendev.services;

import java.util.List;

import org.opendev.dao.LocalityRepository;
import org.opendev.dto.StepDto;
import org.opendev.entities.Locality;
import org.opendev.metier.LocalityMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("/localities")
public class LocalityRestService {

	@Autowired
	LocalityRepository localityRepository;

	@Autowired
	LocalityMetier localityMetier;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RequestMapping(value = "/pointsInPath", method = RequestMethod.POST)
	public List<Locality> getAllLocalitiesInPath(@RequestBody StepDto stepDto, RestTemplate restTemplate) {
		return localityMetier.getAllLocalitiesInPath(stepDto, restTemplate);
	}

	/*
	 * 
	 * CRUD API FOR LOCALITIES
	 *
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Locality addLocality(@RequestBody Locality locality) {
		return localityRepository.saveAndFlush(locality);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Locality> getAllLocalities() {
		return localityRepository.findAll();
	}

	@RequestMapping(value = "{idLocality}", method = RequestMethod.GET)
	public Locality getLocality(@PathVariable long idLocality) {
		return localityRepository.findOne(idLocality);
	}

}
