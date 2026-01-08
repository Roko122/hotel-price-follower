import type { JSX } from 'react';
import type { Hotel } from '@/types/Hotel.js';
import type { FetchTime } from '@/types/FetchTime.js';
import { Spinner } from '@/components/ui/spinner.js';

type HotelPictureFetchTimeContainerProps = {
  hotel: Hotel;
  fetchTime: FetchTime | undefined;
};

function HotelPictureFetchTimeContainer(props: HotelPictureFetchTimeContainerProps): JSX.Element {
  const { hotel, fetchTime } = props;

  if (!fetchTime?.lastFetchTime) {
    return (
      <>
        <div className="flex justify-center items-center border-2 rounded-2xl w-1/4 h-70">
          <Spinner className="size-8 text-yellow-400" />
        </div>
        <p className="mt-2 text-gray-600 text-xl">Ladataan tietoja...</p>
      </>
    );
  }

  const dateObject = new Date(fetchTime.lastFetchTime);

  const dateString = () => {
    if (dateObject.getDate() === new Date().getDay()) {
      return 'tänään';
    } else {
      return dateObject.toLocaleDateString('fi-FI');
    }
  };

  const dateTime: string = dateObject
    .toLocaleTimeString('fi-FI', {
      hour: '2-digit',
      minute: '2-digit'
    })
    .replace('.', ':');

  return (
    <>
      <img src={hotel?.imageUrl} className="border-2 border-gray-700 rounded-2xl w-1/4" />
      <p className="mt-2 text-gray-600 text-xl">
        Hinnat haettu viimeksi {dateString()} klo {dateTime}
      </p>
    </>
  );
}

export default HotelPictureFetchTimeContainer;
