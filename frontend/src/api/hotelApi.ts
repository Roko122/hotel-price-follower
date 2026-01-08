import axios from 'axios';
import type { Hotel } from '../types/Hotel';
import type { FetchTime } from '@/types/FetchTime.js';

const baseUrl: string = 'http://localhost:8080/api/v1/hotels';

export async function getHotels(): Promise<Hotel[]> {
  const res = await axios.get(baseUrl);
  return res.data;
}

export async function getFetchTime(hotelId: number): Promise<FetchTime> {
  const url = `${baseUrl}/${hotelId}/last-fetch`;
  const res = await axios.get(url);
  return res.data;
}
