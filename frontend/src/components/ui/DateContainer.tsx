import type { JSX } from 'react';
import type { PriceSummary } from '@/types/Price.js';
import { formatDateWithWeekday } from '@/utils/dateConverter.js';

type DateContainerProps = {
  dates: PriceSummary[];
};

function DateContainer(props: DateContainerProps): JSX.Element {
  const { dates } = props;

  return (
    <div className="flex justify-evenly text-center font-bold mb-1 w-full px-10">
      {dates.map((date) => (
        <span className="flex-1 min-w-20" key={date.departureDate}>
          {formatDateWithWeekday(date.departureDate)}
        </span>
      ))}
    </div>
  );
}

export default DateContainer;
