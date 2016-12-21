package org.opendev.metier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opendev.dao.LocalityRepository;
import org.opendev.dto.ResponseDto;
import org.opendev.dto.StepDto;
import org.opendev.entities.Locality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocalityMetierImpl implements LocalityMetier {

	@Autowired
	LocalityRepository localityRepository;

	@Override
	public List<Locality> getAllLocalitiesInPath(StepDto stepDto, RestTemplate restTemplate) {

		// LOAD RESULT FROM GOOGLE DIRECTION API !
		ResponseDto reponse = restTemplate.getForObject("https://maps.googleapis.com/maps/api/directions/json?origin="
				+ stepDto.getStart_location().getLat() + "," + stepDto.getStart_location().getLng() + "&destination="
				+ stepDto.getEnd_location().getLat() + "," + stepDto.getEnd_location().getLng()
				+ "&key=AIzaSyBOpeA7yRsxEibmq4FSpNM_FqTSDoNNjGg", ResponseDto.class);

		List<Locality> localitiesInPath = new ArrayList<>();
		List<Locality> localities = this.localityRepository.findAll();

		reponse.getRoutes().get(0).getLegs().get(0).getSteps().forEach(step -> {

			double deltaLat = step.getEnd_location().getLat() - step.getStart_location().getLat();
			double deltaLng = step.getEnd_location().getLng() - step.getStart_location().getLng();

			// Y = AX + B
			// A
			double a = (deltaLat > 0) ? (deltaLng) / (deltaLat) : 0;
			// B
			double b = -1 * a * step.getStart_location().getLat() + step.getStart_location().getLng();

			for (Iterator<Locality> iter = localities.iterator(); iter.hasNext();) {
				Locality locality = iter.next();
				if (a * locality.getAltitude() + b == locality.getLongitude()) {
					localitiesInPath.add(locality);
					iter.remove();
				}
			}
		});

		return localitiesInPath;
	}

}
