package org.opendev.metier;

import java.util.List;

import org.opendev.dto.StepDto;
import org.opendev.entities.Locality;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

public interface LocalityMetier {

	public List<Locality> getAllLocalitiesInPath(@RequestBody StepDto stepDto, RestTemplate restTemplate);
}
