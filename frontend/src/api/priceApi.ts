import axios from 'axios';
import type { PriceData, PriceSummary } from '../types/Price';

const baseUrl: string = 'http://localhost:8080/api/v1/hotels';

async function getPriceSummaryForRoom(
  hotelId: number,
  profileId: number,
  roomId: number,
  departureDates: string[]
): Promise<PriceSummary[]> {
  const url: string = baseUrl + `/${hotelId}/rooms/${roomId}/prices/summary`;

  const res = await axios.get(url, {
    params: {
      profileId: profileId,
      departureDates: departureDates.join(',')
    }
  });
  return res.data;
}

async function getAllPricesForRoom(
  hotelId: number,
  profileId: number,
  roomId: number,
  departureDate: string
): Promise<PriceData[]> {
  const url: string = baseUrl + `/${hotelId}/rooms/${roomId}/prices`;

  const res = await axios.get(url, {
    params: {
      profileId: profileId,
      departureDate: departureDate
    }
  });
  return res.data;
}

export default { getPriceSummaryForRoom, getAllPricesForRoom };
