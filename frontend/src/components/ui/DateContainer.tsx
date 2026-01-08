import type { JSX } from 'react';
import type { PriceSummary } from '@/types/Price.js';
import { formatDateWithWeekday } from '@/util/dateConverter.js';

type DateContainerProps = {
  dates: PriceSummary[];
};

function DateContainer(props: DateContainerProps): JSX.Element {
  const { dates } = props;

  return (
    <div className="flex justify-evenly text-center gap-10 font-bold mb-1 px-10 w-full">
      {dates.map((date) => (
        <span key={date.departureDate}>{formatDateWithWeekday(date.departureDate)}</span>
      ))}
    </div>
  );
}

export default DateContainer;
