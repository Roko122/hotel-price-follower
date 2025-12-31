import axios from 'axios';
import type { Hotel } from '../types/Hotel';

const baseUrl: string = 'http://localhost:8080/api/v1/hotels';

async function getHotels(): Promise<Hotel[]> {
  const res = await axios.get(baseUrl);
  return res.data;
}

export default getHotels;
