package org.vuelosGlobales.generals.tripcrew.infrastructure;

import org.vuelosGlobales.generals.tripcrew.domain.TripCrew;
import org.vuelosGlobales.generals.tripcrew.domain.TripCrewInfoDTO;

import java.util.List;

public interface TripCrewRepository {
     void save(TripCrew tripCrew);
     List<TripCrewInfoDTO> showCrewByConn(int idConn);
}
