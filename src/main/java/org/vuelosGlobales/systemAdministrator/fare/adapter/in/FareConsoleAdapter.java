package org.vuelosGlobales.systemAdministrator.fare.adapter.in;

import org.vuelosGlobales.systemAdministrator.fare.application.FareService;

public class FareConsoleAdapter {
    private final FareService fareService;

    public FareConsoleAdapter(FareService fareService) {
        this.fareService = fareService;
    }
}
