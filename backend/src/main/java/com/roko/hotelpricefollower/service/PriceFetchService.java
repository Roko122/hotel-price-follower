package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeTask;
import com.roko.hotelpricefollower.repository.PriceFetchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceFetchService {

    private final PriceFetchRepository priceFetchRepository;

    public PriceFetchService(PriceFetchRepository priceFetchRepository) {
        this.priceFetchRepository = priceFetchRepository;
    }

    public Optional<PriceFetch> getMostRecentPriceFetch(ScrapeTask scrapeTask) {
        return priceFetchRepository.findFirstByScrapeTaskOrderByFetchTimeDesc(scrapeTask);
    }

    public PriceFetch createPriceFetch(ScrapeTask scrapeTask) {
        PriceFetch priceFetch = new PriceFetch();
        priceFetch.setScrapeTask(scrapeTask);

        return priceFetchRepository.save(priceFetch);
    }

    public PriceFetch savePriceFetch(PriceFetch priceFetch) {
        return priceFetchRepository.save(priceFetch);
    }
}
