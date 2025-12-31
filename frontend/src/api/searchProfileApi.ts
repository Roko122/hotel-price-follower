import axios from 'axios';
import type { SearchProfile } from '../types/SearchProfile';

const baseUrl: string = 'http://localhost:8080/api/v1/hotels';

async function getSearchProfiles(hotelId: number): Promise<SearchProfile[]> {
  const url: string = baseUrl + `/${hotelId}/profiles`;
  const res = await axios.get(url);
  return res.data;
}

export default getSearchProfiles;
