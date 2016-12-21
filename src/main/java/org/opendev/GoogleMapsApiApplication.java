package org.opendev;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opendev.dao.LocalityRepository;
import org.opendev.dto.ResponseDto;
import org.opendev.entities.Locality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GoogleMapsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleMapsApiApplication.class, args);
	}

	@Autowired
	LocalityRepository localityRepository;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ResponseDto reponse = restTemplate.getForObject(
					"https://maps.googleapis.com/maps/api/directions/json?origin=40.74560,-73.94622000000001&destination=46.59122000000001,-112.004230&key=AIzaSyBOpeA7yRsxEibmq4FSpNM_FqTSDoNNjGg",
					ResponseDto.class);
			List<Locality> localitiesInPath = new ArrayList<>();
			List<Locality> localities = this.localityRepository.findAll();

			reponse.getRoutes().get(0).getLegs().get(0).getSteps().forEach(step -> {
				System.out.println("** -------------------------------------------- **");

				double deltaLat = step.getEnd_location().getLat() - step.getStart_location().getLat();
				double deltaLng = step.getEnd_location().getLng() - step.getStart_location().getLng();

				double a = (deltaLat > 0) ? (deltaLng) / (deltaLat) : 0;
				double b = -1 * a * step.getStart_location().getLat() + step.getStart_location().getLng();

				System.out.println("STAR LOC");
				System.out.println(step.getStart_location());
				System.out.println("END LOC");
				System.out.println(step.getEnd_location());
				System.out.println("Y = AX + B");
				System.out.println("Y = " + a + "X " + b);

				for (Iterator<Locality> iter = localities.iterator(); iter.hasNext();) {
					Locality locality = iter.next();
					if (a * locality.getAltitude() + b == locality.getLongitude()) {
						localitiesInPath.add(locality);
						iter.remove();
					}
				}

			});
			System.out.println("** -------------------------------------------- **");
			localitiesInPath.forEach(locality -> {
				System.out.println("IN THE PATH ...");
				System.out.println(locality);
			});
		};
	}

}
