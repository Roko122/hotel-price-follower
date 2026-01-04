import { Select, SelectContent, SelectItem, SelectSeparator, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Fragment } from 'react';

import type { Hotel } from '@/types/Hotel.js';
import type { JSX } from 'react';

type HotelSelectProps = {
  hotels: Hotel[];
  selectedHotel: string | undefined;
  setSelectedHotel: (selectedHotel: string | undefined) => void;
};

function HotelSelect(props: HotelSelectProps): JSX.Element {
  const { hotels, selectedHotel, setSelectedHotel } = props;

  return (
    <Select value={selectedHotel} onValueChange={setSelectedHotel}>
      <SelectTrigger className="w-full">
        <SelectValue placeholder="Valitse hotelli" />
      </SelectTrigger>
      <SelectContent position="popper" className="w-full">
        {hotels.map((hotel, index) => (
          <Fragment key={hotel.id}>
            <SelectItem value={hotel.id.toString()}>{hotel.name}</SelectItem>
            {index < hotels.length - 1 && <SelectSeparator />}
          </Fragment>
        ))}
      </SelectContent>
    </Select>
  );
}

export default HotelSelect;
