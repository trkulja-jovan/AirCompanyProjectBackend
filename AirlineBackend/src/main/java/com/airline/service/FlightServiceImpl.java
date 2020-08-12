package com.airline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.dto.AerodromDto;
import com.airline.dto.AirportDto;
import com.airline.dto.AviokompanijaDto;
import com.airline.dto.KlasaDto;
import com.airline.dto.LetDto;
import com.airline.dto.PodaciLetaDto;
import com.airline.dto.SearchFlightDto;
import com.airline.interfaces.service.IFlightService;
import com.airline.repository.AerodromRepository;
import com.airline.repository.LetRepository;

@Service
@SuppressWarnings("unchecked")
class FlightServiceImpl extends BaseService implements IFlightService {
	
	@Autowired
	private AerodromRepository aerodromRepository;
	
	@Autowired
	private LetRepository letRepository;
	
	@Override
	public ResponseEntity<List<AirportDto>> getAllAirports(String token) {
		
		if(super.isLogged(token)) {
			
			var list = aerodromRepository.findAll();
			
			var airports = list.stream()
					           .map(airp -> {
					        	   var aer = new AirportDto();
					        	   
					        	   aer.setIdAerodrom(airp.getIdAerodrom());
					        	   aer.setCode(airp.getCode());
					        	   aer.setGrad(airp.getGrad());
					        	   aer.setDrzava(airp.getDrzava());
					        	   
					        	   return aer;
					           })
					           .collect(Collectors.toList());
			
			
			return ok(airports);
		} else {
			
			return super.unauthorizedRequest();
		}
	}

	@Override
	public ResponseEntity<List<LetDto>> searchFlights(String token, SearchFlightDto data) {
		if(super.isLogged(token)) {
			
			var allLets = letRepository.getAllFlightsByCriteria(data.getIdAerodromOd(), data.getIdAerodromDo())
					                   .stream()
					                   .map(let -> {
					                	   var avio = let.getAviokompanija();
					                	   var aviokomp = new AviokompanijaDto();
					                	   aviokomp.setIdAviokompanija(avio.getIdAviokompanija());
					                	   aviokomp.setNazivKompanije(avio.getNazivKompanije());
					                	   
					                	   var aerPol = let.getPodacileta().getAerodrom1();
					                	   var aerodromPol = new AerodromDto();
					                	   aerodromPol.setCode(aerPol.getCode());
					                	   aerodromPol.setDrzava(aerPol.getDrzava());
					                	   aerodromPol.setGrad(aerPol.getGrad());
					                	   aerodromPol.setIdAerodrom(aerPol.getIdAerodrom());
					                	   
					                	   var aerDol = let.getPodacileta().getAerodrom2();
					                	   var aerodromDol = new AerodromDto();
					                	   aerodromDol.setCode(aerDol.getCode());
					                	   aerodromDol.setDrzava(aerDol.getDrzava());
					                	   aerodromDol.setGrad(aerDol.getGrad());
					                	   aerodromDol.setIdAerodrom(aerDol.getIdAerodrom());
					                	   
					                	   var podaci = let.getPodacileta();
					                	   var podaciLeta = new PodaciLetaDto();
					                	   podaciLeta.setIdPodaci(podaci.getIdPodaciLeta());
					                	   podaciLeta.setAerodrom1(aerodromPol);
					                	   podaciLeta.setAerodrom2(aerodromDol);
					                	   
					                	   var x = new LetDto();
					                	   x.setIdLet(let.getIdLet());
					                	   x.setOznakaLeta(let.getOznakaLeta());
					                	   x.setAviokompanija(aviokomp);
					                	   x.setPodaciLeta(podaciLeta);
					                	   x.setCena(12_000.00);
					                	   
					                	   x.setKlasa(let.getKlasas().stream().map(k -> super._mapperKlasa.mapFromJson(k.getJson(), KlasaDto.class)).collect(Collectors.toList()));
					                	   
					                	   
					                	   return x;
					                	   
					                   }).collect(Collectors.toList());
			
			if(data.getTipPutovanja() == 2) {
				var lets = letRepository.getAllReturnFlightsByCriteria(data.getIdAerodromOd(), data.getIdAerodromDo(), data.getDatumPovratka());
			}
			
			return ok(allLets);
			
		} else
			return super.unauthorizedRequest();
	}
	
}
