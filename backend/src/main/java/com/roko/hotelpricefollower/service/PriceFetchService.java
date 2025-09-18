package com.roko.hotelpricefollower.service;

import com.roko.hotelpricefollower.domain.PriceFetch;
import com.roko.hotelpricefollower.domain.ScrapeProfile;
import com.roko.hotelpricefollower.repository.PriceFetchRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceFetchService {

    private final PriceFetchRepository priceFetchRepository;

    public PriceFetchService(PriceFetchRepository priceFetchRepository) {
        this.priceFetchRepository = priceFetchRepository;
    }

    public Optional<PriceFetch> getMostRecentPriceFetch(ScrapeProfile scrapeProfile) {
        return priceFetchRepository.findFirstByScrapeProfileOrderByFetchTimeDesc(scrapeProfile);
    }

    public PriceFetch createPriceFetch(ScrapeProfile scrapeProfile) {
        PriceFetch priceFetch = new PriceFetch();
        priceFetch.setScrapeProfile(scrapeProfile);

        return priceFetchRepository.save(priceFetch);
    }

    public PriceFetch savePriceFetch(PriceFetch priceFetch) {
        return priceFetchRepository.save(priceFetch);
    }
}
