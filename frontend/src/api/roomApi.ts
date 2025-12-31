import axios from 'axios';
import type { Room } from '../types/Room.ts';

const baseUrl: string = 'http://localhost:8080/api/v1/hotels';

async function getRooms(hotelId: number): Promise<Room[]> {
  const url: string = baseUrl + `/${hotelId}/rooms`;
  const res = await axios.get(url);
  return res.data;
}

export default getRooms;
